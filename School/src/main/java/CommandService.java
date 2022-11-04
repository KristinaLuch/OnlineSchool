import entity.Lecture;
import service.CourseService;
import service.LectureService;
import service.StudentService;
import service.TeacherService;

import java.util.Scanner;

public class CommandService {

    private Scanner scanner;
    private CourseService courseService;
    private LectureService lectureService;

    private StudentService studentService;
    private TeacherService teacherService;

    private static final String START = "Choose create: course, lecture, student, teacher";
    private static final String RESPONSE_COURSE = "course";
    private static final String RESPONSE_LECTURE = "lecture";
    private static final String RESPONSE_STUDENT = "student";
    private static final String RESPONSE_TEACHER = "teacher";
    private static final String ANSWER_WRONG_RESPONSE = "Wrong response! ";

    public CommandService(Scanner scanner, CourseService courseService, LectureService lectureService, StudentService service, TeacherService teacherService) {
        this.scanner = scanner;
        this.courseService = courseService;
        this.lectureService = lectureService;
        this.studentService = service;
        this.teacherService = teacherService;
    }

    public void startApp(){

        while (true){
            System.out.println(START);
            String response = scanner.next();
            select(response);
        }
    }

    private void select(String response){

        switch (response){
            case RESPONSE_COURSE:
                courseService.create();
                System.out.println("Course created");
                return;
            case RESPONSE_LECTURE:
                Lecture lecture = lectureService.create();
                System.out.println("Lecture of course (id) "+lecture.idCourse);
                System.out.println("Number of lectures - "+Lecture.count);
                return;
            case RESPONSE_STUDENT:
                studentService.create();
                System.out.println("Student created");
                return;
                case RESPONSE_TEACHER:
                teacherService.create();
                System.out.println("Teacher created");
                return;
            default:
                System.out.println(ANSWER_WRONG_RESPONSE);
        }
    }

}
