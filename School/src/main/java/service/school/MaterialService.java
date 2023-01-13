package service.school;

import constants.ValidationType;
import models.Materials;
import repository.MaterialsRep;
import repository.SchoolRep;
import service.ConversationService;

import java.util.Scanner;

public class MaterialService extends SchoolService {

    private static final String PRINT_MATERIALS= "Print materials";

    private ConversationService conversationService;

    public MaterialService(SchoolRep schoolRep, ConversationService conversationService) {
        super(schoolRep);
        this.conversationService = conversationService;
    }

    public Materials crete(){


        String materialsString = conversationService.getResponse(PRINT_MATERIALS, ValidationType.DESCRIPTION);

        Materials materials = new Materials(materialsString);

        schoolRep.add(materials);

        return materials;
    }
}
