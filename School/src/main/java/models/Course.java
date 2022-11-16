package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Course extends SchoolEntity {

    public static int count = 0;
    //private int id;
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

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", teachers=" + teachers +
                ", students=" + students +
                ", lectures=" + lectures +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(teachers, course.teachers) && Objects.equals(students, course.students) && Objects.equals(lectures, course.lectures);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teachers, students, lectures);
    }
}
