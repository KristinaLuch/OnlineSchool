package models.school_object;

import java.io.Serializable;
import java.util.ArrayList;

public class Lecture implements Serializable {
    private static int count = 0;
    private final Integer id;
    private int idCourse;
    private int personId;
    private String name;
    private String description;
    private ArrayList<Homework> homework;
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


    public Materials getMaterials() {
        return materials;
    }

    public void setMaterials(Materials materials) {
        this.materials = materials;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public ArrayList<Homework> getHomework() {
        return homework;
    }

    public void setHomework(ArrayList<Homework> homework) {
        this.homework = homework;
    }

    @Override
    public String toString() {
        return "Lecture{" +
                "id=" + id +
                ", idCourse=" + idCourse +
                ", personId=" + personId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", homework=" + homework +
                ", materials=" + materials +
                '}';
    }
}
