package models;

import java.util.List;

public class Course extends SchoolObject {

    private static int count = 0;
    private String name;
    private List<Person> persons;
    private List<Lecture> lectures;

    public Course(String name, List<Person> persons, List<Lecture> lectures) {
        this.name = name;
        this.persons = persons;
        this.lectures = lectures;
        this.id = ++count;
    }

    public Course() {
        this.id = ++count;
    }

    public int getId() {
        return id;
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
    public String toString() {
        return "Course{" +
                "persons=" + persons +
                ", lectures=" + lectures +
                ", id=" + id +
                '}';
    }
}
