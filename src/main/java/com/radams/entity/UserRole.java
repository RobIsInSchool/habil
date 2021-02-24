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

    @Column(name = "user_name")
    private String userName;

    @Column(name = "role_name")
    private String roleName;
}
