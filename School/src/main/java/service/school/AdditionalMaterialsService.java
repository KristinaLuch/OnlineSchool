package service.school;

import constants.ValidationType;
import exceptions.EntityNotFoundException;
import exceptions.IncorrectSymbolException;
import models.ResourceType;
import models.school_object.AdditionalMaterials;
import models.school_object.Lecture;
import models.school_object.SchoolObject;
import repository.AdditionalMaterialsRep;
import repository.LectureRep;
import service.conversation.ConversationService;

import java.util.ArrayList;

import static models.ResourceType.*;

public class AdditionalMaterialsService implements SchoolService{

    private ConversationService conversationService;
    private AdditionalMaterialsRep additionalMaterialsRep;

    private LectureRep lectureRep;

    public AdditionalMaterialsService(ConversationService conversationService, AdditionalMaterialsRep additionalMaterialsRep, LectureRep lectureRep) {
        this.conversationService = conversationService;
        this.additionalMaterialsRep = additionalMaterialsRep;
        this.lectureRep = lectureRep;
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
        ArrayList<AdditionalMaterials> additionalMaterialsList = additionalMaterialsRep.getAll();
        for (AdditionalMaterials additionalMaterials : additionalMaterialsList) {
            System.out.println(additionalMaterials);
        }
    }

    @Override
    public boolean delete(int id) throws EntityNotFoundException {
        additionalMaterialsRep.delete(id);
        return true;
    }
}
