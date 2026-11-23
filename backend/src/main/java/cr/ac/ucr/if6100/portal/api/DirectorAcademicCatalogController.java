package cr.ac.ucr.if6100.portal.api;

import cr.ac.ucr.if6100.portal.handlers.commands.ManageAcademicCatalogHandler;
import cr.ac.ucr.if6100.portal.handlers.commands.ManageAcademicCatalogHandler.GroupCommand;
import cr.ac.ucr.if6100.portal.handlers.commands.ManageAcademicCatalogHandler.Result;
import cr.ac.ucr.if6100.portal.handlers.commands.ManageAcademicCatalogHandler.TeacherCommand;
import cr.ac.ucr.if6100.portal.handlers.commands.ManageAcademicCatalogHandler.TeacherUpdate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Adapta las operaciones del catálogo académico a HTTP para Dirección. */
@RestController
@RequestMapping("/api/v1/director/academic")
public class DirectorAcademicCatalogController {
    private static final int USERNAME_LENGTH = 80;
    private static final int PERSON_NAME_LENGTH = 160;
    private static final int SUBJECT_LENGTH = 100;
    private static final int GROUP_LABEL_LENGTH = 30;
    private static final int PERIOD_LENGTH = 40;
    private final ManageAcademicCatalogHandler handler;

    public DirectorAcademicCatalogController(ManageAcademicCatalogHandler handler) {
        this.handler = handler;
    }

    @GetMapping("/catalog")
    public ResponseEntity<?> catalog(Authentication authentication) {
        return response(handler.catalog(authentication.getName()), HttpStatus.OK);
    }

    @PostMapping("/teachers")
    public ResponseEntity<?> registerTeacher(Authentication authentication,
        @Valid @RequestBody TeacherRequest request) {
        Result result = handler.registerTeacher(authentication.getName(),
            new TeacherCommand(request.username(), request.displayName()));
        return response(result, HttpStatus.CREATED);
    }

    @PatchMapping("/teachers/{username}")
    public ResponseEntity<?> updateTeacher(Authentication authentication,
        @PathVariable String username, @Valid @RequestBody TeacherUpdateRequest request) {
        Result result = handler.updateTeacher(authentication.getName(), username,
            new TeacherUpdate(request.displayName(), request.active()));
        return response(result, HttpStatus.OK);
    }

    @PostMapping("/groups")
    public ResponseEntity<?> registerGroup(Authentication authentication,
        @Valid @RequestBody GroupRequest request) {
        Result result = handler.registerGroup(authentication.getName(),
            new GroupCommand(request.subject(), request.gradeLevel(), request.groupName(),
                request.period()));
        return response(result, HttpStatus.CREATED);
    }

    @PostMapping("/groups/{groupId}/teachers")
    public ResponseEntity<?> assignTeacher(Authentication authentication,
        @PathVariable UUID groupId, @Valid @RequestBody AssignTeacherRequest request) {
        Result result = handler.assignTeacher(authentication.getName(), groupId,
            request.teacherUsername());
        return response(result, HttpStatus.CREATED);
    }

    @PostMapping("/groups/{groupId}/students")
    public ResponseEntity<?> enrollStudent(Authentication authentication,
        @PathVariable UUID groupId, @Valid @RequestBody EnrollStudentRequest request) {
        Result result = handler.enrollStudent(authentication.getName(), groupId,
            request.studentId());
        return response(result, HttpStatus.CREATED);
    }

    private ResponseEntity<?> response(Result result, HttpStatus createdStatus) {
        return switch (result) {
            case Result.Success success -> ResponseEntity.status(
                success.existing() ? HttpStatus.OK : createdStatus).body(success.value());
            case Result.InvalidField invalid -> ResponseEntity.badRequest()
                .body(ApiErrorResponse.forField(invalid.field()));
            case Result.Forbidden ignored -> ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ApiErrorResponse.generic("ACCESS_DENIED",
                    "No tiene permiso para mantener el catálogo académico."));
            case Result.NotFound missing -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiErrorResponse.generic("RESOURCE_NOT_FOUND",
                    "No existe un registro activo para " + missing.resource() + "."));
            case Result.Conflict conflict -> ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiErrorResponse.generic(conflict.code(),
                    "No se pudo completar la operación por un conflicto de datos."));
        };
    }

    public record TeacherRequest(@NotBlank @Size(max = USERNAME_LENGTH) String username,
        @NotBlank @Size(max = PERSON_NAME_LENGTH) String displayName) { }

    public record TeacherUpdateRequest(@NotBlank @Size(max = PERSON_NAME_LENGTH)
        String displayName, boolean active) { }

    public record GroupRequest(@NotBlank @Size(max = SUBJECT_LENGTH) String subject,
        @NotBlank @Size(max = GROUP_LABEL_LENGTH) String gradeLevel,
        @NotBlank @Size(max = GROUP_LABEL_LENGTH) String groupName,
        @NotBlank @Size(max = PERIOD_LENGTH) String period) { }

    public record AssignTeacherRequest(@NotBlank @Size(max = USERNAME_LENGTH)
        String teacherUsername) { }

    public record EnrollStudentRequest(@NotNull UUID studentId) { }
}
