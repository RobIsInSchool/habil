package com.radams.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * The type Lesson.
 */
@Entity(name = "Lesson")
@Table(name = "lessons")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id")
    private int lessonId;

    @ManyToOne
    @JoinColumn(name = "student_user_id", referencedColumnName = "id", nullable = false)
    private User student;

    @ManyToOne
    @JoinColumn(name = "teacher_user_id", referencedColumnName = "id", nullable = false)
    private User teacher;

    /**
     * Instantiates a new Lesson.
     */
    public Lesson() {

    }

    /**
     * Instantiates a new Lesson.
     *
     * @param student the student id
     * @param teacher the teacher id
     */
    public Lesson(User student, User teacher) {
        this.student = student;
        this.teacher = teacher;
    }

    /**
     * Gets lesson id.
     *
     * @return the lesson
     */
    public int getLessonId() {
        return lessonId;
    }

    /**
     * Sets lesson
     *
     * @param lessonId the lesson
     */
    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    /**
     * Gets student.
     *
     * @return the student
     */
    public User getStudent() {
        return student;
    }

    /**
     * Sets student
     *
     * @param student the student
     */
    public void setStudent(User student) {
        this.student = student;
    }

    /**
     * Gets teacher
     *
     * @return the teacher
     */
    public User getTeacher() {
        return teacher;
    }

    /**
     * Sets teacher id.
     *
     * @param teacher the teacher
     */
    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "lessonId=" + lessonId +
                ", student=" + student.getUserId() +
                ", teacher=" + teacher.getUserId() +
                '}';
    }
}
