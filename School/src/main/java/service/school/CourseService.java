package service.school;

import models.*;
import repository.CourseRep;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CourseService extends SchoolService {
    private Scanner scanner;
    private LectureService lectureService;
    private StudentService studentService;
    private TeacherService teacherService;

    public CourseService(CourseRep schoolRep, Scanner scanner, LectureService lectureService,
                         StudentService studentService, TeacherService teacherService) {
        this.schoolRep = schoolRep;
        this.scanner = scanner;
        this.lectureService = lectureService;
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    public Course create() {
        Course course = new Course();
        addLectures(course);
        addTeachers(course);
        addStudents(course);
        schoolRep.add(course);
        System.out.println(course);
        return course;
    }

    private void addLectures(Course course) {
        while (true) {
            System.out.println("Please, add lectures. \n" +
                    "If you want to create new lecture enter 1, \n" +
                    "finish adding lectures enter 2");
            String response = scanner.next();
            switch (response) {
                case "1":
                    Lecture lecture = lectureService.createLectureInCourse(course.getId());
                    addLectureToList(course, lecture);
                    System.out.println("Lecture added");
                    break;
                case "2":
                    return;
                default:
                    System.out.println("Wrong command");
                    addLectures(course);
                    break;
            }
        }
    }

    private void addTeachers(Course course) {
        while (true) {
            System.out.println("Please, add teachers. \n" +
                    "If you want to create new teachers enter 1, \n" +
                    "finish adding teachers enter 2");
            String response = scanner.next();
            switch (response) {
                case "1":
                    Teacher teacher = teacherService.create();
                    List<Teacher> teachers = course.getTeachers();
                    if (teachers == null) {
                        teachers = new ArrayList<>();
                    }
                    teachers.add(teacher);
                    System.out.println("Teacher added");
                    break;
                case "2":
                    return;
                default:
                    System.out.println("Wrong command");
                    break;
            }
        }
    }

    private void addStudents(Course course) {
        while (true) {
            System.out.println("Please, add students. \n" +
                    "If you want to create new students enter 1, \n" +
                    "finish adding students enter 2");
            String response = scanner.next();
            switch (response) {
                case "1":
                    Student student = studentService.create();
                    List<Student> students = course.getStudents();
                    if (students == null) {
                        students = new ArrayList<>();
                    }
                    students.add(student);
                    System.out.println("Student added");
                    break;
                case "2":
                    return;
                default:
                    System.out.println("Wrong command");
                    break;
            }
        }
    }

    public void addLectureToList(Course course, Lecture lecture) {
        List<Lecture> lectures = course.getLectures();
        if (lectures == null) {
            lectures = new ArrayList<Lecture>();
        }
        lecture.setIdCourse(course.getId());
        lectures.add(lecture);
        course.setLectures(lectures);
    }

}
