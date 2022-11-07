import repository.*;
import service.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

         CourseRep courses = new CourseRep();
         LectureRep lectures = new LectureRep();
         HomeworkRep homeworks = new HomeworkRep();
         MaterialsRep materials = new MaterialsRep();
         StudentRep students = new StudentRep();
         TeacherRep teachers = new TeacherRep();


        MaterialService materialService = new MaterialService(materials, scanner);
        HomeworkService homeworkService = new HomeworkService(homeworks, scanner);
        LectureService lectureService = new LectureService(lectures, scanner, homeworkService, materialService);
        StudentService studentService = new StudentService(students, scanner);
        TeacherService teacherService = new TeacherService(teachers, scanner);
        CourseService courseService = new CourseService(courses, scanner, lectureService, studentService, teacherService);

        CommandService commandService = new CommandService(scanner, courseService, lectureService, studentService, teacherService);

        commandService.startApp();

//        Course course = courseService.create();
//        courseService.addLecture(course, lectureService.createLecture("1", "1", "1"));
//        courseService.addLecture(course, lectureService.createLecture("2", "1", "1"));
//        courseService.addLecture(course, lectureService.createLecture("3", "1", "1"));
//        courseService.addLecture(course, lectureService.createLecture("4", "1", "1"));
//        courseService.addLecture(course, lectureService.createLecture("5", "1", "1"));
//        courseService.addLecture(course, lectureService.createLecture("6", "1", "1"));
//
//        System.out.println("Ід курсу 6 лекції -"+course.getLectures().get(5).idCourse);
//        System.out.println("Кількість створених лекцій - "+ Lecture.count);


    }
}
