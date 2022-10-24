package service;

import entity.Student;

public class StudentService {

    public int countStudent = 0;

    public Student createStudent(String name, String surname){
        countStudent++;
        int idStudent = countStudent;
        Student student = new Student(idStudent, name, surname);
        return student;
    }

}
