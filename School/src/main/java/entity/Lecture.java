package entity;

public class Lecture {

    private int id;

    public static int count = 0;
    private String subject;
    private String homework;
    private String materials;

    public int idCourse;

    public Lecture(String subject, String homework, String materials) {
        this.id = ++count;
        this.subject = subject;
        this.homework = homework;
        this.materials = materials;
    }

    @Override
    public String toString() {
        return "Lecture{" +
                "subject='" + subject + '\'' +
                ", idCourse=" + idCourse +
                '}';
    }
}
