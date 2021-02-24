package com.radams.persistence;

import com.radams.entity.User;
import com.radams.entity.UserRole;
import com.radams.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RoleDaoTest {

    UserRoleDao dao;

    @BeforeEach
    void setUp() {
        dao = new UserRoleDao();
        Database db = Database.getInstance();
        db.runSQL("cleandb.sql");
    }

    @Test
    void getAllUserRolesSuccess() {
        List<UserRole> userRoles = dao.getAllUserRoles();
        assertEquals(6, userRoles.size());
    }


    @Test
    void getByIdSuccess() {
        UserRole retrievedUserRole = dao.getUserRoleById(3);
        assertNotNull(retrievedUserRole);
        assertEquals("Barney", retrievedUserRole.getRoleName());
    }

    @Test
    void saveOrUpdateSuccess() {
        String newRoleName = "changedRole";
        UserDao userDao = new UserDao();
        User testUser = userDao.getUserById(1);
        UserRole testUserRole = new UserRole("unitTestser", testUser);
        testUser.addRole(testUserRole);
        int newId = dao.insert(testUserRole);
        UserRole userRoleToUpdate = dao.getUserRoleById(newId);
        userRoleToUpdate.setRoleName(newRoleName);
        dao.saveOrUpdate(userRoleToUpdate);
        UserRole retrievedUserRole = dao.getUserRoleById(1);
        assertEquals(retrievedUserRole.getRoleName(), newRoleName);
    }

    @Test
    void insertSuccess() {
        UserDao userDao = new UserDao();
        User testUser = userDao.getUserById(1);
        UserRole testUserRole = new UserRole("unitTestser", testUser);
        testUser.addRole(testUserRole);
        int newId = dao.insert(testUserRole);
        UserRole retrievedUserRole = dao.getUserRoleById(newId);
        assertEquals("unitTester", retrievedUserRole.getRoleName());
        assertEquals("Joe", retrievedUserRole.getUser().getFirstName());
    }


    @Test
    void deleteSuccess() {
        UserRole testUserRole = new UserRole();
        dao.insert(testUserRole);
        List<UserRole> userRoles = dao.getAllUserRoles();
        assertEquals(7, userRoles.size());
        dao.delete(testUserRole);
        userRoles = dao.getAllUserRoles();
        assertEquals(6, userRoles.size());
    }
}