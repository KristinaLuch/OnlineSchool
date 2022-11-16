package service;

import models.Course;
import models.Homework;
import models.Lecture;
import models.Materials;
import service.school.CourseService;
import service.school.LectureService;
import service.school.StudentService;
import service.school.TeacherService;

import java.util.Scanner;

public class CommandService {

    private Scanner scanner;
    private CourseService courseService;
    private LectureService lectureService;

    private StudentService studentService;
    private TeacherService teacherService;

//    private static final String START = "Print \"course\", \"lecture\", \"student\", \"teacher\" for create. \n" +
//            "Print \"exit\" for exit";

    private static final String START = "Print \"create\", \"read\" or \"exit\"";

    private static final String PRINT_COMMAND = "Print command:";
    private static final String RESPONSE_CREATE = "create";
    private static final String RESPONSE_READ = "read";

    private static final String COMMAND_CREATE = "Print \"course\", \"lecture\", \"student\", \"teacher\" for create,\n " +
            "\"back\" to return to the main menu, \"exit\" to end the program";

    private static final String COMMAND_READ = "Print \"course\", \"lecture\", \"student\", \"teacher\" for read\n " +
            "\"back\" to return to the main menu, \"exit\" to end the program";


    private static final String RESPONSE_COURSE = "course";
    private static final String RESPONSE_LECTURE = "lecture";
    private static final String RESPONSE_STUDENT = "student";
    private static final String RESPONSE_TEACHER = "teacher";

    private static final String RESPONSE_BACK = "back";
    private static final String RESPONSE_EXIT = "exit";
    private static final String ANSWER_WRONG_RESPONSE = "Wrong response! ";

    private boolean play = true;

    public CommandService(Scanner scanner, CourseService courseService, LectureService lectureService, StudentService service, TeacherService teacherService) {
        this.scanner = scanner;
        this.courseService = courseService;
        this.lectureService = lectureService;
        this.studentService = service;
        this.teacherService = teacherService;
    }

    public void startApp(){
        startCreate();
        System.out.println(START);
        while (play){
            if (Lecture.count == 8){
                System.out.println("You already created 8 lectures. Stop it! I'm done");
                break;
            }
            System.out.println(PRINT_COMMAND);
            String response = scanner.next();
            select(response);
        }
    }

    private void select(String response){

        switch (response){
            case RESPONSE_CREATE:
                createCommand();
                return;
            case RESPONSE_READ:
                readCommand();
                return;
            case RESPONSE_EXIT:
                System.out.println("Well, it's your choice");
                play = false;
                return;
            default:
                System.out.println(ANSWER_WRONG_RESPONSE);
        }
    }

    private void createCommand(){
        System.out.println(COMMAND_CREATE);
        String response = scanner.next();
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
            case RESPONSE_BACK:
                System.out.println("Main menu");
                return;
            case RESPONSE_EXIT:
                System.out.println("Well, it's your choice");
                play = false;
                return;
            default:
                System.out.println(ANSWER_WRONG_RESPONSE);
        }
    }

    private void readCommand(){
        System.out.println(COMMAND_READ);
        String response = scanner.next();
        switch (response){
            case RESPONSE_COURSE:
                courseService.printAll();
                return;
            case RESPONSE_LECTURE:
                lectureService.printAll();
                return;
            case RESPONSE_STUDENT:
                studentService.printAll();
                return;
            case RESPONSE_TEACHER:
                teacherService.printAll();
                return;
            case RESPONSE_BACK:
                System.out.println("Main menu");
                return;
            case RESPONSE_EXIT:
                System.out.println("Well, it's your choice");
                play = false;
                return;
            default:
                System.out.println(ANSWER_WRONG_RESPONSE);
        }
    }

    private void startCreate(){
        System.out.println("Created one course with three lectures: ");
        Course course = new Course();
        Homework homework1 = new Homework("1 hw");
        Homework homework2 = new Homework("2 hw");
        Homework homework3 = new Homework("3 hw");
        Materials materials1 = new Materials("materials");
        Lecture lecture1 = new Lecture("math", homework1, materials1);
        Lecture lecture2 = new Lecture("history", homework2, materials1);
        Lecture lecture3 = new Lecture("literature", homework3, materials1);
        lectureService.addLectureToRep(lecture1);
        lectureService.addLectureToRep(lecture2);
        lectureService.addLectureToRep(lecture3);
        courseService.addLectureToList(course, lecture1);
        courseService.addLectureToList(course, lecture2);
        courseService.addLectureToList(course, lecture3);
        courseService.addToRep(course);
        System.out.println(course + "\n");
    }

}
