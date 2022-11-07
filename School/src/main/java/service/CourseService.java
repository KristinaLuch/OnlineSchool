package service;

import entity.Course;
import entity.Lecture;
import entity.Student;
import entity.Teacher;
import repository.CourseRep;

import java.util.ArrayList;
import java.util.Scanner;

public class CourseService {

    public CourseRep courses;
    private Scanner scanner;

    private LectureService lectureService;
    private StudentService studentService;
    private TeacherService teacherService;

    public CourseService(CourseRep courses, Scanner scanner, LectureService lectureService,
                         StudentService studentService, TeacherService teacherService) {
        this.courses = courses;
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
                    Lecture lecture = lectureService.create();
                    addLectureToList(course, lecture);
                    System.out.println("Lecture added");
                    break;
                case "2":
                    return;
                default:
                    System.out.println("Wrong command");
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
                    ArrayList<Teacher> teachers = course.getTeachers();
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
                    ArrayList<Student> students = course.getStudents();
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
        ArrayList<Lecture> lectures = course.getLectures();
        if (lectures == null) {
            lectures = new ArrayList<Lecture>();
        }
        lecture.idCourse = course.getId();
        lectures.add(lecture);
        course.setLectures(lectures);
    }

}
