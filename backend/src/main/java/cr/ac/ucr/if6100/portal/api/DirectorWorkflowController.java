package cr.ac.ucr.if6100.portal.api;

import cr.ac.ucr.if6100.portal.handlers.commands.impl.AcademicPublicationWorkflow;
import cr.ac.ucr.if6100.portal.handlers.commands.impl.AcademicPublicationWorkflow.SettingsCommand;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Permite a Dirección configurar plazos y supervisar seguimientos. */
@RestController
@RequestMapping("/api/v1/director/workflow")
public class DirectorWorkflowController {
    private static final int MAX_APPEAL_WINDOW_DAYS = 90;
    private static final int MAX_RESOLUTION_WINDOW_DAYS = 90;
    private static final int MAX_REMINDER_INTERVAL_HOURS = 8760;
    private static final int MAX_REMINDERS = 10;
    private final AcademicPublicationWorkflow workflow;

    public DirectorWorkflowController(AcademicPublicationWorkflow workflow) {
        this.workflow = workflow;
    }

    @GetMapping("/settings")
    public ResponseEntity<AcademicPublicationWorkflow.SettingsView> settings() {
        var settings = workflow.settings();
        return settings == null ? ResponseEntity.noContent().build()
            : ResponseEntity.ok(settings);
    }

    @PutMapping("/settings")
    public AcademicPublicationWorkflow.SettingsView updateSettings(Authentication authentication,
        @Valid @RequestBody SettingsRequest request) {
        return workflow.updateSettings(authentication.getName(), new SettingsCommand(
            request.appealWindowDays(), request.resolutionWindowDays(),
            request.reminderIntervalHours(), request.maxReminders()));
    }

    @PostMapping("/reminders/process")
    public ResponseEntity<AcademicPublicationWorkflow.ReminderRun> processReminders(
        Authentication authentication) {
        var result = workflow.runReminders(authentication.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/supervision")
    public AcademicPublicationWorkflow.SupervisionView supervision() {
        return workflow.supervision();
    }

    public record SettingsRequest(@Min(1) @Max(MAX_APPEAL_WINDOW_DAYS) int appealWindowDays,
        @Min(1) @Max(MAX_RESOLUTION_WINDOW_DAYS) int resolutionWindowDays,
        @Min(1) @Max(MAX_REMINDER_INTERVAL_HOURS) int reminderIntervalHours,
        @Min(1) @Max(MAX_REMINDERS) int maxReminders) { }
}
