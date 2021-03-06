package com.radams.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * This class represents a Habil user
 *
 * @author Robert Adams
 */
@Entity(name="User")
@Table(name="users")
public class User implements Serializable {

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
    private Integer numLessonsTaken;

    @Column(name = "lessons_taught")
    private Integer numLessonsTaught;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "date_created")
    private Date dateCreated;

    @Column(name = "date_deleted")
    private Date dateDeleted;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<UserRole> userRoles = new HashSet<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Lesson> lessonsTaken = new HashSet<>();

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Lesson> lessonsTaught = new HashSet<>();

    @ManyToMany(mappedBy = "users")
    @JoinTable(
            name = "user_skills_has",
            joinColumns = { @JoinColumn(name = "id") },
            inverseJoinColumns = { @JoinColumn(name = "id") }
    )
    private Set<Skill> skillsHas = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "user_skills_wants",
            joinColumns = { @JoinColumn(name = "id") },
            inverseJoinColumns = { @JoinColumn(name = "id") }
    )
    private Set<Skill> skillsWants = new HashSet<>();


    /**
     * Instantiates a new User.
     */
    public User() {

    }

    /**
     * Constructor for User class
     *
     * @param firstName   user first name
     * @param lastName    user last name
     * @param username    user's username
     * @param email       user email
     * @param password    user password
     * @param isActive    indicates if user is still active
     * @param dateCreated the date created
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

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets lessons taken.
     *
     * @return the lessons taken
     */
    public Integer getNumLessonsTaken() {
        return numLessonsTaken;
    }

    /**
     * Sets lessons taken.
     *
     * @param numLessonsTaken the lessons taken
     */
    public void setNumLessonsTaken(Integer numLessonsTaken) {
        this.numLessonsTaken = numLessonsTaken;
    }

    /**
     * Gets lessons taught.
     *
     * @return the lessons taught
     */
    public Integer getNumLessonsTaught() {
        return numLessonsTaught;
    }

    /**
     * Sets lessons taught.
     *
     * @param numLessonsTaught the lessons taught
     */
    public void setNumLessonsTaught(Integer numLessonsTaught) {
        this.numLessonsTaught = numLessonsTaught;
    }

    /**
     * Is active boolean.
     *
     * @return the boolean
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Sets active.
     *
     * @param active the active
     */
    public void setActive(boolean active) {
        isActive = active;
    }

    /**
     * Gets date created.
     *
     * @return the date created
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * Sets date created.
     *
     * @param dateCreated the date created
     */
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * Gets date deleted.
     *
     * @return the date deleted
     */
    public Date getDateDeleted() {
        return dateDeleted;
    }

    /**
     * Sets date deleted.
     *
     * @param dateDeleted the date deleted
     */
    public void setDateDeleted(Date dateDeleted) {
        this.dateDeleted = dateDeleted;
    }

    /**
     * Gets user roles.
     *
     * @return the user roles
     */
    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    /**
     * Sets user roles.
     *
     * @param userRoles the user roles
     */
    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    /**
     * Add role.
     *
     * @param role the role
     */
    public void addRole(UserRole role) {
        userRoles.add(role);
        role.setUser(this);
    }

    /**
     * Remove role.
     *
     * @param role the role
     */
    public void removeRole(UserRole role) {
        userRoles.remove(role);
        role.setUser(null);
    }

    public void addLessonTaken(Lesson lesson) {
        lessonsTaken.add(lesson);
    }

    public void addLessonTaught(Lesson lesson) {
        lessonsTaught.add(lesson);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", lessonsTaken=" + numLessonsTaken +
                ", lessonsTaught=" + numLessonsTaught +
                ", isActive=" + isActive +
                ", dateCreated=" + dateCreated +
                ", dateDeleted=" + dateDeleted +
                '}';
    }


}
