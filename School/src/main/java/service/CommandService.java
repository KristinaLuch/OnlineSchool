package service;

import constants.ValidationType;
import exceptions.EntityNotFoundException;
import exceptions.IncorrectSymbolException;
import loger.Log;
import models.ResourceType;
import models.school_object.*;
import service.conversation.ConversationService;
import service.log.LogService;
import service.school.*;
import util.serialization.ReserveCopy;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CommandService {

    private final ConversationService conversationService;
    private final CourseService courseService;
    private final LectureService lectureService;
    private final PersonService personService;
    private final AdditionalMaterialsService additionalMaterialsService;

    private final LogService logService;

    public CommandService(ConversationService conversationService, CourseService courseService,
                          LectureService lectureService, PersonService personService,
                          AdditionalMaterialsService additionalMaterialsService, LogService logService) {
        this.conversationService = conversationService;
        this.courseService = courseService;
        this.lectureService = lectureService;
        this.personService = personService;
        this.additionalMaterialsService = additionalMaterialsService;
        this.logService = logService;
    }

    private static final String START = "Select \"1\" for course, \"2\" for lecture, \"3\" for person, " +
            "\n\"4\" for additional_materials\", \"5\" for another function or \"exit\"";
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
    private static final String ANSWER_WRONG_RESPONSE = "Wrong response! ";
    public static final String PRINT_ID = "Print id: ";
    public static final String CREATED_MAX_LECTURES = "You already created 8 lectures. Stop it! I'm done";
    public static final String MAIN_MENU = "Main menu";
    public static final String EXIT_MESSAGE = "Well, it's your choice";

    public static final String ANOTHER_FUNCTION = "5";

    private static final int MAX_COUNT_OF_LECTURES = 8;

    private boolean play = true;

    public void startApp() {
        startCreate();
        while (play) {
            if (Lecture.getCount() == MAX_COUNT_OF_LECTURES) {
                System.out.println(CREATED_MAX_LECTURES);
                break;
            }
            conversationService.print(START);
            String response = conversationService.getResponse(PRINT_COMMAND, ValidationType.ANYTHING);
            SchoolService environment;
            try {
                Log.info(this.getClass().getName(), "startApp mtd, select environment");
                environment = selectEnvironment(response);
                if (environment == null){
                    continue;
                }
                selectCommand(environment);
            } catch (IncorrectSymbolException e) {
                Log.error(this.getClass().getName(), "method startApp dont have environment", e);
            }
        }
    }

    private SchoolService selectEnvironment(String response) throws IncorrectSymbolException {
        switch (response) {
            case RESPONSE_COURSE:
                return courseService;
            case RESPONSE_LECTURE:
                return lectureService;
            case RESPONSE_PERSON:
                return personService;
            case RESPONSE_ADD_MATERIALS:
                return additionalMaterialsService;
            case ANOTHER_FUNCTION:
                anotherFunction();
                return null;
            case RESPONSE_EXIT:
                System.out.println(EXIT_MESSAGE);
                System.exit(0);
            default:
                throw new IncorrectSymbolException(ANSWER_WRONG_RESPONSE);
        }
    }

    private void anotherFunction(){
        String response = conversationService.getResponse("Show teachers whose " +
                "last name begins up to the letter \"N\" - print \"1\", print logs messages - \"2\", \n" +
                "\"3\" - print lecture created the earliest with the most additional materials\n" +
                "\"4\" - print lecture group by teacher\n" +
                "\"5\" - print additionalMaterials group by lecture\n"+
                "\"6\" - print email with firstname and lastname\n"+
                "\"7\" - save students email in file",
                ValidationType.ANYTHING);
        switch (response) {
            case "1" -> personService.printTeacherBeforeN();
            case "2" -> logService.showMessage();
            case "3" -> lectureService.printLectureCreatedEarliestWithMostAddMaterials();
            case "4" -> lectureService.printLectureGroupByTeacher();
            case "5" -> lectureService.printAddMatGroupByLecture();
            case "6" -> personService.printEmailLastnameMap();
            case "7" -> personService.saveStudentsInFile();
            default -> {
                conversationService.print(ANSWER_WRONG_RESPONSE);
                Log.warning(this.getClass().getName(), "anotherFunction method");
            }
        }
    }

    private void selectCommand(SchoolService environment) throws IncorrectSymbolException {
        String methodName = "method selectCommand";
        String response = conversationService.getResponse(SELECT_COMMAND, ValidationType.ANYTHING);
        switch (response) {
            case RESPONSE_CREATE:
                environment.create();
                return;
            case RESPONSE_READ_BY_ID:
                int idRead = Integer.parseInt(conversationService.getResponse(PRINT_ID, ValidationType.DIGIT));
                try {
                    environment.read_by_id(idRead);
                } catch (EntityNotFoundException e) {
                    Log.error(this.getClass().getName(), methodName, e);
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
                    Log.error(this.getClass().getName(), methodName, e);
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
                try {
                    throw new IncorrectSymbolException(ANSWER_WRONG_RESPONSE);
                }catch (IncorrectSymbolException e){
                    Log.error(this.getClass().getName(), "method selectEnvironment", e);
                }
        }
    }

    public void startCreate() {
        System.out.println("Created one course with three lectures: ");
        Course course = new Course();
        course.setName("for zero");
        Materials materials1 = new Materials("materials");
        Person teacher = personService.createTeacherAdmin(course.getId(), "Stepan", "Bandera", "+3806660666",
                "bandera666@moskaliv.net");

        Person teacher1 = personService.createTeacherAdmin(course.getId(), "Taras", "Shevchenko", "+38025021814",
                "ne.z.moskalyamy@moskaliv.net");
        Person teacher2 = personService.createTeacherAdmin(course.getId(), "Ivan", "Franko", "+38027081856",
                "kamenjar@moskaliv.net");
        Person teacher3 = personService.createTeacherAdmin(course.getId(), "Lesya", "Ukrajinka", "+38025021871",
                "bezNadiiSpodivajus@moskaliv.net");
        Person teacher4 = personService.createTeacherAdmin(course.getId(), "Olha", "Knyaginya", "+380945964",
                "ne.tilky@moskaliv.net");

        Person student1 = personService.createStudentAdmin(course.getId(), "Dmytro", "Kuleba",
                "+380484214449", "dajte.tanky.dlja@moskaliv.net");
        Person student2 = personService.createStudentAdmin(course.getId(), "Volodymyr", "Zelenskyj",
                "+380333333333", "malo.zbroji.dlja@moskaliv.net");
        Person student3 = personService.createStudentAdmin(course.getId(), "Valerij", "Zaluznyj",
                "+380200300200", "teper_v_Ukraini@moskaliv.net");

        courseService.addPerson(course, teacher);
        courseService.addPerson(course, teacher1);
        courseService.addPerson(course, teacher2);
        courseService.addPerson(course, teacher3);
        courseService.addPerson(course, teacher4);
        courseService.addPerson(course, student1);
        courseService.addPerson(course, student2);
        courseService.addPerson(course, student3);

        Lecture lecture1 = new Lecture();
        lecture1.setName("math");
        lecture1.setDescription("For those who are not humanitarian");
        lecture1.setIdCourse(course.getId());
        lecture1.setMaterials(materials1);
        lecture1.setPersonId(teacher.getId());
        lecture1.setLectureDate(LocalDateTime.of(2011,01,11,12,00));
        Homework homework1 = new Homework(lecture1.getId(), "1 hw");
        ArrayList<Homework> homeworks1 = new ArrayList<>();
        homeworks1.add(homework1);
        lecture1.setHomework(homeworks1);
        AdditionalMaterials adMat1 = additionalMaterialsService.createAdmin(lecture1.getId(), "hz", ResourceType.URL);

        Lecture lecture2 = new Lecture();
        lecture2.setName("history");
        lecture2.setDescription("Dates and events");
        lecture2.setIdCourse(course.getId());
        lecture2.setMaterials(materials1);
        lecture2.setPersonId(teacher.getId());
        lecture2.setLectureDate(LocalDateTime.of(2012,01,11,12,00));
        Homework homework2 = new Homework(lecture2.getId(), "2 hw");
        ArrayList<Homework> homeworks2 = new ArrayList<>();
        homeworks2.add(homework2);
        lecture2.setHomework(homeworks2);

        Lecture lecture3 = new Lecture();
        lecture3.setName("literature");
        lecture3.setDescription("Where do you think the person thought, although he may not have thought");
        lecture3.setIdCourse(course.getId());
        lecture3.setMaterials(materials1);
        lecture3.setPersonId(teacher.getId());
        lecture3.setLectureDate(LocalDateTime.of(2013,01,11,12,00));
        Homework homework3 = new Homework(lecture3.getId(), "3 hw");
        ArrayList<Homework> homeworks3 = new ArrayList<>();
        homeworks3.add(homework3);
        lecture3.setHomework(homeworks3);

        AdditionalMaterials adMat2 = additionalMaterialsService.createAdmin(lecture2.getId(), "hz2", ResourceType.BOOK);
        AdditionalMaterials adMat3 = additionalMaterialsService.createAdmin(lecture3.getId(), "hz3", ResourceType.VIDEO);

        lectureService.addLectureToRep(lecture1);
        lectureService.addLectureToRep(lecture2);
        lectureService.addLectureToRep(lecture3);
        courseService.addLectureToList(course, lecture1);
        courseService.addLectureToList(course, lecture2);
        courseService.addLectureToList(course, lecture3);
        courseService.addToRep(course);

        ReserveCopy serializator = new ReserveCopy();
        serializator.backupCourse(course);
        System.out.println("Course: ");
        System.out.println(serializator.deserializationCourse());
    }

}
