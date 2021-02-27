package com.radams.entity;

/**
 * The type Lesson.
 */
public class Lesson {

    private int lessonId;
    private int studentId;
    private int teacherId;

    /**
     * Instantiates a new Lesson.
     */
    public Lesson() {

    }

    /**
     * Instantiates a new Lesson.
     *
     * @param studentId the student id
     * @param teacherId the teacher id
     */
    public Lesson(int studentId, int teacherId) {
        this.studentId = studentId;
        this.teacherId = teacherId;
    }

    /**
     * Gets lesson id.
     *
     * @return the lesson id
     */
    public int getLessonId() {
        return lessonId;
    }

    /**
     * Sets lesson id.
     *
     * @param lessonId the lesson id
     */
    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    /**
     * Gets student id.
     *
     * @return the student id
     */
    public int getStudentId() {
        return studentId;
    }

    /**
     * Sets student id.
     *
     * @param studentId the student id
     */
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    /**
     * Gets teacher id.
     *
     * @return the teacher id
     */
    public int getTeacherId() {
        return teacherId;
    }

    /**
     * Sets teacher id.
     *
     * @param teacherId the teacher id
     */
    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "lessonId=" + lessonId +
                ", studentId=" + studentId +
                ", teacherId=" + teacherId +
                '}';
    }
}
