package entity;

import java.util.ArrayList;

public class Course {

    private int id;
    private ArrayList<Teacher> teachers;
    private ArrayList<Student> students;
    private ArrayList<Lecture> lectures;

    public Course(int id, ArrayList<Teacher> teachers, ArrayList<Student> students, ArrayList<Lecture> lectures) {
        this.id = id;
        this.teachers = teachers;
        this.students = students;
        this.lectures = lectures;
    }

    public Course() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
