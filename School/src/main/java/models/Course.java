package models;

import java.util.ArrayList;
import java.util.List;

public class Course extends SchoolObject {

    private static int count = 0;
    private List<Teacher> teachers;
    private List<Student> students;
    private List<Lecture> lectures;

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

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(List<Lecture> lectures) {
        this.lectures = lectures;
    }

    public static int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", teachers=" + teachers +
                ", students=" + students +
                ", lectures=" + lectures +
                '}';
    }


}
