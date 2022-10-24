package service;

import entity.Teacher;

public class TeacherService {

    public int countTeacher = 0;

    public Teacher createTeacher(String name, String surname, String subject){
        countTeacher++;
        int idTeacher = countTeacher;
        Teacher teacher = new Teacher(idTeacher, name, surname, subject);
        return teacher;
    }

}
