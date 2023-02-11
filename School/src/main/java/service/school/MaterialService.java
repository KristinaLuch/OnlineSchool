package service.school;

import constants.ValidationType;
import models.school_object.Materials;
import repository.MaterialsRep;
import service.conversation.ConversationService;

public class MaterialService{

    private static final String PRINT_MATERIALS= "Print materials";

    private final MaterialsRep materialsRep;

    private final ConversationService conversationService;

    public MaterialService(MaterialsRep materialsRep, ConversationService conversationService) {
        this.materialsRep = materialsRep;
        this.conversationService = conversationService;
    }

    public Materials crete(){

        String materialsString = conversationService.getResponse(PRINT_MATERIALS, ValidationType.DESCRIPTION);

        Materials materials = new Materials(materialsString);

        materialsRep.add(materials);

        return materials;
    }

    public void addToRep(Materials materials){
        if (materials == null){
            return;
        }
        materialsRep.add(materials);
    }
}
