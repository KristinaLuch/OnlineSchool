package com.service;

import com.entity.Course;
import com.entity.Lecture;
import com.entity.Student;
import com.entity.Teacher;

public class CourseService {

    public int countCourses = 0;

    public void addCourse (int id, Teacher[] teachers, Student[] students, Lecture[] lectures){
        Course course = new Course(id, teachers, students, lectures);
        countCourses++;
    }

}
