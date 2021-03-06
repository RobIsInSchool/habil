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
    }

    void updateUserJoeCoyneWithNewSkillHas() {
        User joeCoyne = (User)userDao.getById(1);
        Skill someSkill = (Skill)skillDao.getById(1);
    }
}
