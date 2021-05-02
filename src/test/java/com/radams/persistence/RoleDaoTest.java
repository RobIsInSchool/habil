package com.radams.persistence;

import com.radams.entity.User;
import com.radams.entity.UserRole;
import com.radams.test.util.Database;
import net.bytebuddy.description.type.TypeList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoleDaoTest {

    GenericDao userRoleDao;
    GenericDao userDao;

    @BeforeEach
    void setUp() {
        userRoleDao = new GenericDao(UserRole.class);
        userDao = new GenericDao(User.class);
        Database db = Database.getInstance();
        db.runSQL("cleandb.sql");
    }

    private User getTestUserWithIdOf1() {
        GenericDao<User> userDao = new GenericDao<>(User.class);
        User testUser = userDao.getById(1);
        return testUser;
    }

    @Test
    void getAllUserRolesSuccess() {
        List<UserRole> userRoles = userRoleDao.getAll();
        assertEquals(6, userRoles.size());
    }


    @Test
    void getByIdSuccess() {
        UserRole retrievedUserRole = (UserRole) userRoleDao.getById(3);
        assertNotNull(retrievedUserRole);
        assertEquals("admin", retrievedUserRole.getRoleName());
    }

    @Test
    void saveOrUpdateSuccess() {
        String newRoleName = "changedRole";
        User testUser = getTestUserWithIdOf1();
        UserRole testUserRole = new UserRole("unitTestser", testUser);
        testUser.addRole(testUserRole);
        int newId = userRoleDao.insert(testUserRole);
        UserRole userRoleToUpdate = (UserRole) userRoleDao.getById(newId);
        userRoleToUpdate.setRoleName(newRoleName);
        userRoleDao.saveOrUpdate(userRoleToUpdate);
        UserRole retrievedUserRole = (UserRole) userRoleDao.getById(newId);
        assertEquals(newRoleName, retrievedUserRole.getRoleName());
    }

    @Test
    void insertSuccess() {
        User testUser = getTestUserWithIdOf1();
        UserRole testUserRole = new UserRole("unitTester", testUser);
        testUser.addRole(testUserRole);
        int newId = userRoleDao.insert(testUserRole);
        UserRole retrievedUserRole = (UserRole) userRoleDao.getById(newId);
        assertEquals("unitTester", retrievedUserRole.getRoleName());
        assertEquals("Joe", retrievedUserRole.getUser().getFirstName());
    }


    @Test
    void deleteSuccess() {
        User testUser = getTestUserWithIdOf1();
        UserRole testUserRole = new UserRole("unitTester", testUser);
        testUser.addRole(testUserRole);
        userRoleDao.insert(testUserRole);
        List<UserRole> userRoles = userRoleDao.getAll();
        assertEquals(7, userRoles.size());
        userRoleDao.delete(testUserRole);
        userRoles = userRoleDao.getAll();
        assertEquals(6, userRoles.size());
    }

    @Test
    void findByAdmin() {
        String searchTerm = "admin";
        String searchProperty = "roleName";
        List results = userRoleDao.findByPropertyEqual(searchProperty, searchTerm);
        assertEquals(results.size(), 2);

        UserRole resultOne = (UserRole)results.get(0);
        assertEquals(resultOne.getRoleName(), searchTerm);

        UserRole resultTwo = (UserRole)results.get(1);
        assertEquals(resultTwo.getRoleName(), searchTerm);
    }
}