package cr.ac.ucr.if6100.portal.handlers.queries.impl;

import cr.ac.ucr.if6100.portal.handlers.queries.GetAcademicProgressQuery;
import cr.ac.ucr.if6100.portal.handlers.queries.GetAcademicProgressQuery.GradeRevision;
import cr.ac.ucr.if6100.portal.handlers.queries.GetAcademicProgressQuery.RecentPublication;
import cr.ac.ucr.if6100.portal.persistence.entity.AssessmentEntity;
import cr.ac.ucr.if6100.portal.persistence.entity.ConsultationLogEntity;
import cr.ac.ucr.if6100.portal.persistence.entity.GradeEntity;
import cr.ac.ucr.if6100.portal.persistence.entity.GuardianStudentLinkEntity;
import cr.ac.ucr.if6100.portal.persistence.entity.PortalUserEntity;
import cr.ac.ucr.if6100.portal.persistence.entity.StudentEntity;
import cr.ac.ucr.if6100.portal.persistence.repository.AssessmentRepository;
import cr.ac.ucr.if6100.portal.persistence.repository.ConsultationLogRepository;
import cr.ac.ucr.if6100.portal.persistence.repository.GradeRepository;
import cr.ac.ucr.if6100.portal.persistence.repository.GradeRevisionRepository;
import cr.ac.ucr.if6100.portal.persistence.repository.GuardianStudentLinkRepository;
import cr.ac.ucr.if6100.portal.persistence.repository.PortalUserRepository;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class GetAcademicProgressQueryImpl implements GetAcademicProgressQuery {
    private static final int LATEST_PUBLICATION_LIMIT = 3;
    private final PortalUserRepository userRepository;
    private final GuardianStudentLinkRepository linkRepository;
    private final AssessmentRepository assessmentRepository;
    private final GradeRepository gradeRepository;
    private final GradeRevisionRepository revisionRepository;
    private final ConsultationLogRepository consultationLogRepository;

    public GetAcademicProgressQueryImpl(PortalUserRepository userRepository,
        GuardianStudentLinkRepository linkRepository,
        AssessmentRepository assessmentRepository, GradeRepository gradeRepository,
        GradeRevisionRepository revisionRepository, ConsultationLogRepository consultationLogRepository) {
        this.userRepository = userRepository;
        this.linkRepository = linkRepository;
        this.assessmentRepository = assessmentRepository;
        this.gradeRepository = gradeRepository;
        this.revisionRepository = revisionRepository;
        this.consultationLogRepository = consultationLogRepository;
    }

    @Override
    @Transactional
    public Result query(Query query) {
        var guardian = userRepository.findByUsernameAndRoleNameAndActiveTrue(
            query.guardianUsername(), "GUARDIAN");
        if (guardian.isEmpty()) {
            saveLog(query.guardianUsername(), query.studentId(), query.period(), "DENIED");
            return new Result.NotAuthorized();
        }
        if (query.studentId() == null) {
            return linkedStudents(guardian.get());
        }
        return progressFor(guardian.get(), query.studentId(), query.period());
    }

    private Result linkedStudents(PortalUserEntity guardian) {
        List<GuardianStudentLinkEntity> links = linkRepository
            .findByGuardianUsernameAndStatusOrderByCreatedAtDesc(guardian.getUsername(), "ACTIVE");
        saveLog(guardian.getUsername(), null, "*", links.isEmpty() ? "NO_ACTIVE_LINKS" : "LISTED");
        List<StudentSummary> students = links.stream()
            .map(link -> studentSummary(guardian.getUsername(), link))
            .toList();
        return new Result.StudentList(students);
    }

    private StudentSummary studentSummary(String guardianUsername, GuardianStudentLinkEntity link) {
        UUID studentId = link.getStudent().getId();
        List<AssessmentEntity> publications = assessmentRepository
            .findByStudentIdAndPublishedTrueOrderByPublishedAtDesc(studentId).stream()
            .filter(publication -> publication.getPublishedAt() != null)
            .toList();
        Instant lastConsultedAt = consultationLogRepository
            .findTopByGuardianUsernameAndStudentIdAndOutcomeOrderByConsultedAtDesc(
                guardianUsername, studentId, "AUTHORIZED")
            .map(ConsultationLogEntity::getConsultedAt).orElse(null);
        int pendingNotices = (int) publications.stream()
            .filter(publication -> lastConsultedAt == null
                || publication.getPublishedAt().isAfter(lastConsultedAt))
            .count();
        List<RecentPublication> latestPublications = publications.stream()
            .limit(LATEST_PUBLICATION_LIMIT)
            .map(publication -> new RecentPublication(publication.getSubject(),
                publication.getTitle(), publication.getPublishedAt()))
            .toList();
        StudentEntity student = link.getStudent();
        return new StudentSummary(studentId, student.getFullName(), student.getGradeLevel(),
            student.getGroupName(), latestPublications, pendingNotices);
    }

    private Result progressFor(PortalUserEntity guardian, UUID studentId, String period) {
        var link = linkRepository.findByGuardianUsernameAndStudentIdAndStatus(
            guardian.getUsername(), studentId, "ACTIVE");
        if (link.isEmpty()) {
            saveLog(guardian.getUsername(), studentId, period, "DENIED");
            return new Result.NotAuthorized();
        }
        StudentEntity student = link.get().getStudent();
        List<PublishedAssessment> assessments = assessmentRepository
            .findByStudentIdAndPeriodAndPublishedTrueOrderByAssignedDateDesc(studentId, period)
            .stream().map(this::toPublishedAssessment).toList();
        Instant consultedAt = Instant.now();
        saveLog(guardian.getUsername(), studentId, period, "AUTHORIZED");
        StudentProfile profile = new StudentProfile(student.getId(), student.getFullName(),
            student.getGradeLevel(), student.getGroupName(), student.getTeacherName());
        return new Result.Progress(profile, period, assessments, consultedAt);
    }

    private PublishedAssessment toPublishedAssessment(AssessmentEntity assessment) {
        GradeEntity grade = gradeRepository.findByAssessmentId(assessment.getId()).orElse(null);
        List<GradeRevision> previousVersions = revisionRepository
            .findByAssessmentIdOrderByCorrectedAtDesc(assessment.getId()).stream()
            .map(revision -> new GradeRevision(revision.getNumericValue(),
                revision.getObservation(), revision.getPublishedDate(), revision.getReason(),
                revision.getCorrectedBy(), revision.getCorrectedAt()))
            .toList();
        return new PublishedAssessment(assessment.getId(), assessment.getSubject(),
            assessment.getTitle(), assessment.getAssignedDate(), assessment.getInstructions(),
            grade == null ? null : grade.getNumericValue(),
            grade == null ? null : grade.getObservation(),
            grade == null ? null : grade.getPublishedDate(), previousVersions);
    }

    private void saveLog(String username, UUID studentId, String period, String outcome) {
        consultationLogRepository.save(new ConsultationLogEntity(username, studentId,
            period == null ? "*" : period, outcome, Instant.now()));
    }
}
