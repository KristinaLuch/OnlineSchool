import comparator_add_materials.ComparatorId;
import comparator_add_materials.ComparatorLectureId;
import comparator_add_materials.ComparatorResourceType;
import db.DataBase;
import repository.log.LogRepository;
import repository.school.impl.*;
import service.CommandService;
import service.ValidationService;
import service.conversation.ConversationService;
import service.log.LogService;
import service.school.*;
import util.LevelControl;
import util.PropertyLevel;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ComparatorId comparatorId = new ComparatorId();
        ComparatorLectureId comparatorIdLecture = new ComparatorLectureId();
        ComparatorResourceType comparatorResourceType = new ComparatorResourceType();

        DataBase dataBase = new DataBase();
        CourseRep courses = new CourseRep(new ArrayList<>());
        LectureRep lectures = new LectureRep(new ArrayList<>());
        HomeworkRep homeworks = new HomeworkRep(new HashMap<>());
        MaterialsRep materials = new MaterialsRep(new ArrayList<>());
        PersonRep persons = new PersonRep(new ArrayList<>());
        AdditionalMaterialsRep additionalMaterialsRep = new AdditionalMaterialsRep(new TreeMap<>(), dataBase);

       // String path = "C:\\StartIT_Academy\\Homework3\\School\\src\\main\\java\\file\\log.txt";
        String path = "src/main/java/file/log.txt";
        LogService logService = new LogService(path);
        LogRepository logRep = new LogRepository(logService);


        ValidationService validationService = new ValidationService(persons);
        ConversationService conversationService = new ConversationService(scanner, validationService);
        MaterialService materialService = new MaterialService(materials, conversationService);
        HomeworkService homeworkService = new HomeworkService(homeworks, conversationService);
        AdditionalMaterialsService additionalMaterialsService = new AdditionalMaterialsService(conversationService, additionalMaterialsRep,
                lectures, comparatorId, comparatorIdLecture, comparatorResourceType);
        LectureAssociatedService lectureAssociatedService = new LectureAssociatedService(conversationService, homeworkService,
                additionalMaterialsService, lectures);
        LectureService lectureService = new LectureService(lectures, conversationService, homeworkService, materialService,
                lectureAssociatedService, courses, persons);
        PersonService personService = new PersonService(persons, conversationService);

        CourseService courseService = new CourseService(courses, lectureService, personService, conversationService);

//        TestService testService = new TestService(personService);
//        testService.runTest();
        String pathProperties = "src\\main\\java\\resources\\logLevel.properties";
        PropertyLevel.setPath(pathProperties);

        Path path1 = Path.of("src\\main\\java\\resources");
        LevelControl lc = new LevelControl(path1);

        LogRepository.setWriteLevel(PropertyLevel.getLevel());
        Thread control = new Thread(lc, "controlLevel thread");

        control.start();

        CommandService commandService = new CommandService(conversationService, courseService, lectureService,
                personService, additionalMaterialsService, logService);

        logService.printInfoCountOfMiddleOfFile();

        commandService.startApp();
//        System.out.println("before");
//        lectures.printLectureBeforeDate(LocalDateTime.of(2012,01,11,12,00));
//        System.out.println("after");
//        lectures.printLectureAfterDate(LocalDateTime.of(2012,01,11,12,00));
//        System.out.println("From .. to ..");
//        lectures.printLectureFromDateToDate(LocalDateTime.of(2009,01,11,12,00), LocalDateTime.of(2013,01,9,12,00));
//        additionalMaterialsService.printListMaterials();



    }
}
