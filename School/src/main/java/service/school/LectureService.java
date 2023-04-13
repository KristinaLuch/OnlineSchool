package service.school;

import constants.ValidationType;
import exceptions.EntityNotFoundException;
import exceptions.IncorrectSymbolException;
import loger.Log;
import models.school_object.*;
import repository.school.impl.CourseRep;
import repository.school.impl.LectureRep;
import repository.school.impl.TeacherRep;
import service.conversation.ConversationService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class LectureService implements SchoolService {

    private final LectureRep lectureRep;
    private final ConversationService conversationService;
    private final HomeworkService homeworkService;
    private final MaterialService materialService;
    private final CourseRep courseRep;
    private final TeacherRep teacherRep;

    private final LectureAssociatedService lectureAssociatedService;

    private static final String PRINT_LECTURE_NAME = "Print lecture name";

    private static final String PRINT_LECTURE_DESCRIPTION = "Print description";
    private static final String PRINT_ID_COURSE = "Print id course. If course don`t have id print 0";

    public static final String LECTURE_CREATED = "Lecture created. ";

    public LectureService(LectureRep lectureRep, ConversationService conversationService,
                          HomeworkService homeworkService, MaterialService materialService,
                          LectureAssociatedService lectureAssociatedService, CourseRep courseRep,
                           TeacherRep teacherRep) {
        this.lectureRep = lectureRep;
        this.conversationService = conversationService;
        this.homeworkService = homeworkService;
        this.materialService = materialService;
        this.lectureAssociatedService = lectureAssociatedService;
        this.courseRep = courseRep;
        this.teacherRep = teacherRep;
    }

    public Lecture create() {
        int courseId;
        Course course = getCourseFromId();
        Lecture lecture = createWithoutIdCourse();
        if (course != null) {
            courseId = course.getId();
            lecture.setIdCourse(courseId);
            List<Lecture> lectures = course.getLectures().orElse(new ArrayList<>());
            lectures.add(lecture);
            course.setLectures(lectures);
        }
        addLectureToRep(lecture);
        System.out.println("Lecture of course (id) " + lecture.getIdCourse());
        System.out.println("Number of lectures - " + Lecture.getCount());
        conversationService.print(LECTURE_CREATED + lecture);
        return lecture;
    }

    @Override
    public void read_by_id(int id) throws EntityNotFoundException {
        System.out.println(lectureRep.get(id));
        lectureAssociatedService.showAssociated(id);

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


    public Lecture createLectureInCourse(int courseId) {
        Lecture lecture = createWithoutIdCourse();
        if (courseId > 0) {
            lecture.setIdCourse(courseId);
        }
        addLectureToRep(lecture);
        conversationService.print(LECTURE_CREATED + lecture);
        return lecture;
    }

    private Lecture createWithoutIdCourse() {
        Lecture lecture = new Lecture();
        String name = conversationService.getResponse(PRINT_LECTURE_NAME, ValidationType.NAME);
        lecture.setLectureDate(lectureDate());
        String description = conversationService.getResponse(PRINT_LECTURE_DESCRIPTION, ValidationType.DESCRIPTION);
        Materials materials = materialService.create();
        lecture.setName(name);
        lecture.setDescription(description);
        lecture.setMaterials(materials);
        lecture = addHomeworks(lecture);
        lecture = addPerson(lecture);
        return lecture;
    }

    private Lecture addHomeworks(Lecture lecture) {
        String response;
        ArrayList<Homework> homeworks;
        while (true) {
            response = conversationService.getResponse("Do you want add homework?", ValidationType.ANYTHING);
            if (response.equalsIgnoreCase("yes")) {
                Homework homework = homeworkService.create(lecture);
                    homeworks = lecture.getHomework().orElse(new ArrayList<>());
                    homeworks.add(homework);
                    lecture.setHomework(homeworks);
            } else if (response.equalsIgnoreCase("no")) {
                return lecture;
            } else {
                conversationService.print("Print yes or no");
                Log.warning(this.getClass().getName(), "method addHomeworks");
                return addHomeworks(lecture);
            }
        }
    }


    private Lecture addPerson(Lecture lecture) {
        String response;
        while (true) {
            response = conversationService.getResponse("Do you want add teacher?", ValidationType.ANYTHING);
            Person person;

            switch (response) {
                case "yes":
                    person = addedPerson();
                    if (person == null) {
                        conversationService.print("A value less than 0, is not a number or the id of a non-existing object");
                        addPerson(lecture);
                    } else {
                        lecture.setTeacherId(person.getId());
                    }
                    break;
                case "no":
                    return lecture;
                default:
                    conversationService.print("Print yes or no");
                    return addPerson(lecture);
            }
        }
    }

    private Person addedPerson() {
        Person person = null;
        String idStr = conversationService.getResponse("Print teacher`s person id", ValidationType.DIGIT);
        int id = Integer.parseInt(idStr);
        if (id <= 0) {
            conversationService.print("Number must be greater than 0");
            return null;
        }

        try {
            person = teacherRep.get(id);
        } catch (EntityNotFoundException e) {
            Log.error(this.getClass().getName(), "method addedPerson", e);
        }

        return person;
    }

    private Course getCourseFromId() {
        String courseIdstr;
        int courseId;
        Course course;
        while (true) {
            courseIdstr = conversationService.getResponse(PRINT_ID_COURSE, ValidationType.DIGIT);
            if (courseIdstr != null) {
                courseId = Integer.parseInt(courseIdstr);
                if (courseId == 0) {
                    return null;
                }
                try {
                    course = courseRep.get(courseId);
                    return course;
                } catch (EntityNotFoundException e) {
                    Log.error(this.getClass().getName(), "method getCourseFromId", e);
                }
            } else {
                try {
                    throw new IncorrectSymbolException("Wrong symbols! Print id course = 0, if lectures don`t have course");
                } catch (IncorrectSymbolException e) {
                    Log.error(this.getClass().getName(), "method getCourseFromId", e);
                }
            }
        }
    }

    public void addLectureToRep(Lecture lecture) {
        materialService.addToRep(lecture.getMaterials());
        lectureRep.add(lecture);
        ArrayList<Homework> homeworks = lecture.getHomework().orElse(new ArrayList<>());
        for (Homework homework : homeworks) {
            homeworkService.addToRep(homework);
        }
    }

    private LocalDateTime lectureDate(){
        String date = conversationService.getResponse("Print lecture date(format example: \"20.07.2010 14:10:05\"): ", ValidationType.LECTURE_DATE);
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
    }

    public void printLectureCreatedEarliestWithMostAddMaterials(){
        ArrayList<Lecture> lectures = lectureRep.getAll();
        if (lectures.size() == 0){
            System.out.println("Lectures don`t exist");
            return;
        }
        Lecture firstLectureEarliestDate = lectures.stream().min(Comparator.comparing(Lecture::getCreationDate)).get();
        List<Lecture> lecturesEarlyDate = lectures.stream()
                .filter(lecture -> lecture.getCreationDate().equals(firstLectureEarliestDate.getCreationDate())).toList();

        Optional<Lecture> optionalLectureCreatedEarliestWithMostAddMaterials = lectureAssociatedService.maxAddMat(lecturesEarlyDate);

        optionalLectureCreatedEarliestWithMostAddMaterials.ifPresent(lecture -> System.out.println("Lecture created the earliest with the most additional materials: "
                + lecture));
        if (optionalLectureCreatedEarliestWithMostAddMaterials.isEmpty()){
            System.out.println("Lecture created the earliest with the most additional materials not found");
        }
    }

    public void printLectureGroupByTeacher() {
        Map<Person, List<Lecture>> lectureGroupByTeacher  = lectureRep.getAll()
                .stream().collect(Collectors.groupingBy(lecture -> getTeacherForGroup(lecture.getPersonId())));
        System.out.println(lectureGroupByTeacher);
    }

    private Person getTeacherForGroup(int id){
        try {
            return teacherRep.get(id);
        } catch (EntityNotFoundException e) {
            Log.error(this.getClass().getName(), "getTeacherForGroup mtd", e);
        }
        return null;
    }

    public void printAddMatGroupByLecture(){
       lectureAssociatedService.printAddMatGroupByLecture();
    }

}
