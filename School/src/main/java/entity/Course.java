package entity;

import java.util.ArrayList;

public class Course {

    public static int count = 0;
    private int id;
    private ArrayList<Teacher> teachers;
    private ArrayList<Student> students;
    private ArrayList<Lecture> lectures;

    public Course(ArrayList<Teacher> teachers, ArrayList<Student> students, ArrayList<Lecture> lectures) {
        this.id = ++count;
        this.teachers = teachers;
        this.students = students;
        this.lectures = lectures;
    }

    public Course() {
        this.id = ++count;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(ArrayList<Teacher> teachers) {
        this.teachers = teachers;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public ArrayList<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(ArrayList<Lecture> lectures) {
        this.lectures = lectures;
    }
}
