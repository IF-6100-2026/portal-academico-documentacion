package cr.ac.ucr.if6100.portal.handlers.queries.impl;

import cr.ac.ucr.if6100.portal.handlers.commands.ManageGuardianLinkHandler.LinkView;
import cr.ac.ucr.if6100.portal.handlers.queries.GetLinkCatalogQuery;
import cr.ac.ucr.if6100.portal.persistence.entity.GuardianStudentLinkEntity;
import cr.ac.ucr.if6100.portal.persistence.repository.GuardianStudentLinkRepository;
import cr.ac.ucr.if6100.portal.persistence.repository.PortalUserRepository;
import cr.ac.ucr.if6100.portal.persistence.repository.StudentRepository;
import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class GetLinkCatalogQueryImpl implements GetLinkCatalogQuery {
    private final PortalUserRepository userRepository;
    private final StudentRepository studentRepository;
    private final GuardianStudentLinkRepository linkRepository;

    public GetLinkCatalogQueryImpl(PortalUserRepository userRepository,
        StudentRepository studentRepository, GuardianStudentLinkRepository linkRepository) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.linkRepository = linkRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Result query(String actorUsername) {
        if (userRepository.findByUsernameAndRoleNameAndActiveTrue(actorUsername, "DIRECTOR").isEmpty()) {
            return new Result.Forbidden();
        }
        List<GuardianOption> guardians = userRepository.findAll().stream()
            .filter(user -> user.isActive() && "GUARDIAN".equals(user.getRoleName()))
            .map(user -> new GuardianOption(user.getUsername(), user.getDisplayName()))
            .toList();
        List<StudentOption> students = studentRepository.findAll().stream()
            .filter(student -> student.isActive())
            .map(student -> new StudentOption(student.getId(), student.getFullName(),
                student.getGradeLevel(), student.getGroupName()))
            .sorted(Comparator.comparing(StudentOption::fullName))
            .toList();
        List<LinkView> links = linkRepository.findAll().stream()
            .map(this::toView).sorted(Comparator.comparing(LinkView::createdAt).reversed()).toList();
        return new Result.Success(guardians, students, links);
    }

    private LinkView toView(GuardianStudentLinkEntity link) {
        return new LinkView(link.getId(), link.getGuardian().getUsername(),
            link.getStudent().getId(), link.getStudent().getFullName(),
            link.getRelationshipType(), link.getEvidenceReference(), link.getStatus(),
            link.getCreatedBy(), link.getCreatedAt(), link.getChangedBy(), link.getChangedAt(),
            link.getChangeReason());
    }
}
