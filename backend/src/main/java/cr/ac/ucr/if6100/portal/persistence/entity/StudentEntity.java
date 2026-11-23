package cr.ac.ucr.if6100.portal.persistence.entity;

import java.time.Instant;
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

    @Column(name = "created_by", nullable = false, length = 80)
    private String createdBy;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    protected StudentEntity() { }

    public StudentEntity(String fullName, String gradeLevel, String groupName,
        String teacherName, String createdBy, Instant createdAt) {
        this.id = UUID.randomUUID();
        this.fullName = fullName;
        this.gradeLevel = gradeLevel;
        this.groupName = groupName;
        this.teacherName = teacherName;
        this.active = true;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }

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

    public String getCreatedBy() {
        return createdBy;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
