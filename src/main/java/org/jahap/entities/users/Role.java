package org.jahap.entities.users;

import jakarta.persistence.*;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "role_name", length = 100)
    private String roleName;

    @Column(name = "rolen_description", length = 500)
    private String rolenDescription;

    public String getRolenDescription() {
        return rolenDescription;
    }

    public void setRolenDescription(String rolenDescription) {
        this.rolenDescription = rolenDescription;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}