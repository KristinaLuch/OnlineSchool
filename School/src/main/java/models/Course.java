package models;

import java.util.List;
import java.util.Objects;

public class Course{

    private int id;
    private static int count = 0;
    private String name;
    private List<Person> persons;
    private List<Lecture> lectures;

    public Course(String name, List<Person> persons, List<Lecture> lectures) {
        this.id = ++count;
        this.name = name;
        this.lectures = lectures;
        this.persons = persons;
    }

    public Course() {
        this.id = ++count;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course course)) return false;
        return id == course.id && Objects.equals(name, course.name) && Objects.equals(persons, course.persons) && Objects.equals(lectures, course.lectures);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, persons, lectures);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lectures=" + lectures +
                ", persons=" + persons +
                '}';
    }
}
