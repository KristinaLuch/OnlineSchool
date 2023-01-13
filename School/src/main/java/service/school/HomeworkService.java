package service.school;

import constants.ValidationType;
import models.Homework;
import repository.HomeworkRep;
import repository.SchoolRep;
import service.ConversationService;

import java.util.Scanner;

public class HomeworkService extends SchoolService{

    private static final String PRINT_HOMEWORK = "Print homework";
    private ConversationService conversationService;

    public HomeworkService(HomeworkRep schoolRep, ConversationService conversationService) {
        super(schoolRep);
        this.conversationService = conversationService;
    }

    public Homework create() {

        System.out.println(PRINT_HOMEWORK);
        String homeworkString = conversationService.getResponse(PRINT_HOMEWORK, ValidationType.DESCRIPTION);
        Homework homework = new Homework(homeworkString);
        schoolRep.add(homework);
        return homework;
    }
}
