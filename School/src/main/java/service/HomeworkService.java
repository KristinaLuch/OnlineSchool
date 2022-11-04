package service;

import entity.Course;
import entity.Homework;
import repository.HomeworkRep;

import java.util.ArrayList;
import java.util.Scanner;

public class HomeworkService {

    private static final String PRINT_HOMEWORK = "Print homework";

    public HomeworkRep homeworks;

    private Scanner scanner;

    public HomeworkService(HomeworkRep homeworks, Scanner scanner) {
        this.homeworks = homeworks;
        this.scanner = scanner;
    }

    public Homework create(){
        System.out.println(PRINT_HOMEWORK);
        String homeworkString = scanner.next();
        Homework homework = new Homework(homeworkString);
        homeworks.add(homework);
        return homework;
    }
}
