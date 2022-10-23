package service;

import entity.Student;

public class StudentService {

    public int countStudent = 0;

    public void addStudent(int id, String name, String surname){
        Student student = new Student(id, name, surname);
        countStudent++;
    }

}
