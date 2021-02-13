package com.radams.persistence;

import com.radams.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    UserDao dao;

    @BeforeEach
    void setUp() {
        dao = new UserDao();
    }

    @Test
    void getAllUsersSuccess() {
        List<User> users = dao.getAllUsers();
        assertEquals(2, users.size());
    }

    @Test
    void getUsersByLastNameSuccess() {
        List<User> users = dao.getUsersByLastName("P");
        assertEquals(1, users.size());
    }

    @Test
    void getByIdSuccess() {
        User retrievedUser = dao.getUserById(2);
        assertNotNull(retrievedUser);
        assertEquals("Second", retrievedUser.getFirstName());
    }

    @Test
    void saveOrUpdateSuccess() {
    }

    @Test
    void insertSuccess() {
    }

    @Test
    void deleteSuccess() {
    }
}