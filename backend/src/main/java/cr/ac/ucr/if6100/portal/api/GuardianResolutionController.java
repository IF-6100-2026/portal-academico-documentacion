package cr.ac.ucr.if6100.portal.api;

import cr.ac.ucr.if6100.portal.handlers.commands.impl.AppealWorkflow;
import java.util.UUID;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Registra el primer acuse de consulta de la resolución por quien presentó la apelación. */
@RestController
@RequestMapping("/api/v1/guardian/appeals")
public class GuardianResolutionController {
    private final AppealWorkflow workflow;

    public GuardianResolutionController(AppealWorkflow workflow) {
        this.workflow = workflow;
    }

    @PostMapping("/{appealId}/resolution/read")
    public AppealWorkflow.ResolutionView read(Authentication authentication,
        @PathVariable UUID appealId) {
        return workflow.readResolution(authentication.getName(), appealId);
    }
}
