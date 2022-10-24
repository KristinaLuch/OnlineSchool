package service;

import entity.Student;

public class StudentService {



    public Student createStudent(String name, String surname){
        return new Student(name, surname);
    }

}
