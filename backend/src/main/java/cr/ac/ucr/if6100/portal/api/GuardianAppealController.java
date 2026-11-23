package cr.ac.ucr.if6100.portal.api;

import cr.ac.ucr.if6100.portal.handlers.commands.impl.AppealWorkflow;
import cr.ac.ucr.if6100.portal.handlers.commands.impl.AppealWorkflow.AppealCommand;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import java.util.UUID;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Recibe apelaciones únicamente de la persona encargada vinculada al estudiante. */
@RestController
@RequestMapping("/api/v1/guardian/students/{studentId}/appeals")
public class GuardianAppealController {
    private static final int SHORT_TEXT_LENGTH = 500;
    private static final int TEXT_LENGTH = 4000;
    private final AppealWorkflow workflow;

    public GuardianAppealController(AppealWorkflow workflow) {
        this.workflow = workflow;
    }

    @GetMapping
    public List<AppealWorkflow.AppealView> mine(Authentication authentication) {
        return workflow.guardianAppeals(authentication.getName());
    }

    @PostMapping
    public AppealWorkflow.AppealView file(Authentication authentication,
        @PathVariable UUID studentId, @Valid @RequestBody AppealRequest request) {
        return workflow.fileAppeal(authentication.getName(), studentId,
            new AppealCommand(request.assessmentId(), request.points(), request.reason(),
                request.evidenceReference()));
    }

    public record AppealRequest(@NotNull UUID assessmentId,
        @NotBlank @Size(max = SHORT_TEXT_LENGTH) String points,
        @NotBlank @Size(max = TEXT_LENGTH) String reason,
        @NotBlank @Size(max = SHORT_TEXT_LENGTH) String evidenceReference) { }
}
