package service;

import models.*;
import repository.CourseRep;
import repository.SchoolRep;
import service.school.CourseService;
import service.school.LectureService;
import service.school.PersonService;


import java.util.Scanner;

public class CommandService {

    private Scanner scanner;
    private CourseService courseService;
    private LectureService lectureService;

    private PersonService personService;

    private SchoolRep courseRep;
    private SchoolRep lectureRep;
    private SchoolRep personRep;

//    private static final String START = "Print \"course\", \"lecture\", \"student\", \"teacher\" for create. \n" +
//            "Print \"exit\" for exit";

    private static final String START = "Print \"create\", \"read\", \"read_by_id\" or \"exit\"";

    private static final String PRINT_COMMAND = "Print command:";
    private static final String RESPONSE_CREATE = "create";
    private static final String RESPONSE_READ = "read";
    private static final String RESPONSE_READ_BY_ID = "read_by_id";

    private static final String COMMAND_CREATE = "Print \"course\", \"lecture\", \"person\" for create,\n " +
            "\"back\" to return to the main menu, \"exit\" to end the program";

    private static final String COMMAND_READ = "Print \"course\", \"lecture\", \"person\" for read\n " +
            "\"back\" to return to the main menu, \"exit\" to end the program";


    private static final String RESPONSE_COURSE = "course";
    private static final String RESPONSE_LECTURE = "lecture";
    private static final String RESPONSE_PERSON = "person";
    private static final String RESPONSE_BACK = "back";
    private static final String RESPONSE_EXIT = "exit";
    private static final String ANSWER_WRONG_RESPONSE = "Wrong response! ";

    private boolean play = true;

    public CommandService(Scanner scanner, CourseService courseService, LectureService lectureService, PersonService personService, SchoolRep courseRep, SchoolRep lectureRep, SchoolRep personRep) {
        this.scanner = scanner;
        this.courseService = courseService;
        this.lectureService = lectureService;
        this.personService = personService;
        this.courseRep = courseRep;
        this.lectureRep = lectureRep;
        this.personRep = personRep;
    }

    public void startApp(){
        startCreate();
        System.out.println(START);
        while (play){
            if (Lecture.getCount() == 8){
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
            case RESPONSE_READ_BY_ID:
                readByIdCommand();
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
                System.out.println("Lecture of course (id) "+lecture.getIdCourse());
                System.out.println("Number of lectures - "+Lecture.getCount());
                return;
            case RESPONSE_PERSON:
                Person person = personService.create();
                personRep.add(person);
                System.out.println("Person created");
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
                courseRep.printAll();
                return;
            case RESPONSE_LECTURE:
                lectureRep.printAll();
                return;
            case RESPONSE_PERSON:
                personRep.printAll();
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

    private void readByIdCommand(){
        System.out.println(COMMAND_READ);
        String response = scanner.next();
        System.out.println("Print id:");
        int response2;
        try {
            response2 = Integer.parseInt(scanner.next());
        } catch (Exception ex){
            System.out.println("Wrong id");
            return;
        }

        switch (response){
            case RESPONSE_COURSE:
                Course course = (Course) courseRep.getById(response2);
                System.out.println(course);
                return;
            case RESPONSE_LECTURE:
                SchoolObject lecture = lectureRep.getById(response2);
                System.out.println(lecture);
                return;
            case RESPONSE_PERSON:
                SchoolObject person = personRep.getById(response2);
                System.out.println(person);
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
