package entity;

public class Lecture {

    private int id;
    private String subject;
    private String homework;
    private String materials;

    public int idCourse;

    public Lecture(int id, String subject, String homework, String materials) {
        this.id = id;
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
