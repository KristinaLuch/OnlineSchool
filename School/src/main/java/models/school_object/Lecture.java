package models.school_object;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Optional;

public class Lecture implements Serializable {
    private static int count = 0;
    private final Integer id;
    private int idCourse;
    private int personId;
    private String name;
    private String description;
    private ArrayList<Homework> homework;
    private Materials materials;

    private final LocalDateTime creationDate;

    private LocalDateTime lectureDate;

    public Lecture() {
        this.id = ++count;
        creationDate = LocalDateTime.now();
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Optional<LocalDateTime> getLectureDate() {
        return Optional.ofNullable(lectureDate);
    }

    public void setLectureDate(LocalDateTime lectureDate) {
        this.lectureDate = lectureDate;
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

    public Optional<ArrayList<Homework>> getHomework() {
        return Optional.ofNullable(homework);
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
                lectureDate() +
                '}';
    }
    private String lectureDate(){
        if(creationDate == null){
            return "";
        }
        return ", lectureDate=" + creationDate.format(DateTimeFormatter.ofPattern("MMM d, EEEE HH:mm:ss", Locale.ENGLISH));
    }

}
