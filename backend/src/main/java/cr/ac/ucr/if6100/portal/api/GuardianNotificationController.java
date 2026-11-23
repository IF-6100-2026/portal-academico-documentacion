package cr.ac.ucr.if6100.portal.api;

import cr.ac.ucr.if6100.portal.handlers.queries.impl.GuardianNotificationWorkflow;
import java.util.List;
import java.util.UUID;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Consulta y acusa recibo de avisos dirigidos a la persona encargada autenticada. */
@RestController
@RequestMapping("/api/v1/guardian/notifications")
public class GuardianNotificationController {
    private final GuardianNotificationWorkflow workflow;

    public GuardianNotificationController(GuardianNotificationWorkflow workflow) {
        this.workflow = workflow;
    }

    @GetMapping
    public List<GuardianNotificationWorkflow.NotificationView> list(Authentication authentication) {
        return workflow.notifications(authentication.getName());
    }

    @PostMapping("/{notificationId}/read")
    public GuardianNotificationWorkflow.NotificationView markRead(Authentication authentication,
        @PathVariable UUID notificationId) {
        return workflow.markRead(authentication.getName(), notificationId);
    }
}
