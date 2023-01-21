import models.Course;
import repository.*;
import service.*;
import service.school.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        CourseRep courses = new CourseRep(new Rep<>());
        LectureRep lectures = new LectureRep(new Rep<>());
        HomeworkRep homeworks = new HomeworkRep(new Rep<>());
        MaterialsRep materials = new MaterialsRep(new Rep<>());
        PersonRep persons = new PersonRep(new Rep<>());

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
