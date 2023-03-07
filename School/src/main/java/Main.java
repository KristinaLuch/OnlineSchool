import comparator_add_materials.ComparatorId;
import comparator_add_materials.ComparatorLectureId;
import comparator_add_materials.ComparatorResourceType;
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

        CourseRep courses = new CourseRep(new ArrayList<>());
        LectureRep lectures = new LectureRep(new ArrayList<>());
        HomeworkRep homeworks = new HomeworkRep(new HashMap<>());
        MaterialsRep materials = new MaterialsRep(new ArrayList<>());
        PersonRep persons = new PersonRep(new ArrayList<>());
        AdditionalMaterialsRep additionalMaterialsRep = new AdditionalMaterialsRep(new TreeMap<>());

        String path = "School/src/main/java/file/myFile.txt";
        LogService logService = new LogService(path);
        LogRepository logRep = new LogRepository(logService);


        ValidationService validationService = new ValidationService(logRep);
        ConversationService conversationService = new ConversationService(scanner, validationService, logRep);
        MaterialService materialService = new MaterialService(materials, conversationService);
        HomeworkService homeworkService = new HomeworkService(homeworks, conversationService, logRep);
        AdditionalMaterialsService additionalMaterialsService = new AdditionalMaterialsService(conversationService, additionalMaterialsRep,
                lectures, comparatorId, comparatorIdLecture, comparatorResourceType, logRep);
        LectureAssociatedService lectureAssociatedService = new LectureAssociatedService(conversationService, homeworkService,
                additionalMaterialsService, lectures, logRep);
        LectureService lectureService = new LectureService(lectures, conversationService, homeworkService, materialService,
                lectureAssociatedService, courses, persons, logRep);
        PersonService personService = new PersonService(persons, conversationService, logRep);

        CourseService courseService = new CourseService(courses, lectureService, personService, conversationService, logRep);

        CommandService commandService = new CommandService(conversationService, courseService, lectureService,
                personService, additionalMaterialsService, logRep);
        LogRepository.setWriteLevel(PropertyLevel.getLevel());


//        TestService testService = new TestService(personService);
//        testService.runTest();
        String pathProperties = "School\\src\\main\\java\\resources\\logLevel.properties";
        PropertyLevel.setPath(pathProperties);

        Path path1 = Path.of("C:\\StartIT_Academy\\Homework3\\School\\src\\main\\java\\resources\\logLevel.properties");
        LevelControl lc = new LevelControl(path1);
        LogRepository.setWriteLevel(PropertyLevel.getLevel());
        Thread control = new Thread(lc, "controlLevel thread");


        control.start();
        commandService.startApp();

    }
}
