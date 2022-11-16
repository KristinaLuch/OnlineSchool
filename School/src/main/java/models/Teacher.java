package models;

import java.util.Objects;

public class Teacher extends SchoolEntity {

    public static int count = 0;
    private int id;
    private String name;
    private String surname;
    private String subject;

    public Teacher(String name, String surname, String subject) {
        this.id = ++count;
        this.name = name;
        this.surname = surname;
        this.subject = subject;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teacher teacher)) return false;
        return id == teacher.id && Objects.equals(name, teacher.name) && Objects.equals(surname, teacher.surname) && Objects.equals(subject, teacher.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, subject);
    }
}
