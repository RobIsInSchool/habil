package com.radams.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Iterator;
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

    @Column(name = "zip")
    private String zip;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<UserRole> userRoles = new HashSet<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Lesson> lessonsTaken = new HashSet<>();

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Lesson> lessonsTaught = new HashSet<>();

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name = "user_skills_has",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "skill_id") }
    )
    private Set<Skill> skillsHas = new HashSet<>();

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name = "user_skills_wants",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "skill_id") }
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
     * @param zip         the zip
     * @param dateCreated the date created
     */
    public User(String firstName, String lastName, String username, String email, String password, boolean isActive, String zip, Date dateCreated) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.isActive = isActive;
        this.zip = zip;
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
     * Gets zip.
     *
     * @return the zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * Sets zip.
     *
     * @param zip the zip
     */
    public void setZip(String zip) {
        this.zip = zip;
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
     * Gets skills has.
     *
     * @return the skills has
     */
    public Set<Skill> getSkillsHas() {
        return skillsHas;
    }

    /**
     * Sets skills has.
     *
     * @param skillsHas the skills has
     */
    public void setSkillsHas(Set<Skill> skillsHas) {
        this.skillsHas = skillsHas;
    }

    /**
     * Gets skills wants.
     *
     * @return the skills wants
     */
    public Set<Skill> getSkillsWants() {
        return skillsWants;
    }

    /**
     * Sets skills wants.
     *
     * @param skillsWants the skills wants
     */
    public void setSkillsWants(Set<Skill> skillsWants) {
        this.skillsWants = skillsWants;
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

    /**
     * Add lesson taken.
     *
     * @param lesson the lesson
     */
    public void addLessonTaken(Lesson lesson) {
        lessonsTaken.add(lesson);
    }

    /**
     * Add lesson taught.
     *
     * @param lesson the lesson
     */
    public void addLessonTaught(Lesson lesson) {
        lessonsTaught.add(lesson);
    }

    /**
     * Add skill has.
     *
     * @param skill the skill
     */
    public void addSkillHas(Skill skill) {
        skillsHas.add(skill);
        skill.addUsersHas(this);
    }

    /**
     * Remove skill has.
     *
     * @param skill the skill
     */
    public void removeSkillHas(Skill skill) {
        Iterator<Skill> iterator = skillsHas.iterator();
        while(iterator.hasNext()) {
            String skillName = iterator.next().getSkillName();
            if(skillName.equals(skill.getSkillName())) {
                iterator.remove();
            }
        }
    }

    /**
     * Add skill wants.
     *
     * @param skill the skill
     */
    public void addSkillWants(Skill skill) {
        skillsWants.add(skill);
        skill.addUsersWants(this);
    }

    /**
     * Remove skill wants.
     *
     * @param skill the skill
     */
    public void removeSkillWants(Skill skill) {
        Iterator<Skill> iterator = skillsWants.iterator();
        while(iterator.hasNext()) {
            String skillName = iterator.next().getSkillName();
            if(skillName.equals(skill.getSkillName())) {
                iterator.remove();
            }
        }
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
                ", zip=" + zip +
                '}';
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    //Muchas gracias a https://medium.com/codelog/overriding-hashcode-method-effective-java-notes-723c1fedf51c para su ayuda
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }
        User user = (User) obj;
        return this.userId == (user.userId) && this.username.equals(user.username);
    }

}
