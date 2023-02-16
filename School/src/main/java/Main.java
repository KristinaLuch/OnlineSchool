import comparator_add_materials.ComparatorId;
import comparator_add_materials.ComparatorLectureId;
import comparator_add_materials.ComparatorResourceType;
import models.ResourceType;
import models.school_object.AdditionalMaterials;
import repository.*;
import service.CommandService;
import service.ValidationService;
import service.conversation.ConversationService;
import service.school.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ComparatorId comparatorId = new ComparatorId();
        ComparatorLectureId comparatorIdLecture = new ComparatorLectureId();
        ComparatorResourceType comparatorResourceType = new ComparatorResourceType();

        CourseRep courses = new CourseRep(new ArrayList<>());
        LectureRep lectures = new LectureRep(new ArrayList<>());
        HomeworkRep homeworks = new HomeworkRep(new HashMap<>());
        MaterialsRep materials = new MaterialsRep(new ArrayList<>());
        PersonRep persons = new PersonRep(new ArrayList<>());
        AdditionalMaterialsRep additionalMaterialsRep = new AdditionalMaterialsRep(new TreeMap<>());


        ValidationService validationService = new ValidationService();
        ConversationService conversationService = new ConversationService(scanner, validationService);
        MaterialService materialService = new MaterialService(materials, conversationService);
        HomeworkService homeworkService = new HomeworkService(homeworks, conversationService);
        AdditionalMaterialsService additionalMaterialsService = new AdditionalMaterialsService(conversationService, additionalMaterialsRep,
                lectures, comparatorId, comparatorIdLecture, comparatorResourceType);
        LectureAssociatedService lectureAssociatedService = new LectureAssociatedService(conversationService, homeworkService,
                additionalMaterialsService, lectures);
        LectureService lectureService = new LectureService(lectures, conversationService, homeworkService, materialService, lectureAssociatedService, courses, persons);
        PersonService personService = new PersonService(persons, conversationService);

        CourseService courseService = new CourseService(courses, lectureService, personService, conversationService);

        CommandService commandService = new CommandService(conversationService, courseService, lectureService,
                personService, additionalMaterialsService);



        AdditionalMaterials additionalMaterials = new AdditionalMaterials();
        additionalMaterials.setLectureId(2);
        additionalMaterials.setResourceType(ResourceType.URL);
        AdditionalMaterials additionalMaterials2 = new AdditionalMaterials();
        additionalMaterials2.setLectureId(1);
        additionalMaterials2.setResourceType(ResourceType.URL);
        AdditionalMaterials additionalMaterials3 = new AdditionalMaterials();
        additionalMaterials3.setLectureId(3);
        additionalMaterials3.setResourceType(ResourceType.BOOK);
        AdditionalMaterials additionalMaterials4 = new AdditionalMaterials();
        additionalMaterials4.setLectureId(1);
        additionalMaterials4.setResourceType(ResourceType.VIDEO);
        additionalMaterialsRep.add(additionalMaterials);
        additionalMaterialsRep.add(additionalMaterials2);
        additionalMaterialsRep.add(additionalMaterials3);
        additionalMaterialsRep.add(additionalMaterials4);






        commandService.startApp();

    }
}
