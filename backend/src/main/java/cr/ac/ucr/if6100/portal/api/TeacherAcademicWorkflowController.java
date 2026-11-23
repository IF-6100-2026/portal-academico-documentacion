package cr.ac.ucr.if6100.portal.api;

import cr.ac.ucr.if6100.portal.handlers.commands.impl.AcademicPublicationWorkflow;
import cr.ac.ucr.if6100.portal.handlers.commands.impl.AcademicPublicationWorkflow.AssessmentCommand;
import cr.ac.ucr.if6100.portal.handlers.commands.impl.AcademicPublicationWorkflow.GradeCommand;
import cr.ac.ucr.if6100.portal.handlers.commands.impl.AcademicPublicationWorkflow.GradeCorrection;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Publica evaluaciones y calificaciones para grupos asignados al docente. */
@RestController
@RequestMapping("/api/v1/teacher")
public class TeacherAcademicWorkflowController {
    private static final int TITLE_LENGTH = 180;
    private final AcademicPublicationWorkflow workflow;

    public TeacherAcademicWorkflowController(AcademicPublicationWorkflow workflow) {
        this.workflow = workflow;
    }

    @GetMapping("/catalog")
    public AcademicPublicationWorkflow.TeacherCatalog catalog(Authentication authentication) {
        return workflow.teacherCatalog(authentication.getName());
    }

    @PostMapping("/groups/{groupId}/assessments")
    public ResponseEntity<AcademicPublicationWorkflow.PublicationView> publishAssessment(
        Authentication authentication, @PathVariable UUID groupId,
        @Valid @RequestBody AssessmentRequest request) {
        var result = workflow.publishAssessment(authentication.getName(), groupId,
            new AssessmentCommand(request.title(), request.assignedDate(), request.dueDate(),
                request.instructions(), request.confirmedWithNotebook()));
        return ResponseEntity.status("PUBLISHED".equals(result.status())
            ? HttpStatus.CREATED : HttpStatus.OK).body(result);
    }

    @PostMapping("/assessments/{assessmentId}/grades")
    public ResponseEntity<AcademicPublicationWorkflow.GradeView> publishGrade(
        Authentication authentication, @PathVariable UUID assessmentId,
        @Valid @RequestBody GradeRequest request) {
        var result = workflow.publishGrade(authentication.getName(), assessmentId,
            new GradeCommand(request.numericValue(), request.observation(), request.publishedDate(),
                request.confirmedWithNotebook()));
        return ResponseEntity.status("PUBLISHED".equals(result.status())
            ? HttpStatus.CREATED : HttpStatus.ACCEPTED).body(result);
    }

    @PatchMapping("/assessments/{assessmentId}/grades")
    public AcademicPublicationWorkflow.GradeView correctGrade(
        Authentication authentication, @PathVariable UUID assessmentId,
        @Valid @RequestBody CorrectionRequest request) {
        return workflow.correctGrade(authentication.getName(), assessmentId,
            new GradeCorrection(request.numericValue(), request.observation(),
                request.publishedDate(), request.reason(), request.confirmedWithNotebook(),
                request.expectedVersion()));
    }

    public record AssessmentRequest(@NotBlank @Size(max = TITLE_LENGTH) String title,
        @NotNull LocalDate assignedDate, @NotNull LocalDate dueDate,
        @NotBlank String instructions, boolean confirmedWithNotebook) { }

    public record GradeRequest(@NotNull BigDecimal numericValue, @NotBlank String observation,
        @NotNull LocalDate publishedDate, boolean confirmedWithNotebook) { }

    public record CorrectionRequest(@NotNull BigDecimal numericValue, @NotBlank String observation,
        @NotNull LocalDate publishedDate, @NotBlank String reason,
        boolean confirmedWithNotebook, int expectedVersion) { }
}
