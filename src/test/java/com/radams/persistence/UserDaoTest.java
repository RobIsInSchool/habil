package com.radams.persistence;

import com.radams.entity.User;
import com.radams.entity.UserRole;
import com.radams.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    UserDao dao;
    User testUser;

    @BeforeEach
    void setUp() {
        dao = new UserDao();
        Database db = Database.getInstance();
        db.runSQL("cleandb.sql");
        testUser = new User("test", "user", "testuser", "email", "password", true, Date.valueOf(LocalDate.now()), 1);
    }

    @Test
    void getAllUsersSuccess() {
        List<User> users = dao.getAllUsers();
        assertEquals(6, users.size());
    }

    @Test
    void getUsersByLastNameSuccess() {
        List<User> users = dao.getUsersByLastName("Coyne");
        assertEquals(1, users.size());
    }

    @Test
    void getByIdSuccess() {
        User retrievedUser = dao.getUserById(3);
        assertNotNull(retrievedUser);
        assertEquals("Barney", retrievedUser.getFirstName());
    }

    @Test
    void saveOrUpdateSuccess() {
        String newLastName = "changed";
        User userToUpdate = dao.getUserById(1);
        userToUpdate.setLastName(newLastName);
        dao.saveOrUpdate(userToUpdate);
        User retrievedUser = dao.getUserById(1);
        assertEquals(retrievedUser.getLastName(), newLastName);
    }

    @Test
    void insertSuccess() {
        int newId = dao.insert(testUser);
        User retrievedUser = dao.getUserById(newId);
        assertEquals(newId, retrievedUser.getUserId());
    }

    @Test
    void insertWithRoleSuccess() {
        String roleDescription = "user";
        UserRole role = new UserRole(roleDescription, testUser);
        int newId = dao.insert(testUser);
        User retrievedUser = dao.getUserById(newId);
        assertEquals(newId, retrievedUser.getUserId());
    }

    @Test
    void deleteSuccess() {
        insertSuccess();
        List<User> users = dao.getAllUsers();
        assertEquals(7, users.size());
        dao.delete(testUser);
        users = dao.getAllUsers();
        assertEquals(6, users.size());
    }
}