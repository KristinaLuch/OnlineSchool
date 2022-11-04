package entity;

public class Teacher {

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
}
