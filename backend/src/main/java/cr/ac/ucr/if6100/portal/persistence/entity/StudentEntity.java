package cr.ac.ucr.if6100.portal.persistence.entity;

import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "student")
public class StudentEntity {
    private static final int PERSON_NAME_LENGTH = 160;
    private static final int GROUP_LABEL_LENGTH = 30;

    @Id
    private UUID id;

    @Column(name = "full_name", nullable = false, length = PERSON_NAME_LENGTH)
    private String fullName;

    @Column(name = "grade_level", nullable = false, length = GROUP_LABEL_LENGTH)
    private String gradeLevel;

    @Column(name = "group_name", nullable = false, length = GROUP_LABEL_LENGTH)
    private String groupName;

    @Column(name = "teacher_name", nullable = false, length = PERSON_NAME_LENGTH)
    private String teacherName;

    @Column(nullable = false)
    private boolean active;

    protected StudentEntity() { }

    public UUID getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getGradeLevel() {
        return gradeLevel;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public boolean isActive() {
        return active;
    }
}
