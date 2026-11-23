package cr.ac.ucr.if6100.portal.handlers.commands;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

/** Administra docentes, grupos académicos, asignaciones e inscripciones. */
public interface ManageAcademicCatalogHandler {
    Result catalog(String actorUsername);

    Result registerTeacher(String actorUsername, TeacherCommand command);

    Result updateTeacher(String actorUsername, String teacherUsername, TeacherUpdate command);

    Result registerGroup(String actorUsername, GroupCommand command);

    Result assignTeacher(String actorUsername, UUID groupId, String teacherUsername);

    Result enrollStudent(String actorUsername, UUID groupId, UUID studentId);

    record TeacherCommand(String username, String displayName) { }

    record TeacherUpdate(String displayName, boolean active) { }

    record GroupCommand(String subject, String gradeLevel, String groupName, String period) { }

    record TeacherView(String username, String displayName, boolean active,
        String createdBy, Instant createdAt, String changedBy, Instant changedAt) { }

    record GroupView(UUID id, String subject, String gradeLevel, String groupName, String period,
        boolean active, String createdBy, Instant createdAt) { }

    record AssignmentView(UUID id, UUID groupId, String subject, String gradeLevel,
        String groupName, String period, String teacherUsername, String teacherName,
        boolean active, String assignedBy, Instant assignedAt) { }

    record StudentOption(UUID id, String fullName, String gradeLevel, String groupName) { }

    record EnrollmentView(UUID id, UUID studentId, String studentName, UUID groupId,
        String subject, String gradeLevel, String groupName, String period, boolean active,
        String enrolledBy, Instant enrolledAt) { }

    record CatalogView(List<TeacherView> teachers, List<GroupView> groups,
        List<AssignmentView> assignments, List<EnrollmentView> enrollments,
        List<StudentOption> students) { }

    sealed interface Result {
        record Success(Object value, boolean existing) implements Result { }
        record InvalidField(String field) implements Result { }
        record Forbidden() implements Result { }
        record NotFound(String resource) implements Result { }
        record Conflict(String code) implements Result { }
    }
}
