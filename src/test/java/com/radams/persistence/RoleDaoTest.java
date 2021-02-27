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

class RoleDaoTest {

    UserRoleDao dao;
    GenericDao genericDao;

    @BeforeEach
    void setUp() {
        genericDao = new GenericDao(UserRole.class);
        dao = new UserRoleDao();
        Database db = Database.getInstance();
        db.runSQL("cleandb.sql");
    }

    @Test
    void getAllUserRolesSuccess() {
        List<UserRole> userRoles = genericDao.getAll();
        assertEquals(6, userRoles.size());
    }


    @Test
    void getByIdSuccess() {
        UserRole retrievedUserRole = (UserRole)genericDao.getById(3);
        assertNotNull(retrievedUserRole);
        assertEquals("admin", retrievedUserRole.getRoleName());
    }

    @Test
    void saveOrUpdateSuccess() {
        String newRoleName = "changedRole";
        GenericDao userDao = new GenericDao(User.class);
        User testUser = (User)userDao.getById(1);
        UserRole testUserRole = new UserRole("unitTestser", testUser);
        testUser.addRole(testUserRole);
        int newId = genericDao.insert(testUserRole);
        UserRole userRoleToUpdate = (UserRole)genericDao.getById(newId);
        userRoleToUpdate.setRoleName(newRoleName);
        genericDao.saveOrUpdate(userRoleToUpdate);
        UserRole retrievedUserRole = (UserRole)genericDao.getById(newId);
        assertEquals(newRoleName, retrievedUserRole.getRoleName());
    }

    @Test
    void insertSuccess() {
        GenericDao userDao = new GenericDao(User.class);
        User testUser = (User)userDao.getById(1);
        UserRole testUserRole = new UserRole("unitTester", testUser);
        testUser.addRole(testUserRole);
        int newId = genericDao.insert(testUserRole);
        UserRole retrievedUserRole = (UserRole)genericDao.getById(newId);
        assertEquals("unitTester", retrievedUserRole.getRoleName());
        assertEquals("Joe", retrievedUserRole.getUser().getFirstName());
    }


    @Test
    void deleteSuccess() {
        GenericDao userDao = new GenericDao(User.class);
        User testUser = (User)userDao.getById(1);
        UserRole testUserRole = new UserRole("unitTester", testUser);
        testUser.addRole(testUserRole);
        genericDao.insert(testUserRole);
        List<UserRole> userRoles = genericDao.getAll();
        assertEquals(7, userRoles.size());
        genericDao.delete(testUserRole);
        userRoles = genericDao.getAll();
        assertEquals(6, userRoles.size());
    }

    @Test
    void findByAdmin() {
        String searchTerm = "admin";
        String searchProperty = "roleName";
        List results = genericDao.findByPropertyEqual(searchProperty, searchTerm);
        assertEquals(results.size(), 2);

        UserRole resultOne = (UserRole)results.get(0);
        assertEquals(resultOne.getRoleName(), searchTerm);

        UserRole resultTwo = (UserRole)results.get(1);
        assertEquals(resultTwo.getRoleName(), searchTerm);
    }
}