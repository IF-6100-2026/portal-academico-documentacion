package cr.ac.ucr.if6100.portal.handlers.commands.impl;

import cr.ac.ucr.if6100.portal.handlers.commands.ManageAcademicCatalogHandler;
import cr.ac.ucr.if6100.portal.persistence.repository.PortalUserRepository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/** Implementa las operaciones de catálogo para el rol Dirección. */
@Component
public class ManageAcademicCatalogHandlerImpl implements ManageAcademicCatalogHandler {
    private static final int USERNAME_LENGTH = 80;
    private static final int PERSON_NAME_LENGTH = 160;
    private static final int SUBJECT_LENGTH = 100;
    private static final int GROUP_LABEL_LENGTH = 30;
    private static final int PERIOD_LENGTH = 40;
    private final PortalUserRepository userRepository;
    private final JdbcTemplate jdbcTemplate;

    public ManageAcademicCatalogHandlerImpl(PortalUserRepository userRepository,
        JdbcTemplate jdbcTemplate) {
        this.userRepository = userRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(readOnly = true)
    public Result catalog(String actorUsername) {
        if (!isDirector(actorUsername)) {
            return new Result.Forbidden();
        }
        List<TeacherView> teachers = jdbcTemplate.query("""
            SELECT username, display_name, active, created_by, created_at, changed_by, changed_at
            FROM teacher_profile ORDER BY display_name, username
            """, (result, row) -> teacherView(result));
        List<GroupView> groups = jdbcTemplate.query("""
            SELECT id, subject, grade_level, group_name, period, active, created_by, created_at
            FROM academic_group ORDER BY period DESC, grade_level, group_name, subject
            """, (result, row) -> groupView(result));
        List<AssignmentView> assignments = jdbcTemplate.query("""
            SELECT a.id, a.group_id, g.subject, g.grade_level, g.group_name, g.period,
                   t.username, t.display_name, a.active, a.assigned_by, a.assigned_at
            FROM teaching_assignment a
            JOIN academic_group g ON g.id = a.group_id
            JOIN teacher_profile t ON t.username = a.teacher_username
            ORDER BY a.assigned_at DESC
            """, (result, row) -> assignmentView(result));
        List<EnrollmentView> enrollments = jdbcTemplate.query("""
            SELECT e.id, e.student_id, s.full_name, e.group_id, g.subject, g.grade_level,
                   g.group_name, g.period, e.active, e.enrolled_by, e.enrolled_at
            FROM student_enrollment e
            JOIN student s ON s.id = e.student_id
            JOIN academic_group g ON g.id = e.group_id
            ORDER BY g.period DESC, s.full_name
            """, (result, row) -> enrollmentView(result));
        List<StudentOption> students = jdbcTemplate.query("""
            SELECT id, full_name, grade_level, group_name
            FROM student WHERE active = TRUE ORDER BY full_name
            """, (result, row) -> new StudentOption(result.getObject("id", UUID.class),
                result.getString("full_name"), result.getString("grade_level"),
                result.getString("group_name")));
        return new Result.Success(new CatalogView(teachers, groups, assignments,
            enrollments, students), false);
    }

    @Override
    @Transactional
    public Result registerTeacher(String actorUsername, TeacherCommand command) {
        if (!isDirector(actorUsername)) {
            return new Result.Forbidden();
        }
        String username = normalized(command.username());
        String displayName = normalized(command.displayName());
        if (invalid(username, USERNAME_LENGTH)) {
            return new Result.InvalidField("username");
        }
        if (invalid(displayName, PERSON_NAME_LENGTH)) {
            return new Result.InvalidField("displayName");
        }
        if (userRepository.findByUsernameAndRoleNameAndActiveTrue(username, "TEACHER").isEmpty()) {
            return new Result.NotFound("teacherAccount");
        }
        TeacherView existing = findTeacher(username);
        if (existing != null) {
            return new Result.Success(existing, true);
        }
        Instant now = Instant.now();
        jdbcTemplate.update("""
            INSERT INTO teacher_profile(username, display_name, active, created_by, created_at)
            VALUES (?, ?, TRUE, ?, ?)
            """, username, displayName, actorUsername, Timestamp.from(now));
        recordTeacherHistory(username, displayName, true, actorUsername, now);
        return new Result.Success(findTeacher(username), false);
    }

    @Override
    @Transactional
    public Result updateTeacher(String actorUsername, String teacherUsername,
        TeacherUpdate command) {
        if (!isDirector(actorUsername)) {
            return new Result.Forbidden();
        }
        String displayName = normalized(command.displayName());
        if (invalid(displayName, PERSON_NAME_LENGTH)) {
            return new Result.InvalidField("displayName");
        }
        TeacherView existing = findTeacher(teacherUsername);
        if (existing == null) {
            return new Result.NotFound("teacherProfile");
        }
        Instant now = Instant.now();
        jdbcTemplate.update("""
            UPDATE teacher_profile
            SET display_name = ?, active = ?, changed_by = ?, changed_at = ?
            WHERE username = ?
            """, displayName, command.active(), actorUsername, Timestamp.from(now),
            teacherUsername);
        recordTeacherHistory(teacherUsername, displayName, command.active(), actorUsername, now);
        return new Result.Success(findTeacher(teacherUsername), false);
    }

    @Override
    @Transactional
    public Result registerGroup(String actorUsername, GroupCommand command) {
        if (!isDirector(actorUsername)) {
            return new Result.Forbidden();
        }
        String subject = normalized(command.subject());
        String gradeLevel = normalized(command.gradeLevel());
        String groupName = normalized(command.groupName());
        String period = normalized(command.period());
        if (invalid(subject, SUBJECT_LENGTH)) {
            return new Result.InvalidField("subject");
        }
        if (invalid(gradeLevel, GROUP_LABEL_LENGTH)) {
            return new Result.InvalidField("gradeLevel");
        }
        if (invalid(groupName, GROUP_LABEL_LENGTH)) {
            return new Result.InvalidField("groupName");
        }
        if (invalid(period, PERIOD_LENGTH) || !period.matches("\\d{4}-(I|II)")) {
            return new Result.InvalidField("period");
        }
        GroupView existing = findGroup(subject, gradeLevel, groupName, period);
        if (existing != null) {
            return new Result.Success(existing, true);
        }
        UUID id = UUID.randomUUID();
        jdbcTemplate.update("""
            INSERT INTO academic_group
              (id, subject, grade_level, group_name, period, active, created_by, created_at)
            VALUES (?, ?, ?, ?, ?, TRUE, ?, ?)
            ON CONFLICT ON CONSTRAINT uq_academic_group_identity DO NOTHING
            """, id, subject, gradeLevel, groupName, period, actorUsername,
            Timestamp.from(Instant.now()));
        GroupView created = findGroup(subject, gradeLevel, groupName, period);
        return new Result.Success(created, !id.equals(created.id()));
    }

    @Override
    @Transactional
    public Result assignTeacher(String actorUsername, UUID groupId, String teacherUsername) {
        if (!isDirector(actorUsername)) {
            return new Result.Forbidden();
        }
        GroupView group = findActiveGroup(groupId);
        if (group == null) {
            return new Result.NotFound("academicGroup");
        }
        TeacherView teacher = findTeacher(teacherUsername);
        if (teacher == null || !teacher.active()) {
            return new Result.NotFound("activeTeacherProfile");
        }
        AssignmentView existing = findActiveAssignment(groupId, teacherUsername);
        if (existing != null) {
            return new Result.Success(existing, true);
        }
        UUID id = UUID.randomUUID();
        jdbcTemplate.update("""
            INSERT INTO teaching_assignment
              (id, group_id, teacher_username, active, assigned_by, assigned_at)
            VALUES (?, ?, ?, TRUE, ?, ?)
            ON CONFLICT DO NOTHING
            """, id, groupId, teacherUsername, actorUsername, Timestamp.from(Instant.now()));
        AssignmentView created = findActiveAssignment(groupId, teacherUsername);
        if (created == null) {
            return new Result.Conflict("ASSIGNMENT_COULD_NOT_BE_SAVED");
        }
        return new Result.Success(created, !id.equals(created.id()));
    }

    @Override
    @Transactional
    public Result enrollStudent(String actorUsername, UUID groupId, UUID studentId) {
        if (!isDirector(actorUsername)) {
            return new Result.Forbidden();
        }
        GroupView group = findActiveGroup(groupId);
        if (group == null) {
            return new Result.NotFound("academicGroup");
        }
        StudentOption student = findActiveStudent(studentId);
        if (student == null) {
            return new Result.NotFound("activeStudent");
        }
        EnrollmentView existing = findActiveEnrollment(groupId, studentId);
        if (existing != null) {
            return new Result.Success(existing, true);
        }
        UUID id = UUID.randomUUID();
        jdbcTemplate.update("""
            INSERT INTO student_enrollment
              (id, student_id, group_id, active, enrolled_by, enrolled_at)
            VALUES (?, ?, ?, TRUE, ?, ?)
            ON CONFLICT DO NOTHING
            """, id, studentId, groupId, actorUsername, Timestamp.from(Instant.now()));
        EnrollmentView created = findActiveEnrollment(groupId, studentId);
        if (created == null) {
            return new Result.Conflict("ENROLLMENT_COULD_NOT_BE_SAVED");
        }
        return new Result.Success(created, !id.equals(created.id()));
    }

    private boolean isDirector(String username) {
        return userRepository.findByUsernameAndRoleNameAndActiveTrue(username, "DIRECTOR")
            .isPresent();
    }

    private String normalized(String value) {
        return value == null ? null : value.trim();
    }

    private boolean invalid(String value, int maxLength) {
        return value == null || value.isBlank() || value.length() > maxLength;
    }

    private TeacherView findTeacher(String username) {
        List<TeacherView> rows = jdbcTemplate.query("""
            SELECT username, display_name, active, created_by, created_at, changed_by, changed_at
            FROM teacher_profile WHERE username = ?
            """, (result, row) -> teacherView(result), username);
        return rows.stream().findFirst().orElse(null);
    }

    private void recordTeacherHistory(String username, String displayName, boolean active,
        String actorUsername, Instant occurredAt) {
        jdbcTemplate.update("""
            INSERT INTO teacher_profile_history
              (id, teacher_username, display_name, active, changed_by, changed_at)
            VALUES (?, ?, ?, ?, ?, ?)
            """, UUID.randomUUID(), username, displayName, active, actorUsername,
            Timestamp.from(occurredAt));
    }

    private GroupView findGroup(String subject, String gradeLevel, String groupName,
        String period) {
        List<GroupView> rows = jdbcTemplate.query("""
            SELECT id, subject, grade_level, group_name, period, active, created_by, created_at
            FROM academic_group
            WHERE subject = ? AND grade_level = ? AND group_name = ? AND period = ?
            """, (result, row) -> groupView(result), subject, gradeLevel, groupName, period);
        return rows.stream().findFirst().orElse(null);
    }

    private GroupView findActiveGroup(UUID groupId) {
        List<GroupView> rows = jdbcTemplate.query("""
            SELECT id, subject, grade_level, group_name, period, active, created_by, created_at
            FROM academic_group WHERE id = ? AND active = TRUE
            """, (result, row) -> groupView(result), groupId);
        return rows.stream().findFirst().orElse(null);
    }

    private StudentOption findActiveStudent(UUID studentId) {
        List<StudentOption> rows = jdbcTemplate.query("""
            SELECT id, full_name, grade_level, group_name FROM student
            WHERE id = ? AND active = TRUE
            """, (result, row) -> new StudentOption(result.getObject("id", UUID.class),
                result.getString("full_name"), result.getString("grade_level"),
                result.getString("group_name")), studentId);
        return rows.stream().findFirst().orElse(null);
    }

    private AssignmentView findActiveAssignment(UUID groupId, String teacherUsername) {
        List<AssignmentView> rows = jdbcTemplate.query("""
            SELECT a.id, a.group_id, g.subject, g.grade_level, g.group_name, g.period,
                   t.username, t.display_name, a.active, a.assigned_by, a.assigned_at
            FROM teaching_assignment a JOIN academic_group g ON g.id = a.group_id
            JOIN teacher_profile t ON t.username = a.teacher_username
            WHERE a.group_id = ? AND a.teacher_username = ? AND a.active = TRUE
            """, (result, row) -> assignmentView(result), groupId, teacherUsername);
        return rows.stream().findFirst().orElse(null);
    }

    private EnrollmentView findActiveEnrollment(UUID groupId, UUID studentId) {
        List<EnrollmentView> rows = jdbcTemplate.query("""
            SELECT e.id, e.student_id, s.full_name, e.group_id, g.subject, g.grade_level,
                   g.group_name, g.period, e.active, e.enrolled_by, e.enrolled_at
            FROM student_enrollment e JOIN student s ON s.id = e.student_id
            JOIN academic_group g ON g.id = e.group_id
            WHERE e.group_id = ? AND e.student_id = ? AND e.active = TRUE
            """, (result, row) -> enrollmentView(result), groupId, studentId);
        return rows.stream().findFirst().orElse(null);
    }

    private TeacherView teacherView(ResultSet result) throws SQLException {
        return new TeacherView(result.getString("username"), result.getString("display_name"),
            result.getBoolean("active"), result.getString("created_by"),
            instant(result.getTimestamp("created_at")), result.getString("changed_by"),
            instant(result.getTimestamp("changed_at")));
    }

    private GroupView groupView(ResultSet result) throws SQLException {
        return new GroupView(result.getObject("id", UUID.class), result.getString("subject"),
            result.getString("grade_level"), result.getString("group_name"),
            result.getString("period"), result.getBoolean("active"),
            result.getString("created_by"), instant(result.getTimestamp("created_at")));
    }

    private AssignmentView assignmentView(ResultSet result) throws SQLException {
        return new AssignmentView(result.getObject("id", UUID.class),
            result.getObject("group_id", UUID.class), result.getString("subject"),
            result.getString("grade_level"), result.getString("group_name"),
            result.getString("period"), result.getString("username"),
            result.getString("display_name"), result.getBoolean("active"),
            result.getString("assigned_by"), instant(result.getTimestamp("assigned_at")));
    }

    private EnrollmentView enrollmentView(ResultSet result) throws SQLException {
        return new EnrollmentView(result.getObject("id", UUID.class),
            result.getObject("student_id", UUID.class), result.getString("full_name"),
            result.getObject("group_id", UUID.class), result.getString("subject"),
            result.getString("grade_level"), result.getString("group_name"),
            result.getString("period"), result.getBoolean("active"),
            result.getString("enrolled_by"), instant(result.getTimestamp("enrolled_at")));
    }

    private Instant instant(Timestamp timestamp) {
        return timestamp == null ? null : timestamp.toInstant();
    }
}
