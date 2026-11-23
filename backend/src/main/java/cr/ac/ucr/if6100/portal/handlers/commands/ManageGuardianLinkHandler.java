package cr.ac.ucr.if6100.portal.handlers.commands;

import java.time.Instant;
import java.util.UUID;

public interface ManageGuardianLinkHandler {
    Result handle(Command command);

    record Command(String actorUsername, Action action, UUID linkId, String guardianUsername,
        UUID studentId, String relationshipType, String evidenceReference, String targetStatus,
        String reason) { }

    enum Action { CREATE, CHANGE_STATUS }

    sealed interface Result {
        record Created(LinkView link) implements Result { }
        record Updated(LinkView link) implements Result { }
        record Duplicate(Instant activatedAt) implements Result { }
        record InvalidField(String field) implements Result { }
        record RelationshipUnavailable() implements Result { }
        record LinkNotActive() implements Result { }
        record NotFound() implements Result { }
        record Forbidden() implements Result { }
    }

    record LinkView(UUID id, String guardianUsername, UUID studentId, String studentName,
        String relationshipType, String evidenceReference, String status, String createdBy,
        Instant createdAt, String changedBy, Instant changedAt, String changeReason) { }
}
