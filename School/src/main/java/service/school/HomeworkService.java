package service.school;

import models.Homework;
import repository.HomeworkRep;

import java.util.Scanner;

public class HomeworkService extends SchoolService{

    private static final String PRINT_HOMEWORK = "Print homework";
    private Scanner scanner;

    public HomeworkService(HomeworkRep schoolRep, Scanner scanner) {
        this.schoolRep = schoolRep;
        this.scanner = scanner;
    }

    public Homework create() {
        System.out.println(PRINT_HOMEWORK);
        String homeworkString = scanner.next();
        Homework homework = new Homework(homeworkString);
        schoolRep.add(homework);
        return homework;
    }
}
