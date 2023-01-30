package service.school;

import constants.ValidationType;
import models.*;
import repository.CourseRep;
import service.conversation.ConversationService;

import java.util.ArrayList;
import java.util.List;

public class CourseService {

    private CourseRep courseRep;
    private LectureService lectureService;
    private PersonService personService;

    private ConversationService conversationService;

    public static final String PRINT_COURSE_NAME = "Please, print course name";

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

    public CourseService(CourseRep courseRep, LectureService lectureService, PersonService personService, ConversationService conversationService) {
        this.courseRep = courseRep;
        this.lectureService = lectureService;
        this.personService = personService;
        this.conversationService = conversationService;
    }

    public Course create() {
        Course course = new Course();
        String name = conversationService.getResponse(PRINT_COURSE_NAME, ValidationType.NAME);
        course.setName(name);
        addLectures(course);
        addPearson(course);
        courseRep.add(course);
        conversationService.print(course.toString());
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

    public boolean addLecture(Course course, Lecture lecture) {
        if (lecture == null||course == null){
            return false;
        }
        List<Lecture> lectures = course.getLectures();
        lectures.add(lecture);
        course.setLectures(lectures);
        return true;
    }

    public boolean addPerson(Course course, Person person) {
        if (person == null||course == null){
            return false;
        }
        List<Person> persons = course.getPersons();
        persons.add(person);
        course.setPersons(persons);
        return true;
    }

    public void addToRep(Course course){
        if (course == null){
            return;
        }
        courseRep.add(course);
    }

}
