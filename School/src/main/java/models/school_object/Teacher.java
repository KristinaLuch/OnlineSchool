package models.school_object;

import models.Role;

import java.io.Serializable;

public class Teacher implements Comparable<Teacher>, Serializable {

    private int courseID;
    private static int count = 0;
    private final Integer id;
    private String firstname;
    private String lastname;
    private String phone;
    private String email;

    public Teacher(Integer id, String firstname, String lastname, String phone, String email) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
    }

    public Teacher(int courseID, String firstname, String lastname, String phone, String email) {
        id = ++count;
        this.courseID = courseID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
    }

    public Teacher(String firstname, String lastname, String phone, String email) {
        id = ++count;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public static int getCount() {
        return count;
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
        return "Teacher{" +
                "courseID=" + courseID +
                ", id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public int compareTo(Teacher o) {
        return this.lastname.compareTo(o.lastname);
    }

}
