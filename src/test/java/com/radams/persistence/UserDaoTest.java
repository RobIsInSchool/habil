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

    GenericDao userDao;

    @BeforeEach
    void setUp() {
        userDao = new GenericDao(User.class);
        Database db = Database.getInstance();
        db.runSQL("cleandb.sql");
    }

    private User getTestUser() {
        User testUser = new User("test", "user", "testuser", "email", "password", true, "53705", Date.valueOf(LocalDate.now()));
        return testUser;
    }

    @Test
    void getAllUsersSuccess() {
        List<User> users = userDao.getAll();
        assertEquals(6, users.size());
    }


    @Test
    void getByIdSuccess() {
        User retrievedUser = (User) userDao.getById(2);
        assertNotNull(retrievedUser);
        assertEquals("Hensen", retrievedUser.getLastName());
    }

    @Test
    void saveOrUpdateSuccess() {
        String newLastName = "changed";
        User userToUpdate = (User) userDao.getById(1);
        userToUpdate.setLastName(newLastName);
        userDao.saveOrUpdate(userToUpdate);
        User retrievedUser = (User) userDao.getById(1);
        assertEquals(retrievedUser.getLastName(), newLastName);
    }


    @Test
    void insertSuccess() {
        User testUser = getTestUser();
        int newId = userDao.insert(testUser);
        User retrievedUser = (User) userDao.getById(newId);
        assertEquals(newId, retrievedUser.getUserId());
    }

    @Test
    void insertWithRoleSuccess() {
        User testUser = getTestUser();
        UserRole role = new UserRole("testRole", testUser);
        testUser.addRole(role);
        int id = userDao.insert(testUser);
        User retrievedUser = (User) userDao.getById(id);
        assertEquals(id, retrievedUser.getUserId());
        assertEquals(1, retrievedUser.getUserRoles().size());
    }

    @Test
    void deleteSuccess() {
        User testUser = getTestUser();
        userDao.insert(testUser);
        List<User> users = userDao.getAll();
        assertEquals(7, users.size());
        userDao.delete(testUser);
        users = userDao.getAll();
        assertEquals(6, users.size());
    }

    @Test
    void findByLastNamePropertySuccess() throws Exception {
         String searchTerm = "Coyne";
         String searchProperty = "lastName";
         List results = userDao.findByPropertyEqual(searchProperty, searchTerm);
         assertTrue(results.size() > 0);
         User result = (User)results.get(0);
         assertEquals(result.getLastName(), searchTerm);
    }


}