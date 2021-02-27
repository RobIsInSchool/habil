package com.radams.persistence;

import com.radams.entity.User;
import com.radams.entity.UserRole;
import com.radams.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    GenericDao genericDao;

    @BeforeEach
    void setUp() {
        genericDao = new GenericDao(User.class);
        Database db = Database.getInstance();
        db.runSQL("cleandb.sql");
    }

    @Test
    void getAllUsersSuccess() {
        List<User> users = genericDao.getAll();
        assertEquals(6, users.size());
    }


    @Test
    void getByIdSuccess() {
        User retrievedUser = (User)genericDao.getById(2);
        assertNotNull(retrievedUser);
        assertEquals("Hensen", retrievedUser.getLastName());
    }

    @Test
    void saveOrUpdateSuccess() {
        String newLastName = "changed";
        User userToUpdate = (User)genericDao.getById(1);
        userToUpdate.setLastName(newLastName);
        genericDao.saveOrUpdate(userToUpdate);
        User retrievedUser = (User)genericDao.getById(1);
        assertEquals(retrievedUser.getLastName(), newLastName);
    }


    @Test
    void insertSuccess() {
        User testUser = new User("test", "user", "testuser", "email", "password", true, Date.valueOf(LocalDate.now()));
        int newId = genericDao.insert(testUser);
        User retrievedUser = (User)genericDao.getById(newId);
        assertEquals(newId, retrievedUser.getUserId());
    }

    @Test
    void insertWithRoleSuccess() {
        User testUser = new User("test", "user", "testuser", "email", "password", true, Date.valueOf(LocalDate.now()));
        UserRole role = new UserRole("testRole", testUser);
        testUser.addRole(role);
        int id = genericDao.insert(testUser);
        User retrievedUser = (User)genericDao.getById(id);
        assertEquals(id, retrievedUser.getUserId());
        assertEquals(1, retrievedUser.getUserRoles().size());
    }

    @Test
    void deleteSuccess() {
        User testUser = new User("test", "user", "testuser", "email", "password", true, Date.valueOf(LocalDate.now()));
        genericDao.insert(testUser);
        List<User> users = genericDao.getAll();
        assertEquals(7, users.size());
        genericDao.delete(testUser);
        users = genericDao.getAll();
        assertEquals(6, users.size());
    }

    @Test
    void findByLastNamePropertySuccess() throws Exception {
         String searchTerm = "Coyne";
         String searchProperty = "lastName";
         List results = genericDao.findByPropertyEqual(searchProperty, searchTerm);
         assertTrue(results.size() > 0);
         User result = (User)results.get(0);
         assertEquals(result.getLastName(), searchTerm);
    }


}