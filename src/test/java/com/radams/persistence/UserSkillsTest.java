package com.radams.persistence;

import com.radams.entity.Lesson;
import com.radams.entity.Skill;
import com.radams.entity.User;
import com.radams.test.util.Database;
import net.bytebuddy.description.type.TypeList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
}
