package models.school_object;

import models.Role;

import java.io.Serializable;

public class Student extends Person implements Runnable, Serializable {

    private int time;

    private int taskNumber;

    public Student(String firstname, String lastname, String phone, String email) {
        super(Role.STUDENT, firstname, lastname, phone, email);
    }

    @Override
    public void run() {
        time = (int) (8 + (Math.random() * (15 - 8)));
    }

    public int getTime() {
        return time;
    }


    public void setTaskNumber(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String toString() {
        return "Student " + super.getFirstname() + " " + super.getLastname() + ", task number " + this.taskNumber;
    }

}
