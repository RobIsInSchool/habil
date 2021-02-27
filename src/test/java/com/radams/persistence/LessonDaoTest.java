package com.radams.persistence;

import com.radams.entity.Lesson;
import com.radams.entity.User;
import com.radams.test.util.Database;
import net.bytebuddy.description.type.TypeList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LessonDaoTest {
    GenericDao lessonDao;
    GenericDao userDao;

    @BeforeEach
    void setUp() {
        lessonDao = new GenericDao(Lesson.class);
        userDao = new GenericDao(User.class);
        Database db = Database.getInstance();
        db.runSQL("cleandb.sql");
    }

    @Test
    void getLessonByIdSuccess() {
        Lesson retrievedLesson = (Lesson)lessonDao.getById(1);
        assertNotNull(retrievedLesson);
        assertEquals(2, retrievedLesson.getTeacher().getUserId());
        assertEquals(1, retrievedLesson.getStudent().getUserId());
    }

    @Test
    void insertLessonSuccess() {
        User teacher = (User)userDao.getById(4);
        User student = (User)userDao.getById(6);
        //Karen Mack is teaching Dawn Tillman

        Lesson testLesson = new Lesson(student, teacher);

        teacher.addLessonTaught(testLesson);
        student.addLessonTaken(testLesson);

        int newLessonId = lessonDao.insert(testLesson);

        assertNotNull(lessonDao.getById(newLessonId));
    }

    @Test
    void getAllLessonsSuccess() {
        List<Lesson> retrievedLessons = lessonDao.getAll();
        assertEquals(4, retrievedLessons.size());
    }

    void updateLessonSuccess() {

    }
}
