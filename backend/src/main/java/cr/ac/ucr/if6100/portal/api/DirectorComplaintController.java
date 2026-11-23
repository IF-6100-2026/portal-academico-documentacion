package cr.ac.ucr.if6100.portal.api;

import cr.ac.ucr.if6100.portal.handlers.commands.impl.StudentCommunicationWorkflow;
import cr.ac.ucr.if6100.portal.handlers.commands.impl.StudentCommunicationWorkflow.ComplaintResolution;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;
import java.util.UUID;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Presenta a Dirección las quejas abiertas y conserva el hallazgo y la respuesta. */
@RestController
@RequestMapping("/api/v1/director/complaints")
public class DirectorComplaintController {
    private static final int TEXT_LENGTH = 4000;
    private final StudentCommunicationWorkflow workflow;

    public DirectorComplaintController(StudentCommunicationWorkflow workflow) {
        this.workflow = workflow;
    }

    @GetMapping
    public List<StudentCommunicationWorkflow.ComplaintView> complaints() {
        return workflow.complaints();
    }

    @PatchMapping("/{complaintId}")
    public StudentCommunicationWorkflow.ComplaintView attend(Authentication authentication,
        @PathVariable UUID complaintId, @Valid @RequestBody ResolutionRequest request) {
        return workflow.attendComplaint(authentication.getName(), complaintId,
            new ComplaintResolution(request.finding(), request.response(), request.teacherUsername()));
    }

    public record ResolutionRequest(@NotBlank String finding,
        @NotBlank @Size(max = TEXT_LENGTH) String response, String teacherUsername) { }
}
