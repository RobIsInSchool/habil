package com.radams.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * This class represents a role that a Habil user will have
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

    @Column(name="role_name")
    private String roleName;

    @ManyToOne
    @JoinColumn(name="role_id", referencedColumnName = "role_id", insertable=false, updatable=false)
    private User user;

    public UserRole() {

    }

    public UserRole(String roleName, User user) {
        this.roleName = roleName;
        this.user = user;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public User getUser() {
        return user;
    }

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
