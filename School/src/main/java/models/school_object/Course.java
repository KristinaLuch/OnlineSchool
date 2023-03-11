package models.school_object;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Course implements Comparable<Course>, Serializable {

    private final Integer id;
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

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public String toString() {
        return "Course{"+"\n" +
                "id=" + id +",\n" +
                " name='" + name + '\'' +",\n" +
                " lectures=" + lectures +",\n" +
                " persons=" + persons +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course course)) return false;
        return Objects.equals(id, course.id) && Objects.equals(name, course.name) && Objects.equals(persons, course.persons) && Objects.equals(lectures, course.lectures);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, persons, lectures);
    }

    @Override
    public int compareTo(Course o) {
        int result = this.name.compareTo(o.name);
        return result;
    }
}
