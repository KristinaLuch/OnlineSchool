package entity;

public class Course {

    private int id;
    private Teacher[] teachers;
    private Student[] students;
    private Lecture[] lectures;

    public Course(int id, Teacher[] teachers, Student[] students, Lecture[] lectures) {
        this.id = id;
        this.teachers = teachers;
        this.students = students;
        this.lectures = lectures;
    }
}
