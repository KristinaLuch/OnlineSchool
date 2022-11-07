package service;

import entity.Homework;
import entity.Lecture;
import entity.Materials;
import repository.LectureRep;

import java.util.Scanner;

public class LectureService {

    public LectureRep lectures;
    private Scanner scanner;
    private HomeworkService homeworkService;
    private MaterialService materialService;
    private static final String PRINT_SUBJECT = "Print subject";

    public LectureService(LectureRep lectures, Scanner scanner, HomeworkService homeworkService, MaterialService materialService) {
        this.lectures = lectures;
        this.scanner = scanner;
        this.homeworkService = homeworkService;
        this.materialService = materialService;
    }

    public Lecture create() {
        System.out.println(PRINT_SUBJECT);
        String subject = scanner.next();
        Homework homework = homeworkService.create();
        Materials materials = materialService.crete();
        Lecture lecture = new Lecture(subject, homework, materials);
        lectures.add(lecture);
        System.out.println("Lecture created. " + lecture);
        return lecture;

    }

}
