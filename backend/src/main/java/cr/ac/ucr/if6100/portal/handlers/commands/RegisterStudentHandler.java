package cr.ac.ucr.if6100.portal.handlers.commands;

import java.time.Instant;
import java.util.UUID;

/** Declara el comando, las respuestas y los datos de salida de registrar estudiante. */
public interface RegisterStudentHandler {
    Result handle(Command command);

    record Command(String actorUsername, String fullName, String gradeLevel,
        String groupName, String teacherName) { }

    sealed interface Result {
        record Created(StudentView student) implements Result { }
        record InvalidField(String field) implements Result { }
        record Forbidden() implements Result { }
    }

    record StudentView(UUID id, String fullName, String gradeLevel, String groupName,
        String teacherName, boolean active, String createdBy, Instant createdAt) { }
}
