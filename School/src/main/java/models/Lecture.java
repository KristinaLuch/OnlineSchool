package models;

import java.util.Objects;

public class Lecture extends SchoolEntity {

    //private int id;

    public static int count = 0;

    public int idCourse;
    private String subject;
    private Homework homework;
    private Materials materials;

    public Lecture(String subject, Homework homework, Materials materials) {
        this.subject = subject;
        this.homework = homework;
        this.materials = materials;
        this.id = ++count;
    }

    public int getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Homework getHomework() {
        return homework;
    }

    public void setHomework(Homework homework) {
        this.homework = homework;
    }

    public Materials getMaterials() {
        return materials;
    }

    public void setMaterials(Materials materials) {
        this.materials = materials;
    }

    @Override
    public String toString() {
        return "Lecture{" +
                "id=" + id +
                ", idCourse=" + idCourse +
                ", subject='" + subject + '\'' +
                ", homework=" + homework +
                ", materials=" + materials +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lecture lecture)) return false;
        return idCourse == lecture.idCourse && Objects.equals(subject, lecture.subject) && Objects.equals(homework, lecture.homework) && Objects.equals(materials, lecture.materials);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCourse, subject, homework, materials);
    }
}
