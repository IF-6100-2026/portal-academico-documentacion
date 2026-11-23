package cr.ac.ucr.if6100.portal.handlers.queries;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface GetAcademicProgressQuery {
    Result query(Query query);

    record Query(String guardianUsername, UUID studentId, String period) { }

    sealed interface Result {
        record StudentList(List<StudentSummary> students) implements Result { }
        record Progress(StudentProfile student, String period, List<PublishedAssessment> assessments,
            Instant consultedAt) implements Result { }
        record NotAuthorized() implements Result { }
    }

    record StudentSummary(UUID id, String fullName, String gradeLevel, String groupName,
        List<RecentPublication> latestPublications, int pendingNotices) { }

    record RecentPublication(String subject, String title, Instant publishedAt) { }

    record StudentProfile(UUID id, String fullName, String gradeLevel, String groupName,
        String teacherName) { }

    record PublishedAssessment(UUID id, String subject, String title, LocalDate assignedDate,
        String instructions, BigDecimal grade, String observation, LocalDate gradePublishedDate,
        List<GradeRevision> previousVersions) { }

    record GradeRevision(BigDecimal grade, String observation, LocalDate publishedDate,
        String reason, String correctedBy, Instant correctedAt) { }
}
