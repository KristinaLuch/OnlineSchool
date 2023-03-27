package service.school;

import comparator_add_materials.ComparatorId;
import comparator_add_materials.ComparatorLectureId;
import comparator_add_materials.ComparatorResourceType;
import constants.ValidationType;
import exceptions.EntityNotFoundException;
import exceptions.IncorrectSymbolException;
import loger.Log;
import models.ResourceType;
import models.school_object.AdditionalMaterials;
import models.school_object.Lecture;
import repository.school.impl.AdditionalMaterialsRep;
import repository.school.impl.LectureRep;
import service.conversation.ConversationService;

import java.util.*;

import static models.ResourceType.*;

public class AdditionalMaterialsService implements SchoolService {

    private final ConversationService conversationService;
    private final AdditionalMaterialsRep additionalMaterialsRep;
    private final LectureRep lectureRep;
    private final ComparatorId comparatorId;
    private final ComparatorLectureId comparatorLectureId;

    private ComparatorResourceType comparatorResourceType;


    public AdditionalMaterialsService(ConversationService conversationService, AdditionalMaterialsRep additionalMaterialsRep,
                                      LectureRep lectureRep, ComparatorId comparatorId,
                                      ComparatorLectureId comparatorLectureId,
                                      ComparatorResourceType comparatorResourceType) {
        this.conversationService = conversationService;
        this.additionalMaterialsRep = additionalMaterialsRep;
        this.lectureRep = lectureRep;
        this.comparatorId = comparatorId;
        this.comparatorLectureId = comparatorLectureId;
        this.comparatorResourceType = comparatorResourceType;
    }

    public static final String PRINT_NAME = "Print name:";
    public static final String PRINT_RESOURCE_TYPE = "Print resource type(\"book\", \"video\" or \"url\"):";

    public static final String PRINT_LECTURE_ID = "Print lecture id:";

    @Override
    public AdditionalMaterials create() {
        return create(getLectureId());
    }

    public AdditionalMaterials create(int lectureId) {
        AdditionalMaterials additionalMaterials = new AdditionalMaterials();
        additionalMaterials.setName(conversationService.getResponse(PRINT_NAME, ValidationType.NAME));
        additionalMaterials.setResourceType(getResourceType());
        additionalMaterials.setLectureId(lectureId);
        additionalMaterialsRep.add(additionalMaterials);
        conversationService.print(additionalMaterials.toString());
        conversationService.print("Created");
        return additionalMaterials;
    }

    public AdditionalMaterials createAdmin(int lectureId, String name, ResourceType type) {
        AdditionalMaterials additionalMaterials = new AdditionalMaterials();
        additionalMaterials.setName(name);
        additionalMaterials.setResourceType(type);
        additionalMaterials.setLectureId(lectureId);
        additionalMaterialsRep.add(additionalMaterials);
        return additionalMaterials;
    }

    private int getLectureId() {
        int lectureId = Integer.parseInt(conversationService.getResponse(PRINT_LECTURE_ID, ValidationType.DIGIT));
        try {
            lectureRep.get(lectureId);
            return lectureId;
        } catch (EntityNotFoundException e) {
            Log.error(this.getClass().getName(), "method getLectureId", e);
            return getLectureId();
        }
    }

    private ResourceType getResourceType() {
        String response = conversationService.getResponse(PRINT_RESOURCE_TYPE, ValidationType.ANYTHING);
        switch (response) {
            case "book" -> {
                return BOOK;
            }
            case "video" -> {
                return VIDEO;
            }
            case "url" -> {
                return URL;
            }
            default -> {
                try {
                    throw new IncorrectSymbolException("Choose \"book\", \"video\" or \"url\"");
                } catch (IncorrectSymbolException e) {
                    Log.error(this.getClass().getName(), "method getResourceType", e);
                    return getResourceType();
                }
            }
        }
    }

    @Override
    public void read_by_id(int id) throws EntityNotFoundException {
        AdditionalMaterials additionalMaterials = additionalMaterialsRep.get(id);
        System.out.println(additionalMaterials.toString());
    }

    @Override
    public void readAll() {

        Map<Integer, ArrayList<AdditionalMaterials>> map = additionalMaterialsRep.getAll();

        ArrayList<AdditionalMaterials> additionalMaterialsList = new ArrayList<>();

        map.forEach((key, value) -> additionalMaterialsList.addAll(value));

        Comparator comparator = getComparator();


        if (comparator != null) {
            additionalMaterialsList.sort(comparator);
        }
        for (AdditionalMaterials additionalMaterials : additionalMaterialsList) {
            System.out.println(additionalMaterials);
        }
    }

    private Comparator getComparator() {
        String result = conversationService.getResponse("Select an option to sort: 1. default(id); " +
                "2. Lecture id; 3. ResourceType", ValidationType.DIGIT);
        switch (result) {
            case "1":
                return comparatorId;
            case "2":
                return comparatorLectureId;
            case "3":
                return comparatorResourceType;
            default:
                try {
                    throw new IncorrectSymbolException("Choose 1, 2 or 3");
                } catch (IncorrectSymbolException e) {
                    Log.error(this.getClass().getName(), "method getComparator", e);
                    return getComparator();
                }
        }
    }

    @Override
    public boolean delete(int id) throws EntityNotFoundException {
        additionalMaterialsRep.delete(id);
        return true;
    }

    public ArrayList<AdditionalMaterials> getAdditionalMaterialsFromLecture(int lectureId) {
        return additionalMaterialsRep.getAdditionalMaterials(lectureId);
    }

    public void printHomeworksFromLecture(int lectureId) {
        ArrayList<AdditionalMaterials> addMatLecture = additionalMaterialsRep.getAdditionalMaterials(lectureId);
        addMatLecture.forEach(System.out::println);
    }

    public boolean belongsToLecture(int lectureId, int id) {
        return additionalMaterialsRep.belongsToLecture(lectureId, id);
    }

    public void printListMaterials(){

        getAdditionalMaterialsList().stream().sorted().forEach(System.out::println);

    }

    public Optional<Lecture> maxAddMat(List<Lecture> lectures){
        Map<Integer, ArrayList<AdditionalMaterials>> additionalMaterialsMap = additionalMaterialsRep.getAll();
        Optional<ArrayList<AdditionalMaterials>> maxList =lectures.stream()
                .map(lecture -> additionalMaterialsMap.get(lecture.getId()))
                .max(Comparator.comparing(ArrayList::size));
        if (maxList.isEmpty()){
            return Optional.empty();
        }
        int lectureId = maxList.get().get(0).getLectureId();
        return lectures.stream().filter(lecture -> lecture.getId() == lectureId).findFirst();
    }

    public Map<Integer, ArrayList<AdditionalMaterials>> getAllAddMatt(){
        return additionalMaterialsRep.getAll();
    }

    public ArrayList<AdditionalMaterials> getAdditionalMaterialsList(){
        Map<Integer, ArrayList<AdditionalMaterials>> map = additionalMaterialsRep.getAll();

        ArrayList<AdditionalMaterials> additionalMaterialsList = new ArrayList<>();

        map.forEach((key, value) -> additionalMaterialsList.addAll(value));
        return additionalMaterialsList;
    }
}
