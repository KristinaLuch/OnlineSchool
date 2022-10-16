package com.service;

import com.entity.Teacher;

public class TeacherService {

    public int countTeacher = 0;

    public void addTeacher(int id, String name, String surname, String subject){
        Teacher teacher = new Teacher(id, name, surname, subject);
        countTeacher++;
    }

}
