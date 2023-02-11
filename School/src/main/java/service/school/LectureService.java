package service.school;

import constants.ValidationType;
import exceptions.EntityNotFoundException;
import exceptions.IncorrectSymbolException;
import models.*;
import models.school_object.*;
import repository.CourseRep;
import repository.LectureRep;
import repository.PersonRep;
import service.conversation.ConversationService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class LectureService implements SchoolService{

    private final LectureRep lectureRep;
    private final  ConversationService conversationService;
    private final  HomeworkService homeworkService;
    private final  MaterialService materialService;
    private final  CourseRep courseRep;
    private final  PersonRep personRep;

    private static final String PRINT_LECTURE_NAME = "Print lecture name";

    private static final String PRINT_LECTURE_DESCRIPTION = "Print description";
    private static final String PRINT_ID_COURSE = "Print id course. If course don`t have id print 0";

    public static final String LECTURE_CREATED = "Lecture created. ";

    public LectureService(LectureRep lectureRep, ConversationService conversationService,
                          HomeworkService homeworkService, MaterialService materialService,
                          CourseRep courseRep, PersonRep personRep) {
        this.lectureRep = lectureRep;
        this.conversationService = conversationService;
        this.homeworkService = homeworkService;
        this.materialService = materialService;
        this.courseRep = courseRep;
        this.personRep = personRep;
    }

    public SchoolObject create() {
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
        System.out.println("Lecture of course (id) "+lecture.getIdCourse());
        System.out.println("Number of lectures - "+Lecture.getCount());
        conversationService.print(LECTURE_CREATED + lecture);
        return lecture;
    }

    @Override
    public void read_by_id(int id) throws EntityNotFoundException {
        System.out.println(lectureRep.get(id));
    }

    @Override
    public void readAll() {
        ArrayList<Lecture> lectures = lectureRep.getAll();
        for (Lecture lecture : lectures) {
            System.out.println(lecture);
        }
    }

    @Override
    public boolean delete(int id) throws EntityNotFoundException {
        lectureRep.delete(id);
        return true;
    }


    public Lecture createLectureInCourse(int courseId){
        Lecture lecture = createWithoutIdCourse();
        if (courseId > 0){
            lecture.setIdCourse(courseId);
        }
        addLectureToRep(lecture);
        conversationService.print(LECTURE_CREATED + lecture);
        return lecture;
    }

    private Lecture createWithoutIdCourse(){
        Lecture lecture = new Lecture();
        String name = conversationService.getResponse(PRINT_LECTURE_NAME, ValidationType.NAME);
        String description = conversationService.getResponse(PRINT_LECTURE_DESCRIPTION, ValidationType.DESCRIPTION);
        Materials materials = materialService.crete();
        lecture.setName(name);
        lecture.setDescription(description);
        lecture.setMaterials(materials);
        lecture = addHomeworks(lecture);
        lecture = addPerson(lecture);
        return lecture;
    }

    private Lecture addHomeworks(Lecture lecture){
        String response;
        Homework[] homeworks;
        Homework [] tmp;
        while (true) {
            response = conversationService.getResponse("Do you want add homework?", ValidationType.ANYTHING);
            if (response.equalsIgnoreCase("yes")) {
                Homework homework = homeworkService.create(lecture.getId());
                if (homework == null) {
                    conversationService.print("A value less than 0, is not a number or the id of a non-existing object");
                    continue;
                } else {
                    homeworks = lecture.getHomework();
                    if(homeworks == null||homeworks.length == 0){
                        homeworks = new Homework[1];
                        homeworks[0] = homework;
                        lecture.setHomework(homeworks);
                    } else {
                        tmp = Arrays.copyOf(homeworks, homeworks.length+1);
                        tmp[homeworks.length] = homework;
                        homeworks = tmp;
                        lecture.setHomework(homeworks);
                    }
                }
            } else if (response.equalsIgnoreCase("no")) {
                return lecture;
            } else {
                conversationService.print("Print yes or no");
                return addPerson(lecture);
            }
        }
    }

    private Lecture addPerson(Lecture lecture){
        String response;
        while (true) {
            response = conversationService.getResponse("Do you want add teacher?", ValidationType.ANYTHING);
            Person person;

            switch (response){
                case "yes":
                    person = addedPerson();
                    if (person == null) {
                        conversationService.print("A value less than 0, is not a number or the id of a non-existing object");
                        addPerson(lecture);
                    } else {
                        lecture.setPersonId(person.getId());
                        return lecture;
                    }
                case "no":
                    return lecture;
                default:
                    conversationService.print("Print yes or no");
                    return addPerson(lecture);
            }
        }
    }

    private Person addedPerson(){
        Person person = null;
        String idStr = conversationService.getResponse("Print teacher`s peron id", ValidationType.DIGIT);
        if (idStr == null){
            return null;
        }
        int id = Integer.parseInt(idStr);
        if(id <= 0){
            conversationService.print("Number must be greater than 0");
            return null;
        }

        try {
            person = personRep.get(id);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
        }

        if(person != null){
            if (person.getRole() == Role.TEACHER){
                return person;
            }
            else {
                conversationService.print("Person must be a teacher");
                return null;
            }
        }
        return null;
    }

    private Course getCourseFromId(){
        String courseIdstr;
        int courseId;
        Course course;
        while (true) {
            courseIdstr = conversationService.getResponse(PRINT_ID_COURSE, ValidationType.DIGIT);
            if(courseIdstr != null){
                courseId = Integer.parseInt(courseIdstr);
                if (courseId == 0){
                    return null;
                }
                try {
                    course = courseRep.get(courseId);
                    return course;
                } catch (EntityNotFoundException e) {
                    e.printStackTrace();
                }
            }
            else {
                try {
                    throw new IncorrectSymbolException("Wrong symbols! Print id course = 0, if lectures don`t have course");
                } catch (IncorrectSymbolException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void addLectureToRep(Lecture lecture){
        materialService.addToRep(lecture.getMaterials());
        lectureRep.add(lecture);

        Homework[] homeworks = lecture.getHomework();
        for (Homework homework : homeworks) {
            homeworkService.addToRep(homework);
        }
    }

}
