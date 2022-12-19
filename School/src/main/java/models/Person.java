package models;

import constants.Role;

public class Person extends SchoolObject{

    private int courseID;

    private Role role;

    private static int count = 0;

    private int id;

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
        this.courseID = courseID;
        this.role = role;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCourseID() {
        return courseID;
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

    public String getPhone() {
        return phone;
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
}
