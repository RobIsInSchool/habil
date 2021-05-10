package com.radams.entity;

import com.radams.entity.User;
import com.radams.entity.UserRole;
import com.radams.persistence.GenericDao;
import com.radams.test.util.Database;
import net.bytebuddy.description.type.TypeList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TestUserMatcher {
    GenericDao userDao;
    GenericDao skillDao;

    @BeforeEach
    void setUp() {
        userDao = new GenericDao(User.class);
        skillDao = new GenericDao(Skill.class);
        Database db = Database.getInstance();
        db.runSQL("cleandb.sql");
        createUsers();
    }

    @Test
    void TestSuccessfulMatch() {
        List<User> foundUsers = (List<User>) userDao.findByPropertyEqual("lastName", "one");
        User userToTest = foundUsers.get(0);
        UserMatcher matcher = new UserMatcher(userToTest);
        List<User> matchedUsers = new ArrayList<>();
        try {
            for(User matchedUser : matcher.getMatchedUsers()) {
                matchedUsers.add(matchedUser);
            }
        } catch (Exception e) {

        }
        assertEquals(1, matchedUsers.size());
        User matchedUser = matchedUsers.get(0);
        assertEquals(matchedUser.getLastName(), "two");
    }

    private void createUsers() {

        String uOneFirstName = "user";
        String uOneLastName = "one";
        String uOneUsername = "userone";
        String uOneEmail = "user@one.com";
        String uOnePass = "uonepass";
        String uOneZip = "53705";

        String uTwoFirstName = "user";
        String uTwoLastName = "two";
        String uTwoUsername = "usertwo";
        String uTwoEmail = "user@two.com";
        String uTwoPass = "utwopass";
        String uTwoZip = "53703";

        String uThreeFirstName = "user";
        String uThreeLastName = "three";
        String uThreeUsername = "userthree";
        String uThreeEmail = "user@three.com";
        String uThreePass = "uthreepass";
        String uThreeZip = "53705";

        String uFourFirstName = "user";
        String uFourLastName = "four";
        String uFourUsername = "userfour";
        String uFourEmail = "user@four.com";
        String uFourPass = "ufourpass";
        String uFourZip = "94043";

        User userOne = new User(uOneFirstName, uOneLastName, uOneUsername, uOneEmail, uOnePass, true, uOneZip, Date.valueOf(LocalDate.now()));
        User userTwo = new User(uTwoFirstName, uTwoLastName, uTwoUsername, uTwoEmail, uTwoPass, true, uTwoZip, Date.valueOf(LocalDate.now()));
        User userThree = new User(uThreeFirstName, uThreeLastName, uThreeUsername, uThreeEmail, uThreePass, true, uThreeZip, Date.valueOf(LocalDate.now()));
        User userFour = new User(uFourFirstName, uFourLastName, uFourUsername, uFourEmail, uFourPass, true, uFourZip, Date.valueOf(LocalDate.now()));

        Skill auto = (Skill) skillDao.getById(2);
        Skill fishing = (Skill) skillDao.getById(1);
        Skill surfing = (Skill) skillDao.getById(3);

        userOne.addSkillHas(auto);
        userOne.addSkillWants(fishing);

        userTwo.addSkillHas(fishing);
        userTwo.addSkillWants(auto);

        userThree.addSkillHas(surfing);
        userThree.addSkillWants(auto);

        userFour.addSkillHas(fishing);
        userFour.addSkillWants(auto);

        //ONLY users one and two should match

        userDao.insert(userOne);
        userDao.insert(userTwo);
        userDao.insert(userThree);
        userDao.insert(userFour);
    }
}
