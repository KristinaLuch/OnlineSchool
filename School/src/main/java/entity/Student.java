package entity;

public class Student {

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
}
