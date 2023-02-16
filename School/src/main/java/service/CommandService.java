package service;

import constants.ValidationType;
import exceptions.EntityNotFoundException;
import exceptions.IncorrectSymbolException;
import models.ResourceType;
import models.Role;
import models.school_object.*;
import repository.CourseRep;
import repository.IPersonRep;
import repository.LectureRep;
import repository.PersonRep;
import service.conversation.ConversationService;
import service.school.*;

import java.util.ArrayList;
import java.util.Scanner;

public class CommandService {

    private ConversationService conversationService;
    private CourseService courseService;
    private LectureService lectureService;
    private PersonService personService;
    private AdditionalMaterialsService additionalMaterialsService;

    public CommandService(ConversationService conversationService, CourseService courseService,
                          LectureService lectureService, PersonService personService,
                          AdditionalMaterialsService additionalMaterialsService) {
        this.conversationService = conversationService;
        this.courseService = courseService;
        this.lectureService = lectureService;
        this.personService = personService;
        this.additionalMaterialsService = additionalMaterialsService;
    }

    private static final String START = "Select \"1\" for course, \"2\" for lecture, \"3\" for person, " +
            "\n\"4\" for additional_materials\" or \"exit\"";
    private static final String PRINT_COMMAND = "Print command:";
    private static final String RESPONSE_CREATE = "create";
    private static final String RESPONSE_READ_ALL = "read_all";
    private static final String RESPONSE_READ_BY_ID = "read";
    private static final String RESPONSE_DELETE_BY_ID = "delete";

    private static final String SELECT_COMMAND = "Print \"create\", \"read\", \"read_all\", \"delete\"" +
            "\"back\" to return to the main menu, \"exit\" to end the program";
    private static final String RESPONSE_COURSE = "1";
    private static final String RESPONSE_LECTURE = "2";
    private static final String RESPONSE_PERSON = "3";
    private static final String RESPONSE_ADD_MATERIALS = "4";
    private static final String RESPONSE_BACK = "back";
    private static final String RESPONSE_EXIT = "exit";
    public static final String DELETED = "Deleted success";
    private static final String ANSWER_WRONG_RESPONSE = "Wrong response! ";
    public static final String PRINT_ID = "Print id: ";

    public static final String CREATED_MAX_LECTURES = "You already created 8 lectures. Stop it! I'm done";

    public static final String MAIN_MENU = "Main menu";

    public static final String EXIT_MESSAGE = "Well, it's your choice";

    private boolean play = true;



    public void startApp(){
        startCreate();
        while (play){
            if (Lecture.getCount() == 8){
                System.out.println(CREATED_MAX_LECTURES);
                break;
            }
            conversationService.print(START);
            String response = conversationService.getResponse(PRINT_COMMAND, ValidationType.ANYTHING);
            SchoolService environment;
            try {
                environment = selectEnvironment(response);
                selectCommand(environment);

            } catch (IncorrectSymbolException e) {
                e.printStackTrace();
            }
        }
    }

    private SchoolService selectEnvironment(String response) throws IncorrectSymbolException {

        switch (response){
            case RESPONSE_COURSE:
                return courseService;
            case RESPONSE_LECTURE:
                return lectureService;
            case RESPONSE_PERSON:
                return personService;
            case RESPONSE_ADD_MATERIALS:
                return additionalMaterialsService;
            case RESPONSE_EXIT:
                System.out.println(EXIT_MESSAGE);
                System.exit(0);
            default:
                throw new IncorrectSymbolException(ANSWER_WRONG_RESPONSE);

        }
    }

    private void selectCommand(SchoolService environment) throws IncorrectSymbolException {
        String response = conversationService.getResponse(SELECT_COMMAND, ValidationType.ANYTHING);
        switch (response){
            case RESPONSE_CREATE:
                environment.create();
                return;
            case RESPONSE_READ_BY_ID:
                int idRead = Integer.parseInt( conversationService.getResponse(PRINT_ID, ValidationType.DIGIT));
                try {
                    environment.read_by_id(idRead);
                } catch (EntityNotFoundException e) {
                    e.printStackTrace();
                }
                return;
            case RESPONSE_READ_ALL:
                environment.readAll();
                return;
            case RESPONSE_DELETE_BY_ID:
                int idDel = Integer.parseInt(conversationService.getResponse(PRINT_ID, ValidationType.DIGIT));
                try {
                    environment.delete(idDel);
                } catch (EntityNotFoundException e) {
                    e.printStackTrace();
                }
                return;
            case RESPONSE_BACK:
                System.out.println(MAIN_MENU);
                return;
            case RESPONSE_EXIT:
                System.out.println(EXIT_MESSAGE);
                play = false;
                return;
            default:
                throw new IncorrectSymbolException(ANSWER_WRONG_RESPONSE);

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
        ArrayList<Homework> homeworks1 = new ArrayList<>();
        homeworks1.add(homework1);
        lecture1.setHomework(homeworks1);

        Lecture lecture2 = new Lecture();
        lecture2.setName("history");
        lecture2.setDescription("Dates and events");
        lecture2.setIdCourse(course.getId());
        lecture2.setMaterials(materials1);
        Homework homework2 = new Homework(lecture2.getId(), "2 hw");
        ArrayList<Homework> homeworks2 = new ArrayList<>();
        homeworks2.add(homework2);
        lecture2.setHomework(homeworks2);

        Lecture lecture3 = new Lecture();
        lecture3.setName("literature");
        lecture3.setDescription("Where do you think the person thought, although he may not have thought");
        lecture3.setIdCourse(course.getId());
        lecture3.setMaterials(materials1);
        Homework homework3 = new Homework(lecture3.getId(), "3 hw");
        ArrayList<Homework> homeworks3 = new ArrayList<>();
        homeworks3.add(homework3);
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
