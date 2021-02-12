package com.radams.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class User {
    private final Logger logger = LogManager.getLogger(this.getClass());

    private int userId;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private int lessonsTaken;
    private int lessonsTaught;
    private boolean isActive;
    private int role_id;

    public User() {

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

    public int getLessonsTaken() {
        return lessonsTaken;
    }

    public void setLessonsTaken(int lessonsTaken) {
        this.lessonsTaken = lessonsTaken;
    }

    public int getLessonsTaught() {
        return lessonsTaught;
    }

    public void setLessonsTaught(int lessonsTaught) {
        this.lessonsTaught = lessonsTaught;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
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
                ", lessonsTaken=" + lessonsTaken +
                ", lessonsTaught=" + lessonsTaught +
                ", isActive=" + isActive +
                ", role_id=" + role_id +
                '}';
    }
}
