import comparator_add_materials.ComparatorId;
import comparator_add_materials.ComparatorLectureId;
import comparator_add_materials.ComparatorResourceType;
import models.school_object.Person;
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

import static constants.FilesAddresses.PATH_PROPERTIES;

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
        ArrayList<Person> students = new ArrayList<>();
        ArrayList<Person> teachers = new ArrayList<>();

        StudentRep studentRep = new StudentRep(teachers, students);
        TeacherRep teacherRep = new TeacherRep(teachers, students);

        AdditionalMaterialsRep additionalMaterialsRep = new AdditionalMaterialsRep(new TreeMap<>());

       // String path = "C:\\StartIT_Academy\\Homework3\\School\\src\\main\\java\\file\\log.txt";

//       LogRepository logRep = new LogRepository();
        LogService logService = LogRepository.logServiceSt;

        ValidationService validationService = new ValidationService(studentRep, teacherRep);
        ConversationService conversationService = new ConversationService(scanner, validationService);
        MaterialService materialService = new MaterialService(materials, conversationService);
        HomeworkService homeworkService = new HomeworkService(homeworks, conversationService);
        AdditionalMaterialsService additionalMaterialsService = new AdditionalMaterialsService(conversationService, additionalMaterialsRep,
                lectures, comparatorId, comparatorIdLecture, comparatorResourceType);

//        ArrayList<Person> studentList = new ArrayList<>();
//        ArrayList<Person> teacherList = new ArrayList<>();
//
//        StudentRep studentRep = new StudentRep(teacherList, studentList);
//        TeacherRep teacherRep = new TeacherRep(teacherList, studentList);

        LectureAssociatedService lectureAssociatedService = new LectureAssociatedService(conversationService, homeworkService,
                additionalMaterialsService, lectures);
        LectureService lectureService = new LectureService(lectures, conversationService, homeworkService, materialService,
                lectureAssociatedService, courses, teacherRep);

        StudentService studentService = new StudentService(studentRep, teacherRep, conversationService);

        TeacherService teacherService = new TeacherService(studentRep, teacherRep, conversationService);
        CourseService courseService = new CourseService(courses, lectureService, studentService, conversationService);

//        TestService testService = new TestService(personService);
//        testService.runTest();
        PropertyLevel.setPath(PATH_PROPERTIES);

        Path path1 = Path.of("School\\src\\main\\java\\resources");
        LevelControl lc = new LevelControl(path1);

        LogRepository.setWriteLevel(PropertyLevel.getLevel());
        Thread control = new Thread(lc, "controlLevel thread");

        control.start();

        CommandService commandService = new CommandService(conversationService, courseService, lectureService,
                studentService, teacherService, additionalMaterialsService, logService);

//        logService.printInfoCountOfMiddleOfFile();

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
