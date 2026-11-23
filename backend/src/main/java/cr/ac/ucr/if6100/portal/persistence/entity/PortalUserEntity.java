package cr.ac.ucr.if6100.portal.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "portal_user")
public class PortalUserEntity {
    private static final int USERNAME_LENGTH = 80;
    private static final int DISPLAY_NAME_LENGTH = 160;
    private static final int ROLE_LENGTH = 30;

    @Id
    @Column(length = USERNAME_LENGTH)
    private String username;

    @Column(name = "display_name", nullable = false, length = DISPLAY_NAME_LENGTH)
    private String displayName;

    @Column(name = "role_name", nullable = false, length = ROLE_LENGTH)
    private String roleName;

    @Column(nullable = false)
    private boolean active;

    protected PortalUserEntity() { }

    public String getUsername() {
        return username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getRoleName() {
        return roleName;
    }

    public boolean isActive() {
        return active;
    }
}
