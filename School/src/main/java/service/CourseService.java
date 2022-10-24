package service;

import entity.Course;
import entity.Lecture;
import entity.Student;
import entity.Teacher;

import java.util.ArrayList;

public class CourseService {

    public int countCourses = 0;

    public Course createCourse (){
        countCourses++;
        Course course = new Course();
        int idCourse = countCourses;
        course.setId(idCourse);
        return course;
    }

    public void addLecture(Course course, Lecture lecture){
        ArrayList<Lecture> lectures = course.getLectures();
        if(lectures == null){
            lectures = new ArrayList<Lecture>();
        }
        lecture.idCourse = course.getId();
        lectures.add(lecture);
        course.setLectures(lectures);

    }

}
