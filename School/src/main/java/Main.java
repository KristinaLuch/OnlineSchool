import models.Course;
import repository.*;
import service.CommandService;
import service.ValidationService;
import service.conversation.ConversationService;
import service.school.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        CourseRep courses = new CourseRep(new ArrayList<>());
        LectureRep lectures = new LectureRep(new ArrayList<>());
        HomeworkRep homeworks = new HomeworkRep(new ArrayList<>());
        MaterialsRep materials = new MaterialsRep(new ArrayList<>());
        PersonRep persons = new PersonRep(new ArrayList<>());

        ValidationService validationService = new ValidationService();
        ConversationService conversationService = new ConversationService(scanner, validationService);
        MaterialService materialService = new MaterialService(materials, conversationService);
        HomeworkService homeworkService = new HomeworkService(homeworks, conversationService);
        LectureService lectureService = new LectureService(lectures, conversationService, homeworkService, materialService, courses, persons);
        PersonService personService = new PersonService(persons, conversationService);

        CourseService courseService = new CourseService(courses, lectureService, personService, conversationService);

        CommandService commandService = new CommandService(scanner, courseService, lectureService,
                personService, courses, lectures, persons, conversationService);

        commandService.startApp();

    }
}
