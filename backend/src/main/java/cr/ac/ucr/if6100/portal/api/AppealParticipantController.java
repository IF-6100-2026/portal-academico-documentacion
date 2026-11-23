package cr.ac.ucr.if6100.portal.api;

import cr.ac.ucr.if6100.portal.handlers.commands.impl.AppealWorkflow;
import cr.ac.ucr.if6100.portal.handlers.commands.impl.AppealWorkflow.ContributionCommand;
import cr.ac.ucr.if6100.portal.handlers.commands.impl.AppealWorkflow.ResolutionCommand;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
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

/** Expone aportes y decisiones del expediente a sus participantes institucionales. */
@RestController
@RequestMapping({"/api/v1/teacher/appeals", "/api/v1/director/appeals",
    "/api/v1/committee/appeals"})
public class AppealParticipantController {
    private static final int TEXT_LENGTH = 4000;
    private static final int SHORT_TEXT_LENGTH = 500;
    private static final int TYPE_LENGTH = 30;
    private final AppealWorkflow workflow;

    public AppealParticipantController(AppealWorkflow workflow) {
        this.workflow = workflow;
    }

    @GetMapping
    public List<AppealWorkflow.AppealView> appeals(Authentication authentication) {
        return workflow.appeals(authentication.getName());
    }

    @GetMapping("/{appealId}")
    public AppealWorkflow.AppealView appeal(Authentication authentication,
        @PathVariable UUID appealId) {
        return workflow.appeal(authentication.getName(), appealId);
    }

    @PostMapping("/{appealId}/contributions")
    public AppealWorkflow.ContributionView contribute(Authentication authentication,
        @PathVariable UUID appealId, @Valid @RequestBody ContributionRequest request) {
        return workflow.contribute(authentication.getName(), appealId,
            new ContributionCommand(request.type(), request.content(), request.targetUsername()));
    }

    @PostMapping("/{appealId}/resolution")
    public AppealWorkflow.ResolutionView resolve(Authentication authentication,
        @PathVariable UUID appealId, @Valid @RequestBody ResolutionRequest request) {
        return workflow.resolve(authentication.getName(), appealId,
            new ResolutionCommand(request.outcome(), request.foundation(),
                request.committeeMembers(), request.correctionRequired()));
    }

    public record ContributionRequest(@NotBlank @Size(max = TYPE_LENGTH) String type,
        @NotBlank @Size(max = TEXT_LENGTH) String content, String targetUsername) { }

    public record ResolutionRequest(@NotBlank @Size(max = SHORT_TEXT_LENGTH) String outcome,
        @NotBlank @Size(max = TEXT_LENGTH) String foundation,
        @NotBlank @Size(max = SHORT_TEXT_LENGTH) String committeeMembers,
        boolean correctionRequired) { }
}
