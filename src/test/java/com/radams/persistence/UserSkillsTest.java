package com.radams.persistence;

import com.radams.entity.Lesson;
import com.radams.entity.Skill;
import com.radams.entity.User;
import com.radams.test.util.Database;
import net.bytebuddy.description.type.TypeList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class UserSkillsTest {
    GenericDao skillDao;
    GenericDao userDao;

    @BeforeEach
    void setUp() {
        skillDao = new GenericDao(Skill.class);
        userDao = new GenericDao(User.class);
        Database db = Database.getInstance();
        db.runSQL("cleandb.sql");
    }

    @Test
    void getSkillById() {
        Skill fishing = (Skill)skillDao.getById(1);
        assertNotNull(fishing);
        assertEquals("fishing", fishing.getSkillName());
    }

    @Test
    void insertSkillSuccess() {
        String skillName = "Auto Maintenance";
        Skill autoMaintenance = new Skill(skillName);
        int newSkillId = skillDao.insert(autoMaintenance);
        assertNotNull(newSkillId);
        Skill retrievedSkill = (Skill)skillDao.getById(newSkillId);
        assertEquals(skillName, retrievedSkill.getSkillName());
    }

    @Test
    void updateUserJoeCoyneWithNewSkillHas() {
        User joeCoyne = (User)userDao.getById(1);
        Skill fishingSkill = (Skill)skillDao.getById(1);
        joeCoyne.addSkillHas(fishingSkill);
        userDao.saveOrUpdate(joeCoyne);
        User updatedJoeCoyne = (User)userDao.getById(1);
        assertEquals(1, updatedJoeCoyne.getSkillsHas().size());
    }

    @Test
    void updateUserJoeCoyneWithRemovedSkillHas() {
        User joeCoyne = (User)userDao.getById(1);
        Skill fishingSkill = (Skill)skillDao.getById(1);
        joeCoyne.addSkillHas(fishingSkill);
        userDao.saveOrUpdate(joeCoyne);
        User updatedJoeCoyneWith = (User)userDao.getById(1);
        updatedJoeCoyneWith.removeSkillHas(fishingSkill);
        userDao.saveOrUpdate(updatedJoeCoyneWith);
        User updatedJoeCoyneWithout = (User)userDao.getById(1);
        assertEquals(0, updatedJoeCoyneWithout.getSkillsHas().size());

    }

    @Test
    void updateUserJoeCoyneWithNewSkillWants() {
        User joeCoyne = (User)userDao.getById(1);
        Skill autoMaintenance = (Skill)skillDao.getById(2);
        joeCoyne.addSkillsWants(autoMaintenance);
        userDao.saveOrUpdate(joeCoyne);
        User updatedJoeCoyne = (User)userDao.getById(1);
        assertEquals(1, updatedJoeCoyne.getSkillsWants().size());
    }

    @Test
    void updateUserJoeCoyneWithRemovedSkillWants() {
        User joeCoyne = (User)userDao.getById(1);
        Skill autoMaintenance = (Skill)skillDao.getById(1);
        joeCoyne.addSkillsWants(autoMaintenance);
        userDao.saveOrUpdate(joeCoyne);
        User updatedJoeCoyneWith = (User)userDao.getById(1);
        updatedJoeCoyneWith.removeSkillWants(autoMaintenance);
        userDao.saveOrUpdate(updatedJoeCoyneWith);
        User updatedJoeCoyneWithout = (User)userDao.getById(1);
        assertEquals(0, updatedJoeCoyneWithout.getSkillsWants().size());

    }

    @Test
    void matchUsersBasedOnSkillsSuccess() {
        User joeCoyne = (User)userDao.getById(1);
        User karenMack = (User)userDao.getById(4);
        User barneyCurry = (User)userDao.getById(3);
        Skill autoMaintenance = (Skill)skillDao.getById(2);
        Skill fishing = (Skill)skillDao.getById(1);

        //Joe Coyne wants to learn auto maintenance - Karen Mack and Barney Curry know auto maintenance
        //Joe Coyne knows how to fish, but only Karen Mack wants to learn how to fish
        joeCoyne.addSkillsWants(autoMaintenance);
        joeCoyne.addSkillHas(fishing);
        karenMack.addSkillHas(autoMaintenance);
        karenMack.addSkillsWants(fishing);
        barneyCurry.addSkillHas(autoMaintenance);

        userDao.saveOrUpdate(joeCoyne);
        userDao.saveOrUpdate(karenMack);
        userDao.saveOrUpdate(barneyCurry);

        List<User> otherUsers = userDao.getAll();
//        otherUsers.remove(joeCoyne);
        Set<User> matchedUsers = new HashSet<>();

        Set<Skill> joeSkillsWants = joeCoyne.getSkillsWants();
        Set<Skill> joeSkillsHas = joeCoyne.getSkillsHas();

        for (User user : otherUsers) {
            Set<Skill> userWants = user.getSkillsWants();
            Set<Skill> userHas = user.getSkillsHas();

            Set<Skill> allSkills = new HashSet<>();
            allSkills.addAll(userWants);
            allSkills.addAll(userHas);

            for (Skill skill : allSkills) {
                int skillId = skill.getSkillId();
                for (Skill wantSkill : joeSkillsWants) {
                    int wantSkillId = wantSkill.getSkillId();
                    if(wantSkillId == skillId) {
                        System.out.println("joe wants auto");
                        for (Skill joeSkillHas : joeSkillsHas) {
                            int hasSkillId = joeSkillHas.getSkillId();
                            System.out.println("Joe has: " + joeSkillHas);

                            if (userHas.contains(skill) && userWants.contains(joeSkillHas)) {
                                matchedUsers.add(user);
                            }
                        }
                    }
                }
            }
        }
    }
}
