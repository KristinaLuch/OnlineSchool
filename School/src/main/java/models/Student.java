package models;

import java.util.Objects;

public class Student extends SchoolEntity {

    public static int count = 0;
    private int id;
    private String name;
    private String surname;

    public Student(String name, String surname) {
        this.id = ++count;
        this.name = name;
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student student)) return false;
        return id == student.id && Objects.equals(name, student.name) && Objects.equals(surname, student.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname);
    }
}
