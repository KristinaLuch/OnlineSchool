package service.school;

import comparator_add_materials.ComparatorId;
import comparator_add_materials.ComparatorLectureId;
import comparator_add_materials.ComparatorResourceType;
import constants.ValidationType;
import exceptions.EntityNotFoundException;
import exceptions.IncorrectSymbolException;
import models.ResourceType;
import models.school_object.AdditionalMaterials;
import models.school_object.SchoolObject;
import repository.AdditionalMaterialsRep;
import repository.LectureRep;
import service.conversation.ConversationService;

import java.util.ArrayList;
import java.util.Comparator;

import static models.ResourceType.*;

public class AdditionalMaterialsService implements SchoolService{

    private ConversationService conversationService;
    private AdditionalMaterialsRep additionalMaterialsRep;

    private LectureRep lectureRep;

    private ComparatorId comparatorId;

    private ComparatorLectureId comparatorLectureId;

    private ComparatorResourceType comparatorResourceType;

    public AdditionalMaterialsService(ConversationService conversationService, AdditionalMaterialsRep additionalMaterialsRep, LectureRep lectureRep, ComparatorId comparatorId, ComparatorLectureId comparatorLectureId, ComparatorResourceType comparatorResourceType) {
        this.conversationService = conversationService;
        this.additionalMaterialsRep = additionalMaterialsRep;
        this.lectureRep = lectureRep;
        this.comparatorId = comparatorId;
        this.comparatorLectureId = comparatorLectureId;
        this.comparatorResourceType = comparatorResourceType;
    }

    public static final String PRINT_NAME = "Print name:";
    public static final String PRINT_RESOURCE_TYPE = "Print resource type():";

    public static final String PRINT_LECTURE_ID = "Print lecture id:";

    @Override
    public SchoolObject create() {
        AdditionalMaterials additionalMaterials = new AdditionalMaterials();
        additionalMaterials.setName(conversationService.getResponse(PRINT_NAME, ValidationType.NAME));
        additionalMaterials.setResourceType(getResourceType());
        additionalMaterials.setLectureId(getLectureId());
        additionalMaterialsRep.add(additionalMaterials);
        conversationService.print(additionalMaterials.toString());
        conversationService.print("Created");
        return additionalMaterials;
    }

    private int getLectureId(){
        int lectureId = Integer.parseInt(conversationService.getResponse(PRINT_LECTURE_ID, ValidationType.DIGIT));
        try {
            lectureRep.get(lectureId);
            return lectureId;
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return getLectureId();
        }
    }

    private ResourceType getResourceType() {
        String response = conversationService.getResponse(PRINT_RESOURCE_TYPE, ValidationType.ANYTHING);
        switch (response){
            case "book"-> {
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
                    e.printStackTrace();
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
        Comparator<AdditionalMaterials> comparator = getComparator();
        ArrayList<AdditionalMaterials> additionalMaterialsList = additionalMaterialsRep.getAll();
        if(comparator != null){
            additionalMaterialsList.sort(comparator);
        }
        for (AdditionalMaterials additionalMaterials : additionalMaterialsList) {
            System.out.println(additionalMaterials);
        }
    }

    private Comparator getComparator(){
        String result = conversationService.getResponse("Select an option to sort: 1. default(id); " +
                "2. Lecture id; 3. ResourceType", ValidationType.DIGIT);
        switch (result){
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
                    e.printStackTrace();
                    return getComparator();
                }
        }
    }

    @Override
    public boolean delete(int id) throws EntityNotFoundException {
        additionalMaterialsRep.delete(id);
        return true;
    }
}
