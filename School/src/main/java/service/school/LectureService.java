package service.school;

import models.Course;
import models.Homework;
import models.Lecture;
import models.Materials;
import repository.CourseRep;
import repository.LectureRep;

import java.util.List;
import java.util.Scanner;

public class LectureService extends SchoolService {
    
    private Scanner scanner;
    private HomeworkService homeworkService;
    private MaterialService materialService;

    private CourseRep courseRep;
    private static final String PRINT_SUBJECT = "Print subject";
    private static final String PRINT_ID_COURSE = "Print id course. If course don`t have id print 0";

    public LectureService(LectureRep lectureRep, Scanner scanner, HomeworkService homeworkService, MaterialService materialService,
        CourseRep courseRep) {
        this.schoolRep = lectureRep;
        this.scanner = scanner;
        this.homeworkService = homeworkService;
        this.materialService = materialService;
        this.courseRep = courseRep;
    }

    public Lecture create() {
        int courseId;
        Course course = getCourseFromId();
        Lecture lecture = createWithoutIdCourse();
        if (course != null){
            courseId = course.getId();
            lecture.setIdCourse(courseId);
            List<Lecture> lectures = course.getLectures();
            lectures.add(lecture);
            course.setLectures(lectures);
        }
        addLectureToRep(lecture);
        System.out.println("Lecture created. " + lecture);
        return lecture;
    }


    public Lecture createLectureInCourse(int courseId){
        Lecture lecture = createWithoutIdCourse();
        if (courseId > 0){
            lecture.setIdCourse(courseId);
        }
        addLectureToRep(lecture);
        System.out.println("Lecture created. " + lecture);
        return lecture;
    }

    private Lecture createWithoutIdCourse(){
        System.out.println(PRINT_SUBJECT);
        String subject = scanner.next();
        Homework homework = homeworkService.create();
        Materials materials = materialService.crete();
        Lecture lecture = new Lecture(subject, homework, materials);
        return lecture;
    }

    private Course getCourseFromId(){
        int courseId;
        Course course;
        boolean isInt;
        while (true) {
            System.out.println(PRINT_ID_COURSE);
            isInt = scanner.hasNextInt();
            if(isInt){
                courseId = scanner.nextInt();
                if (courseId == 0){
                    return null;
                }
                if (courseRep.getById(courseId) != null){
                    course = (Course) courseRep.getById(courseId);
                    return course;
                } else {
                    System.out.println("\nCourse does not exist \n");
                }

            }
            else {
                System.out.println("Wrong symbols! Print id course = 0, if lectures don`t have course");
                scanner.next();
            }
        }
    }

    public void addLectureToRep(Lecture lecture){
        homeworkService.addToRep(lecture.getHomework());
        materialService.addToRep(lecture.getMaterials());
        schoolRep.add(lecture);
    }

}
