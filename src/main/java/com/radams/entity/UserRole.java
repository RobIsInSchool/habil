package com.radams.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * This class represents a role that a Habil user will have
 *
 * @author Robert Adams
 */
@Entity(name="UserRole")
@Table(name="roles")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name="id")
    private int roleId;

    @Column(name = "role_name")
    private String roleName;

    @ManyToOne
    @JoinColumn(name = "user_name", referencedColumnName = "user_name", nullable = false)
    private User user;

    /**
     * Instantiates a new User role.
     */
    public UserRole() {
    }

    /**
     * Instantiates a new User role.
     *
     * @param roleName the role name
     * @param user     the user
     */
    public UserRole(String roleName, User user) {
        this.roleName = roleName;
        this.user = user;
    }

    /**
     * Gets role id.
     *
     * @return the role id
     */
    public int getRoleId() {
        return roleId;
    }

    /**
     * Sets role id.
     *
     * @param roleId the role id
     */
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    /**
     * Gets role name.
     *
     * @return the role name
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Sets role name.
     *
     * @param roleName the role name
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", user=" + user +
                '}';
    }

}
