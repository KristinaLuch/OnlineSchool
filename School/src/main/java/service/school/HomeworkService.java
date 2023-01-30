package service.school;

import constants.ValidationType;
import models.Homework;
import repository.HomeworkRep;
import service.conversation.ConversationService;

public class HomeworkService {

    private static final String PRINT_HOMEWORK = "Print homework";

    private HomeworkRep homeworkRep;
    private ConversationService conversationService;

    public HomeworkService(HomeworkRep homeworkRep, ConversationService conversationService) {
        this.homeworkRep = homeworkRep;
        this.conversationService = conversationService;
    }

    public Homework create(int lectureId) {

        String task = conversationService.getResponse(PRINT_HOMEWORK, ValidationType.DESCRIPTION);
        Homework homework = new Homework(lectureId, task);
        homeworkRep.add(homework);
        return homework;
    }

    public void addToRep(Homework homework){
        if (homework == null){
            return;
        }
        homeworkRep.add(homework);
    }
}
