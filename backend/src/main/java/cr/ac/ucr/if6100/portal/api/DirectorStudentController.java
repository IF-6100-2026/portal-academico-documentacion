package cr.ac.ucr.if6100.portal.api;

import cr.ac.ucr.if6100.portal.handlers.commands.RegisterStudentHandler;
import cr.ac.ucr.if6100.portal.handlers.commands.RegisterStudentHandler.Result;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Adapta la solicitud HTTP de Dirección al handler de registrar estudiante. */
@RestController
@RequestMapping("/api/v1/director/students")
public class DirectorStudentController {
    private static final int PERSON_NAME_LENGTH = 160;
    private static final int GROUP_LABEL_LENGTH = 30;
    private final RegisterStudentHandler handler;

    public DirectorStudentController(RegisterStudentHandler handler) {
        this.handler = handler;
    }

    @PostMapping
    public ResponseEntity<?> register(Authentication authentication,
        @Valid @RequestBody RegisterStudentRequest request) {
        Result result = handler.handle(new RegisterStudentHandler.Command(
            authentication.getName(), request.fullName(), request.gradeLevel(),
            request.groupName(), request.teacherName()));
        return switch (result) {
            case Result.Created created -> ResponseEntity.status(HttpStatus.CREATED)
                .body(created.student());
            case Result.InvalidField invalid -> ResponseEntity.badRequest()
                .body(ApiErrorResponse.forField(invalid.field()));
            case Result.Forbidden ignored -> ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        };
    }

    public record RegisterStudentRequest(
        @NotBlank @Size(max = PERSON_NAME_LENGTH) String fullName,
        @NotBlank @Size(max = GROUP_LABEL_LENGTH) String gradeLevel,
        @NotBlank @Size(max = GROUP_LABEL_LENGTH) String groupName,
        @NotBlank @Size(max = PERSON_NAME_LENGTH) String teacherName) { }
}
