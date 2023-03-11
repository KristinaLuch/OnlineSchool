package service.school;

import constants.ValidationType;
import exceptions.EntityNotFoundException;
import models.school_object.Homework;
import repository.school.impl.HomeworkRep;
import service.conversation.ConversationService;

import java.util.ArrayList;

public class HomeworkService {

    private static final String PRINT_HOMEWORK = "Print homework";

    private final HomeworkRep homeworkRep;
    private final ConversationService conversationService;

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

    public void addToRep(Homework homework) {
        if (homework == null) {
            return;
        }
        homeworkRep.add(homework);
    }

    public boolean delete(int id) {

        try {
            homeworkRep.delete(id);
        } catch (EntityNotFoundException e) {

            return false;
        }
        return true;
    }

    public ArrayList<Homework> getHomeworksFromLecture(int lectureId) {
        return homeworkRep.getHomeworks(lectureId);
    }

    public void printHomeworksFromLecture(int lectureId) {
        ArrayList<Homework> homeworkLecture = homeworkRep.getHomeworks(lectureId);
        homeworkLecture.forEach(System.out::println);
    }
}
