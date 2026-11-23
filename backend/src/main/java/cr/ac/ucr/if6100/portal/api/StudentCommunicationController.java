package cr.ac.ucr.if6100.portal.api;

import cr.ac.ucr.if6100.portal.handlers.commands.impl.StudentCommunicationWorkflow;
import cr.ac.ucr.if6100.portal.handlers.commands.impl.StudentCommunicationWorkflow.MessageCommand;
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

/** Expone la conversación que comparten una persona encargada y el docente del estudiante. */
@RestController
@RequestMapping({"/api/v1/guardian", "/api/v1/teacher"})
public class StudentCommunicationController {
    private static final int SUBJECT_LENGTH = 180;
    private static final int USERNAME_LENGTH = 80;
    private static final int CONTENT_LENGTH = 4000;
    private final StudentCommunicationWorkflow workflow;

    public StudentCommunicationController(StudentCommunicationWorkflow workflow) {
        this.workflow = workflow;
    }

    @GetMapping("/students/{studentId}/messages")
    public List<StudentCommunicationWorkflow.MessageView> conversation(
        Authentication authentication, @PathVariable UUID studentId) {
        return workflow.conversation(authentication.getName(), studentId);
    }

    @PostMapping("/students/{studentId}/messages")
    public StudentCommunicationWorkflow.MessageView send(Authentication authentication,
        @PathVariable UUID studentId, @Valid @RequestBody MessageRequest request) {
        return workflow.sendMessage(authentication.getName(), studentId,
            new MessageCommand(request.recipientUsername(), request.subject(), request.content(),
                request.topic(), request.replyToMessageId()));
    }

    @PostMapping("/messages/{messageId}/read")
    public StudentCommunicationWorkflow.MessageView markRead(Authentication authentication,
        @PathVariable UUID messageId) {
        return workflow.markMessageRead(authentication.getName(), messageId);
    }

    public record MessageRequest(@NotBlank @Size(max = USERNAME_LENGTH) String recipientUsername,
        @NotBlank @Size(max = SUBJECT_LENGTH) String subject,
        @NotBlank @Size(max = CONTENT_LENGTH) String content, String topic,
        UUID replyToMessageId) { }
}
