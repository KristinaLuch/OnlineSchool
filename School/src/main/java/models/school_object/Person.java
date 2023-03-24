package models.school_object;

import models.Role;

import java.io.Serializable;

public class Person implements Comparable<Person>, Serializable {

    private int courseID;
    private Role role;
    private static int count = 0;
    private final Integer id;
    private String firstname;
    private String lastname;
    private String phone;
    private String email;

    public Person(int courseID, Role role, String firstname, String lastname, String phone, String email) {
        id = ++count;
        this.courseID = courseID;
        this.role = role;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
    }

    public Person(Role role, String firstname, String lastname, String phone, String email) {
        id = ++count;
        this.role = role;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public Role getRole() {
        return role;
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
        return "Person{" +
                "courseID=" + courseID +
                ", role=" + role +
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
