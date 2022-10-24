package service;

import entity.Teacher;

public class TeacherService {



    public Teacher createTeacher(String name, String surname, String subject){

        return new Teacher(name, surname, subject);

    }

}
