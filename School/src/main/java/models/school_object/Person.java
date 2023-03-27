package models.school_object;

import models.Role;

import java.io.Serializable;

public class Person implements Comparable<Person>, Serializable {
    private int courseID;

    int id;
    private String firstname;
    private String lastname;
    private String phone;
    private String email;

    public Person(int courseID, int id, String firstname, String lastname, String phone, String email) {
        this.courseID = courseID;
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
    }

    public Person(int courseID, String firstname, String lastname, String phone, String email) {
        this.courseID = courseID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
    }

    public Person() {

    }


    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Person{" +
                "courseID=" + courseID +
                ", id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }


    @Override
    public int compareTo(Person o) {
        return this.lastname.compareTo(o.lastname);
    }

}
