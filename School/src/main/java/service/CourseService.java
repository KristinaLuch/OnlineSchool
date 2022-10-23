package service;

import entity.Course;
import entity.Lecture;
import entity.Student;
import entity.Teacher;

public class CourseService {

    public int countCourses = 0;

    public void addCourse (int id, Teacher[] teachers, Student[] students, Lecture[] lectures){
        Course course = new Course(id, teachers, students, lectures);
        countCourses++;
    }

}
