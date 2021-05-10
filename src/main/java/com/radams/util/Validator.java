package com.radams.util;
import com.radams.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.radams.entity.User;

import java.util.ArrayList;
import java.util.List;

public class Validator {

    private GenericDao userDao = new GenericDao(User.class);
    private final Logger logger = LogManager.getLogger(this.getClass());

    private boolean usernameExists;
    private boolean emailExists;
    private List<User> users;
    private List<String> usernames;
    private List<String> emails;

    public Validator() {
        this.usernameExists = false;
        this.emailExists = false;
        this.users = new ArrayList<>();
        this.usernames = new ArrayList<>();
        this.emails = new ArrayList<>();
        this.getUsers();
        this.getUsernamesAndEmails();
    }

    public boolean isUsernameExists() {
        return usernameExists;
    }

    public void setUsernameExists(boolean usernameExists) {
        this.usernameExists = usernameExists;
    }

    public boolean isEmailExists() {
        return emailExists;
    }

    public void setEmailExists(boolean emailExists) {
        this.emailExists = emailExists;
    }

    private void getUsers() {
        users = (List<User>) userDao.getAll();
    }

    private void getUsernamesAndEmails() {
        for (User user : users) {
            usernames.add(user.getUsername());
            emails.add(user.getEmail());
        }
    }


    public void validate(String username, String email) {
        if (usernames.contains(username)) {
            usernameExists = true;
        }
        if (emails.contains(email)) {
            emailExists = true;
        }
    }
}
