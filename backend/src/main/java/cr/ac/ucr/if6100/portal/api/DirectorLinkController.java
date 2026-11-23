package cr.ac.ucr.if6100.portal.api;

import cr.ac.ucr.if6100.portal.handlers.commands.ManageGuardianLinkHandler;
import cr.ac.ucr.if6100.portal.handlers.commands.ManageGuardianLinkHandler.Action;
import cr.ac.ucr.if6100.portal.handlers.commands.ManageGuardianLinkHandler.Command;
import cr.ac.ucr.if6100.portal.handlers.commands.ManageGuardianLinkHandler.Result;
import cr.ac.ucr.if6100.portal.handlers.queries.GetLinkCatalogQuery;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/director/guardian-links")
public class DirectorLinkController {
    private final ManageGuardianLinkHandler handler;
    private final GetLinkCatalogQuery catalogQuery;

    public DirectorLinkController(ManageGuardianLinkHandler handler, GetLinkCatalogQuery catalogQuery) {
        this.handler = handler;
        this.catalogQuery = catalogQuery;
    }

    @org.springframework.web.bind.annotation.GetMapping("/catalog")
    public ResponseEntity<?> catalog(Authentication authentication) {
        return switch (catalogQuery.query(authentication.getName())) {
            case GetLinkCatalogQuery.Result.Success success -> ResponseEntity.ok(success);
            case GetLinkCatalogQuery.Result.Forbidden ignored -> ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        };
    }

    @PostMapping
    public ResponseEntity<?> create(Authentication authentication, @Valid @RequestBody CreateLinkRequest request) {
        Result result = handler.handle(new Command(authentication.getName(), Action.CREATE, null,
            request.guardianUsername(), request.studentId(), request.relationshipType(),
            request.evidenceReference(), request.pending(), null, request.restrictionReason()));
        return switch (result) {
            case Result.Created created -> ResponseEntity.status(HttpStatus.CREATED).body(created.link());
            case Result.Duplicate duplicate -> ResponseEntity.ok(duplicate);
            case Result.PendingDuplicate duplicate -> ResponseEntity.ok(duplicate);
            case Result.InvalidField invalid -> ResponseEntity.badRequest().body(
                ApiErrorResponse.forField(invalid.field()));
            case Result.RelationshipUnavailable unavailable -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ApiErrorResponse.generic("RELATIONSHIP_UNAVAILABLE",
                    unavailable.field().equals("studentId")
                        ? "El estudiante no existe o está inactivo."
                        : "La persona encargada no existe o está inactiva."));
            case Result.LinkNotActive ignored -> ResponseEntity.status(HttpStatus.CONFLICT).build();
            case Result.NotFound ignored -> ResponseEntity.notFound().build();
            case Result.Forbidden ignored -> ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            case Result.Updated ignored -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        };
    }

    @PatchMapping("/{linkId}/status")
    public ResponseEntity<?> changeStatus(Authentication authentication, @PathVariable UUID linkId,
        @Valid @RequestBody ChangeStatusRequest request) {
        Result result = handler.handle(new Command(authentication.getName(), Action.CHANGE_STATUS,
            linkId, null, null, null, null, false, request.status(), request.reason()));
        return switch (result) {
            case Result.Updated updated -> ResponseEntity.ok(updated.link());
            case Result.InvalidField invalid -> ResponseEntity.badRequest().body(
                ApiErrorResponse.forField(invalid.field()));
            case Result.LinkNotActive ignored -> ResponseEntity.status(HttpStatus.CONFLICT).body(
                ApiErrorResponse.generic("LINK_NOT_ACTIVE", "El vínculo no está activo."));
            case Result.NotFound ignored -> ResponseEntity.notFound().build();
            case Result.Forbidden ignored -> ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            case Result.Created ignored -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            case Result.Duplicate ignored -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            case Result.PendingDuplicate ignored -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            case Result.RelationshipUnavailable ignored -> ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        };
    }

    public record CreateLinkRequest(@NotBlank String guardianUsername, @NotNull UUID studentId,
        @NotBlank String relationshipType, @NotBlank String evidenceReference, boolean pending,
        String restrictionReason) { }

    public record ChangeStatusRequest(@NotBlank String status, @NotBlank String reason) { }
}
