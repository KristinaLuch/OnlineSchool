package models;

import java.util.Arrays;

public class Lecture {
    private static int count = 0;
    private int id;
    private int idCourse;
    private int personId;
    private String name;
    private String description;
    private Homework[] homework;
    private Materials materials;

    public Lecture() {
        this.id = ++count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static int getCount() {
        return count;
    }

    public int getId() {
        return id;
    }

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public Homework[] getHomework() {
        return homework;
    }

    public void setHomework(Homework[] homework) {
        this.homework = homework;
    }

    public Materials getMaterials() {
        return materials;
    }

    public void setMaterials(Materials materials) {
        this.materials = materials;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    @Override
    public String toString() {
        return "Lecture{" +
                "id=" + id +
                ", idCourse=" + idCourse +
                ", personId=" + personId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", homework=" + Arrays.toString(homework) +
                ", materials=" + materials +
                '}';
    }
}
