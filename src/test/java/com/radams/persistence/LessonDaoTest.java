package com.radams.persistence;

import com.radams.entity.Lesson;
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

        int newLessonId = lessonDao.insert(testLesson);

        assertNotNull(lessonDao.getById(newLessonId));
    }

    @Test
    void getAllLessonsSuccess() {
        List<Lesson> retrievedLessons = lessonDao.getAll();
        assertEquals(5, retrievedLessons.size());
    }

    @Test
    void updateLessonSuccess() {
        // 4 teaches 6 - or - Karen Mack teaches Diane Klein
        Lesson lessonToUpdate = (Lesson)lessonDao.getById(3);

        // Change 4 (Karen Mack) to 1 (Joe Coyne)
        User teacher = (User)userDao.getById(1);
        User student = (User)userDao.getById(6);
        lessonToUpdate.setTeacher(teacher);
        lessonDao.saveOrUpdate(lessonToUpdate);
        Lesson updatedLesson = (Lesson)lessonDao.getById(3);
        assertEquals(1, updatedLesson.getTeacher().getUserId());
    }

    @Test
    void deleteLessonSuccess() {
        //Delete lesson with id of 3
        int idToDelete = 3;
        Lesson lessonToDelete = (Lesson)lessonDao.getById(idToDelete);
        List<Lesson> lessonsBefore = lessonDao.getAll();
        assertEquals(5, lessonsBefore.size());
        assertNotNull(lessonDao.getById(idToDelete));
        lessonDao.delete(lessonToDelete);
        List<Lesson> lessonsAfter = lessonDao.getAll();
        assertEquals(4, lessonsAfter.size());
        assertNull(lessonDao.getById(idToDelete));
    }


    @Test
    void findLessonsByPropertyTeacher() {
        //Get Joe Coyne, who should be teacher twice
        User teacherToFind = (User)userDao.getById(1);
        List<Lesson> taughtLessons = lessonDao.findByPropertyEqual("teacher", teacherToFind);
        assertNotNull(taughtLessons);
        assertEquals(2, taughtLessons.size());

        assertEquals(2, taughtLessons.get(0).getLessonId());
        assertEquals(5, taughtLessons.get(1).getLessonId());
    }

    @Test
    void findLessonsByPropertyStudent() {
        //Get Fred Hensen, who should be student twice
        User studentToFind = (User)userDao.getById(2);
        List<Lesson> takenLessons = lessonDao.findByPropertyEqual("student", studentToFind);
        assertNotNull(takenLessons);
        assertEquals(2, takenLessons.size());

        assertEquals(2, takenLessons.get(0).getLessonId());
        assertEquals(5, takenLessons.get(1).getLessonId());

    }

    @Test
    void findLessonByPropertiesTeacherAndStudent() {
        //Get teacher Joe Coyne and Student Fred Hensen
        User teacherToFind = (User)userDao.getById(1);
        User studentToFind = (User)userDao.getById(2);

        Map<String, User> propertiesToSearch = new HashMap<>();
        propertiesToSearch.put("teacher", teacherToFind);
        propertiesToSearch.put("student", studentToFind);

        List<Lesson> foundLessons = lessonDao.findByPropertiesEqual(propertiesToSearch);

        assertNotNull(foundLessons);
        assertEquals(2, foundLessons.get(0).getLessonId());
        assertEquals(5, foundLessons.get(1).getLessonId());
    }
}
