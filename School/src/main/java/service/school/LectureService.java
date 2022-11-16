package service.school;

import models.Homework;
import models.Lecture;
import models.Materials;
import repository.LectureRep;

import java.util.Scanner;

public class LectureService extends SchoolService {
    
    private Scanner scanner;
    private HomeworkService homeworkService;
    private MaterialService materialService;
    private static final String PRINT_SUBJECT = "Print subject";

    public LectureService(LectureRep schoolRep, Scanner scanner, HomeworkService homeworkService, MaterialService materialService) {
        this.schoolRep = schoolRep;
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
        schoolRep.add(lecture);
        System.out.println("Lecture created. " + lecture);
        return lecture;
    }

    public void addLectureToRep(Lecture lecture){
        homeworkService.addToRep(lecture.getHomework());
        materialService.addToRep(lecture.getMaterials());
        schoolRep.add(lecture);
    }

}
