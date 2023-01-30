package service;

import constants.ValidationType;
import models.*;
import repository.CourseRep;
import repository.LectureRep;
import repository.PersonRep;
import service.conversation.ConversationService;
import service.school.CourseService;
import service.school.LectureService;
import service.school.PersonService;


import java.util.Scanner;

public class CommandService {

    private Scanner scanner;
    private CourseService courseService;
    private LectureService lectureService;

    private ConversationService conversationService;
    private PersonService personService;
    private CourseRep courseRep;
    private LectureRep lectureRep;
    private PersonRep personRep;
    private static final String START = "Print \"create\", \"read\", \"read_by_id\", \"delete_by_id\" or \"exit\"";

    private static final String PRINT_COMMAND = "Print command:";
    private static final String RESPONSE_CREATE = "create";
    private static final String RESPONSE_READ = "read";
    private static final String RESPONSE_READ_BY_ID = "read_by_id";

    private static final String RESPONSE_DELETE_BY_ID = "delete_by_id";

    private static final String COMMAND_CREATE = "Print \"course\", \"lecture\", \"person\" for create,\n " +
            "\"back\" to return to the main menu, \"exit\" to end the program";

    private static final String COMMAND_READ = "Print \"course\", \"lecture\", \"person\" for read\n " +
            "\"back\" to return to the main menu, \"exit\" to end the program";

    private static final String COMMAND_DELETE = "Print \"course\", \"lecture\", \"person\" for delete\n " +
            "\"back\" to return to the main menu, \"exit\" to end the program";

    private static final String RESPONSE_COURSE = "course";
    private static final String RESPONSE_LECTURE = "lecture";
    private static final String RESPONSE_PERSON = "person";
    private static final String RESPONSE_BACK = "back";
    private static final String RESPONSE_EXIT = "exit";

    public static final String DELETED = "Deleted success";
    private static final String ANSWER_WRONG_RESPONSE = "Wrong response! ";

    public static final String PRINT_ID = "Print id: ";

    private boolean play = true;

    public CommandService(Scanner scanner, CourseService courseService, LectureService lectureService,
                          PersonService personService, CourseRep courseRep,
                          LectureRep lectureRep, PersonRep personRep, ConversationService conversationService) {
        this.scanner = scanner;
        this.courseService = courseService;
        this.lectureService = lectureService;
        this.personService = personService;
        this.courseRep = courseRep;
        this.lectureRep = lectureRep;
        this.personRep = personRep;
        this.courseService = courseService;
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
            case RESPONSE_DELETE_BY_ID:
                deleteByIdCommand();
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
                Course course = (Course) courseRep.get(response2);
                System.out.println(course);
                return;
            case RESPONSE_LECTURE:
                Lecture lecture = lectureRep.get(response2);
                System.out.println(lecture);
                return;
            case RESPONSE_PERSON:
                Person person = personRep.get(response2);
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

    private void deleteByIdCommand(){
        conversationService.print(COMMAND_DELETE);
        String response = conversationService.getResponse(COMMAND_DELETE, ValidationType.ANYTHING);

        int response2 = Integer.parseInt(conversationService.getResponse(PRINT_ID, ValidationType.DIGIT));

        switch (response){
            case RESPONSE_COURSE:
                courseRep.delete(response2);
                conversationService.print(DELETED);
                return;
            case RESPONSE_LECTURE:
                lectureRep.delete(response2);
                conversationService.print(DELETED);
                return;
            case RESPONSE_PERSON:
                personRep.delete(response2);
                conversationService.print(DELETED);
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
        course.setName("for zero");
        Materials materials1 = new Materials("materials");

        Lecture lecture1 = new Lecture();
        lecture1.setName("math");
        lecture1.setDescription("For those who are not humanitarian");
        lecture1.setIdCourse(course.getId());
        lecture1.setMaterials(materials1);
        Homework homework1 = new Homework(lecture1.getId(), "1 hw");
        Homework[] homeworks1 = new Homework[1];
        homeworks1[0] = homework1;
        lecture1.setHomework(homeworks1);

        Lecture lecture2 = new Lecture();
        lecture2.setName("history");
        lecture2.setDescription("Dates and events");
        lecture2.setIdCourse(course.getId());
        lecture2.setMaterials(materials1);
        Homework homework2 = new Homework(lecture2.getId(), "2 hw");
        Homework[] homeworks2 = new Homework[1];
        homeworks2[0] = homework2;
        lecture2.setHomework(homeworks2);

        Lecture lecture3 = new Lecture();
        lecture3.setName("literature");
        lecture3.setDescription("Where do you think the person thought, although he may not have thought");
        lecture3.setIdCourse(course.getId());
        lecture3.setMaterials(materials1);
        Homework homework3 = new Homework(lecture3.getId(), "3 hw");
        Homework[] homeworks3 = new Homework[1];
        homeworks3[0] = homework3;
        lecture3.setHomework(homeworks3);

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
