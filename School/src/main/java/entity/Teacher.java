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
}
