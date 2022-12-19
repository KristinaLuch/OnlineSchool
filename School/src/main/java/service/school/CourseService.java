package service.school;

import models.*;
import repository.CourseRep;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CourseService extends SchoolService {
    private Scanner scanner;
    private LectureService lectureService;
    private PersonService personService;

    public CourseService(CourseRep schoolRep, Scanner scanner, LectureService lectureService, PersonService personService) {
        this.schoolRep = schoolRep;
        this.scanner = scanner;
        this.lectureService = lectureService;
        this.personService = personService;
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
            System.out.println("Please, add lectures. \n" +
                    "If you want to create new lecture enter 1, \n" +
                    "finish adding lectures enter 2");
            String response = scanner.next();
            switch (response) {
                case "1":
                    Lecture lecture = lectureService.createLectureInCourse(course.getId());
                    addLectureToList(course, lecture);
                    System.out.println("Lecture added");
                    break;
                case "2":
                    return;
                default:
                    System.out.println("Wrong command");
                    addLectures(course);
                    break;
            }
        }
    }

    private void addPearson(Course course) {
        while (true) {
            System.out.println("Please, add persons. \n" +
                    "If you want to create new person enter 1, \n" +
                    "finish adding persons enter 2");
            String response = scanner.next();
            switch (response) {
                case "1":
                    Person person = personService.create();
                    person.setCourseID(course.getId());
                    List<Person> persons = course.getPersons();
                    if (persons == null) {
                        persons = new ArrayList<>();
                    }
                    persons.add(person);
                    System.out.println("Teacher added");
                    break;
                case "2":
                    return;
                default:
                    System.out.println("Wrong command");
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
