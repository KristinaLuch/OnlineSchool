package entity;

public class Lecture {

    private int id;

    public static int count = 0;
    private String subject;
    private Homework homework;
    private Materials materials;

    public int idCourse;

    public Lecture(String subject, Homework homework, Materials materials) {
        this.subject = subject;
        this.homework = homework;
        this.materials = materials;
        this.id = ++count;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Lecture{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", homework=" + homework +
                ", materials=" + materials +
                ", idCourse=" + idCourse +
                '}';
    }
}
