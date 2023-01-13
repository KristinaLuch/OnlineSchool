package service.school;

import constants.ValidationType;
import models.*;
import repository.CourseRep;
import repository.SchoolRep;
import service.ConversationService;

import java.io.FilterOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CourseService extends SchoolService {
    private LectureService lectureService;
    private PersonService personService;

    private ConversationService conversationService;

    public static final String PRINT_ADD_LECTURES = "Please, add lectures. \n" +
            "If you want to create new lecture enter 1, \n" +
            "finish adding lectures enter 2";

    public static final String PRINT_ADD_PERSON = "Please, add persons. \n" +
            "If you want to create new person enter 1, \n" +
            "finish adding persons enter 2";

    public static final String LECTURE_ADDED = "Lecture added";

    public static final String WRONG_COMMAND = "Wrong command";

    public static final String CASE_1 = "1";
    public static final String CASE_2 = "2";

    public CourseService(CourseRep schoolRep, LectureService lectureService, PersonService personService, ConversationService conversationService) {
        super(schoolRep);
        this.lectureService = lectureService;
        this.personService = personService;
        this.conversationService = conversationService;
    }

    public Course create() {
        Course course = new Course();
        addLectures(course);
        addPearson(course);
        schoolRep.add(course);
        System.out.println(course);
        return course;
    }

    private void addLectures(Course course) {
        while (true) {
            String response = conversationService.getResponse(PRINT_ADD_LECTURES, ValidationType.ANYTHING);
            switch (response) {
                case CASE_1:
                    Lecture lecture = lectureService.createLectureInCourse(course.getId());
                    addLectureToList(course, lecture);
                    conversationService.print(LECTURE_ADDED);
                    break;
                case CASE_2:
                    return;
                default:
                    conversationService.print(WRONG_COMMAND);
                    addLectures(course);
                    break;
            }
        }
    }

    private void addPearson(Course course) {
        while (true) {
            String response = conversationService.getResponse(PRINT_ADD_PERSON, ValidationType.ANYTHING);
            switch (response) {
                case CASE_1:
                    Person person = personService.create();
                    person.setCourseID(course.getId());
                    List<Person> persons = course.getPersons();
                    if (persons == null) {
                        persons = new ArrayList<>();
                    }
                    persons.add(person);
                    System.out.println("Teacher added");
                    break;
                case CASE_2:
                    return;
                default:
                    System.out.println(WRONG_COMMAND);
                    break;
            }
        }
    }


    public void addLectureToList(Course course, Lecture lecture) {
        List<Lecture> lectures = course.getLectures();
        if (lectures == null) {
            lectures = new ArrayList<Lecture>();
        }
        lecture.setIdCourse(course.getId());
        lectures.add(lecture);
        course.setLectures(lectures);
    }

}
