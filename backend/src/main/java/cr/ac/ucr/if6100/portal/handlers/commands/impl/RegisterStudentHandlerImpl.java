package cr.ac.ucr.if6100.portal.handlers.commands.impl;

import cr.ac.ucr.if6100.portal.handlers.commands.RegisterStudentHandler;
import cr.ac.ucr.if6100.portal.persistence.entity.StudentEntity;
import cr.ac.ucr.if6100.portal.persistence.repository.PortalUserRepository;
import cr.ac.ucr.if6100.portal.persistence.repository.StudentRepository;
import java.time.Instant;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/** Valida el permiso de Dirección y persiste la ficha activa con su responsable y fecha. */
@Component
public class RegisterStudentHandlerImpl implements RegisterStudentHandler {
    private static final int PERSON_NAME_LENGTH = 160;
    private static final int GROUP_LABEL_LENGTH = 30;
    private final PortalUserRepository userRepository;
    private final StudentRepository studentRepository;

    public RegisterStudentHandlerImpl(PortalUserRepository userRepository,
        StudentRepository studentRepository) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    @Transactional
    public Result handle(Command command) {
        if (userRepository.findByUsernameAndRoleNameAndActiveTrue(
            command.actorUsername(), "DIRECTOR").isEmpty()) {
            return new Result.Forbidden();
        }
        String fullName = normalized(command.fullName());
        String gradeLevel = normalized(command.gradeLevel());
        String groupName = normalized(command.groupName());
        String teacherName = normalized(command.teacherName());
        if (invalid(fullName, PERSON_NAME_LENGTH)) {
            return new Result.InvalidField("fullName");
        }
        if (invalid(gradeLevel, GROUP_LABEL_LENGTH)) {
            return new Result.InvalidField("gradeLevel");
        }
        if (invalid(groupName, GROUP_LABEL_LENGTH)) {
            return new Result.InvalidField("groupName");
        }
        if (invalid(teacherName, PERSON_NAME_LENGTH)) {
            return new Result.InvalidField("teacherName");
        }
        Instant createdAt = Instant.now();
        StudentEntity student = new StudentEntity(fullName, gradeLevel, groupName,
            teacherName, command.actorUsername(), createdAt);
        return new Result.Created(toView(studentRepository.save(student)));
    }

    private String normalized(String value) {
        return value == null ? null : value.trim();
    }

    private boolean invalid(String value, int maxLength) {
        return value == null || value.isBlank() || value.length() > maxLength;
    }

    private StudentView toView(StudentEntity student) {
        return new StudentView(student.getId(), student.getFullName(), student.getGradeLevel(),
            student.getGroupName(), student.getTeacherName(), student.isActive(),
            student.getCreatedBy(), student.getCreatedAt());
    }
}
