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

        LogService logService = new LogService();
        LogRepository logRep = new LogRepository(logService);
        logService.setLogRepository(logRep);


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




        commandService.startApp();

    }
}
