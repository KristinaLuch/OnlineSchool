package service.school;

import constants.ValidationType;
import exceptions.EntityNotFoundException;
import exceptions.IncorrectSymbolException;
import loger.Log;
import models.school_object.Course;
import models.school_object.Lecture;
import models.school_object.Person;
import repository.school.impl.CourseRep;
import service.conversation.ConversationService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CourseService implements SchoolService {

    private final CourseRep courseRep;
    private final LectureService lectureService;
    private final PersonService personService;
    private final ConversationService conversationService;

    public static final String PRINT_COURSE_NAME = "Please, print course name";

    public static final String PRINT_ADD_LECTURES = """
            Please, add lectures.\s
            If you want to create new lecture enter 1,\s
            finish adding lectures enter 2""";

    public static final String PRINT_ADD_PERSON = "Please, add persons. \n" +
            "If you want to create new person enter 1, \n" +
            "finish adding persons enter 2";

    public static final String LECTURE_ADDED = "Lecture added";

    public static final String WRONG_COMMAND = "Wrong command";

    public static final String CASE_1 = "1";
    public static final String CASE_2 = "2";

    public CourseService(CourseRep courseRep, LectureService lectureService, PersonService personService,
                         ConversationService conversationService) {
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
        System.out.println("Course created");
        return course;
    }

    @Override
    public void read_by_id(int id) throws EntityNotFoundException {
        Course course = courseRep.get(id);
        System.out.println(course);
    }

    @Override
    public void readAll() {
        ArrayList<Course> courses = courseRep.getAll();
        Collections.sort(courses);
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    @Override
    public boolean delete(int id) throws EntityNotFoundException {
        return courseRep.delete(id);
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
                    try {
                        throw new IncorrectSymbolException(WRONG_COMMAND);
                    } catch (IncorrectSymbolException e) {
                        e.printStackTrace();
                    }
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
                    Optional<List<Person>> personsOpt = course.getPersons();
                    List<Person> persons = personsOpt.orElse(new ArrayList<>());
                    persons.add(person);
                    System.out.println("Person added");
                    break;
                case CASE_2:
                    return;
                default:
                    try {
                        throw new IncorrectSymbolException(WRONG_COMMAND);
                    } catch (IncorrectSymbolException e) {
                        Log.error(this.getClass().getName(), "method addPearson", e);
                    }
                    break;
            }
        }
    }


    public void addLectureToList(Course course, Lecture lecture) {
        List<Lecture> lectures = course.getLectures().orElse(new ArrayList<>());
        lecture.setIdCourse(course.getId());
        lectures.add(lecture);
        course.setLectures(lectures);
    }

    public boolean addPerson(Course course, Person person) {
        Optional<List<Person>> personsOpt = course.getPersons();
        List<Person> persons = personsOpt.orElse(new ArrayList<>());
        persons.add(person);
        course.setPersons(persons);
        return true;
    }

    public void addToRep(Course course) {
        courseRep.add(course);
    }

}
