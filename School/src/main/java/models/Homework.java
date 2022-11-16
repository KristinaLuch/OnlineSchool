package models;

import java.util.Objects;

public class Homework extends SchoolEntity {

    public static int count = 0;
    private int id;
    private String homework;

    public Homework(String homework) {
        this.homework = homework;
        this.id = ++count;
    }

    public String getHomework() {
        return homework;
    }

    public void setHomework(String homework) {
        this.homework = homework;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Homework{" +
                "id=" + id +
                ", homework='" + homework + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Homework homework1)) return false;
        return id == homework1.id && Objects.equals(homework, homework1.homework);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, homework);
    }
}
