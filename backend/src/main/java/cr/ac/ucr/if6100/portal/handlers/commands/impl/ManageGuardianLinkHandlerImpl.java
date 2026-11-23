package cr.ac.ucr.if6100.portal.handlers.commands.impl;

import cr.ac.ucr.if6100.portal.handlers.commands.ManageGuardianLinkHandler;
import cr.ac.ucr.if6100.portal.persistence.entity.GuardianStudentLinkEntity;
import cr.ac.ucr.if6100.portal.persistence.entity.LinkHistoryEntity;
import cr.ac.ucr.if6100.portal.persistence.repository.GuardianStudentLinkRepository;
import cr.ac.ucr.if6100.portal.persistence.repository.LinkHistoryRepository;
import cr.ac.ucr.if6100.portal.persistence.repository.PortalUserRepository;
import cr.ac.ucr.if6100.portal.persistence.repository.StudentRepository;
import java.time.Instant;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ManageGuardianLinkHandlerImpl implements ManageGuardianLinkHandler {
    private final PortalUserRepository userRepository;
    private final StudentRepository studentRepository;
    private final GuardianStudentLinkRepository linkRepository;
    private final LinkHistoryRepository historyRepository;

    public ManageGuardianLinkHandlerImpl(PortalUserRepository userRepository,
        StudentRepository studentRepository, GuardianStudentLinkRepository linkRepository,
        LinkHistoryRepository historyRepository) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.linkRepository = linkRepository;
        this.historyRepository = historyRepository;
    }

    @Override
    @Transactional
    public Result handle(Command command) {
        if (userRepository.findByUsernameAndRoleNameAndActiveTrue(
            command.actorUsername(), "DIRECTOR").isEmpty()) {
            return new Result.Forbidden();
        }
        return switch (command.action()) {
            case CREATE -> create(command);
            case CHANGE_STATUS -> changeStatus(command);
        };
    }

    private Result create(Command command) {
        if (blank(command.guardianUsername())) {
            return new Result.InvalidField("guardianUsername");
        }
        if (command.studentId() == null) {
            return new Result.InvalidField("studentId");
        }
        if (blank(command.relationshipType())) {
            return new Result.InvalidField("relationshipType");
        }
        if (blank(command.evidenceReference())) {
            return new Result.InvalidField("evidenceReference");
        }
        if (command.pending() && blank(command.reason())) {
            return new Result.InvalidField("restrictionReason");
        }
        var guardian = userRepository.findByUsernameAndRoleNameAndActiveTrue(
            command.guardianUsername().trim(), "GUARDIAN");
        if (guardian.isEmpty()) {
            return new Result.RelationshipUnavailable("guardianUsername");
        }
        var student = studentRepository.findByIdAndActiveTrue(command.studentId());
        if (student.isEmpty()) {
            return new Result.RelationshipUnavailable("studentId");
        }
        var pending = linkRepository.findByGuardianUsernameAndStudentIdAndStatus(
            command.guardianUsername().trim(), command.studentId(), "PENDING");
        if (pending.isPresent()) {
            return new Result.PendingDuplicate(pending.get().getCreatedAt());
        }
        var existing = linkRepository.findByGuardianUsernameAndStudentIdAndStatus(
            command.guardianUsername().trim(), command.studentId(), "ACTIVE");
        if (existing.isPresent()) {
            return new Result.Duplicate(existing.get().getCreatedAt());
        }
        Instant now = Instant.now();
        String status = command.pending() ? "PENDING" : "ACTIVE";
        String reason = command.pending() ? command.reason().trim()
            : "Alta autorizada. Respaldo: " + command.evidenceReference().trim();
        GuardianStudentLinkEntity link = new GuardianStudentLinkEntity(
            guardian.get(), student.get(), command.relationshipType().trim(),
            command.evidenceReference().trim(), command.actorUsername(), now);
        if (command.pending()) {
            link.changeStatus("PENDING", reason, command.actorUsername(), now);
        }
        link = linkRepository.save(link);
        historyRepository.save(new LinkHistoryEntity(link, null, status, reason,
            command.actorUsername(), now));
        return new Result.Created(toView(link));
    }

    private Result changeStatus(Command command) {
        if (command.linkId() == null) {
            return new Result.InvalidField("linkId");
        }
        String newStatus = command.targetStatus() == null ? "" : command.targetStatus().trim();
        if (!newStatus.equals("SUSPENDED") && !newStatus.equals("ENDED")) {
            return new Result.InvalidField("targetStatus");
        }
        if (blank(command.reason())) {
            return new Result.InvalidField("reason");
        }
        var activeLink = linkRepository.findByIdAndStatus(command.linkId(), "ACTIVE");
        if (activeLink.isEmpty()) {
            return new Result.LinkNotActive();
        }
        GuardianStudentLinkEntity link = activeLink.get();
        Instant now = Instant.now();
        String previousStatus = link.getStatus();
        link.changeStatus(newStatus, command.reason().trim(), command.actorUsername(), now);
        historyRepository.save(new LinkHistoryEntity(link, previousStatus, newStatus,
            command.reason().trim(), command.actorUsername(), now));
        return new Result.Updated(toView(link));
    }

    private LinkView toView(GuardianStudentLinkEntity link) {
        return new LinkView(link.getId(), link.getGuardian().getUsername(),
            link.getStudent().getId(), link.getStudent().getFullName(),
            link.getRelationshipType(), link.getEvidenceReference(), link.getStatus(),
            link.getCreatedBy(), link.getCreatedAt(), link.getChangedBy(), link.getChangedAt(),
            link.getChangeReason());
    }

    private boolean blank(String value) {
        return value == null || value.isBlank();
    }
}
