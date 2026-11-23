package cr.ac.ucr.if6100.portal.api;

import cr.ac.ucr.if6100.portal.handlers.commands.impl.StudentCommunicationWorkflow;
import cr.ac.ucr.if6100.portal.handlers.commands.impl.StudentCommunicationWorkflow.ComplaintCommand;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.UUID;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Permite a una persona encargada presentar una queja sobre información no recibida. */
@RestController
@RequestMapping("/api/v1/guardian/students/{studentId}/complaints")
public class GuardianComplaintController {
    private static final int TEXT_LENGTH = 4000;
    private static final int TYPE_LENGTH = 60;
    private static final int SUBJECT_LENGTH = 100;
    private static final int PERIOD_LENGTH = 40;
    private final StudentCommunicationWorkflow workflow;

    public GuardianComplaintController(StudentCommunicationWorkflow workflow) {
        this.workflow = workflow;
    }

    @PostMapping
    public StudentCommunicationWorkflow.ComplaintView file(Authentication authentication,
        @PathVariable UUID studentId, @Valid @RequestBody ComplaintRequest request) {
        return workflow.fileComplaint(authentication.getName(), studentId,
            new ComplaintCommand(request.informationType(), request.subject(), request.period(),
                request.content(), request.assessmentId(), request.reasonType()));
    }

    public record ComplaintRequest(@NotBlank @Size(max = TYPE_LENGTH) String informationType,
        @NotBlank @Size(max = SUBJECT_LENGTH) String subject,
        @NotBlank @Size(max = PERIOD_LENGTH) String period,
        @NotBlank @Size(max = TEXT_LENGTH) String content, UUID assessmentId,
        String reasonType) { }
}
