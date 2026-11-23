package cr.ac.ucr.if6100.portal.api;

import cr.ac.ucr.if6100.portal.handlers.queries.GetAcademicProgressQuery;
import cr.ac.ucr.if6100.portal.handlers.queries.GetAcademicProgressQuery.Result;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/v1/guardian")
public class GuardianAcademicController {
    private final GetAcademicProgressQuery query;

    public GuardianAcademicController(GetAcademicProgressQuery query) {
        this.query = query;
    }

    @GetMapping("/students")
    public ResponseEntity<?> students(Authentication authentication) {
        Result result = query.query(new GetAcademicProgressQuery.Query(
            authentication.getName(), null, "*"));
        return switch (result) {
            case Result.StudentList students -> ResponseEntity.ok(students);
            case Result.Progress ignored -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            case Result.NotAuthorized ignored -> ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                new ApiErrorResponse("ACCESS_DENIED", "No tiene permiso para realizar esta operación.",
                    UUID.randomUUID()));
        };
    }

    @GetMapping("/students/{studentId}/progress")
    public ResponseEntity<?> progress(Authentication authentication, @PathVariable UUID studentId,
        @RequestParam @NotBlank String period) {
        Result result = query.query(new GetAcademicProgressQuery.Query(
            authentication.getName(), studentId, period.trim()));
        return switch (result) {
            case Result.StudentList ignored -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            case Result.Progress progress -> ResponseEntity.ok(progress);
            case Result.NotAuthorized ignored -> ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                new ApiErrorResponse("ACCESS_DENIED", "No tiene permiso para consultar este registro.",
                    UUID.randomUUID()));
        };
    }
}
