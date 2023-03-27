package models.school_object;

import java.io.Serializable;

public class Student extends Person implements Comparable<StudentContr>, Serializable {

    private int courseID;
    private static int count = 0;
    private  Integer id;
    private String firstname;
    private String lastname;
    private String phone;
    private String email;


    public Student(int courseID, int id, String firstname, String lastname, String phone, String email) {
        super(courseID, id, firstname, lastname, phone, email);
    }

    public Student(int courseID, String firstname, String lastname, String phone, String email) {
        super(courseID, firstname, lastname, phone, email);
    }

    public Student(String firstname, String lastname, String phone, String email) {
        super(firstname, lastname, phone, email);
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
        return "Student{" +
                "courseID=" + courseID +
                ", id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public int compareTo(StudentContr o) {
        return this.lastname.compareTo(o.lastname);
    }

}
