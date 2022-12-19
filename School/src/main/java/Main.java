import repository.*;
import service.*;
import service.school.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

         CourseRep courses = new CourseRep();
         LectureRep lectures = new LectureRep();
         HomeworkRep homeworks = new HomeworkRep();
         MaterialsRep materials = new MaterialsRep();
         PersonRep persons = new PersonRep();

        MaterialService materialService = new MaterialService(materials, scanner);
        HomeworkService homeworkService = new HomeworkService(homeworks, scanner);
        LectureService lectureService = new LectureService(lectures, scanner, homeworkService, materialService, courses, persons);
        PersonService personService = new PersonService(persons, scanner);

        CourseService courseService = new CourseService(courses, scanner, lectureService, personService);

        CommandService commandService = new CommandService(scanner, courseService, lectureService, personService, courses, lectures, persons);

        commandService.startApp();

    }
}
