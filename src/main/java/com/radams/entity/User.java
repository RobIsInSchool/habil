package com.radams.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.ejb.Local;
import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * This class represents a Habil user
 * @author Robert Adams
 *
 */
@Entity(name="User")
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id")
    private int userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "user_name")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "lessons_taken")
    private Integer lessonsTaken;

    @Column(name = "lessons_taught")
    private Integer lessonsTaught;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "date_created")
    private Date dateCreated;

    @Column(name = "date_deleted")
    private Date dateDeleted;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
//    private Set<UserRole> userRoles = new HashSet<>();


    public User() {

    }

    /**
     * Constructor for User class
     * @param firstName user first name
     * @param lastName user last name
     * @param username user's username
     * @param email user email
     * @param password user password
     * @param isActive indicates if user is still active
     */
    public User(String firstName, String lastName, String username, String email, String password, boolean isActive, Date dateCreated) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.isActive = isActive;
        this.dateCreated = dateCreated;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getLessonsTaken() {
        return lessonsTaken;
    }

    public void setLessonsTaken(Integer lessonsTaken) {
        this.lessonsTaken = lessonsTaken;
    }

    public Integer getLessonsTaught() {
        return lessonsTaught;
    }

    public void setLessonsTaught(Integer lessonsTaught) {
        this.lessonsTaught = lessonsTaught;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateDeleted() {
        return dateDeleted;
    }

    public void setDateDeleted(Date dateDeleted) {
        this.dateDeleted = dateDeleted;
    }


    @Override
    public String toString() {
        return "User{" +
                ", userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", lessonsTaken=" + lessonsTaken +
                ", lessonsTaught=" + lessonsTaught +
                ", isActive=" + isActive +
                ", dateCreated=" + dateCreated +
                ", dateDeleted=" + dateDeleted +
                '}';
    }


}
