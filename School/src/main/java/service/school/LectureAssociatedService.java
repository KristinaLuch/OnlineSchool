package service.school;

import constants.ValidationType;
import exceptions.EntityNotFoundException;
import exceptions.IncorrectSymbolException;
import loger.Log;
import models.school_object.Homework;
import models.school_object.Lecture;
import repository.school.impl.LectureRep;
import service.conversation.ConversationService;

import java.util.ArrayList;

public class LectureAssociatedService {

    private final ConversationService conversationService;
    private final HomeworkService homeworkService;
    private final AdditionalMaterialsService additionalMaterialsService;
    private final LectureRep lectureRep;

    public LectureAssociatedService(ConversationService conversationService, HomeworkService homeworkService,
                                    AdditionalMaterialsService additionalMaterialsService, LectureRep lectureRep) {
        this.conversationService = conversationService;
        this.homeworkService = homeworkService;
        this.additionalMaterialsService = additionalMaterialsService;
        this.lectureRep = lectureRep;
    }

    public static final String ADDED = "Added";
    public static final String DELETED = "Deleted";

    public void showAssociated(int lectureId) {
        if (!lectureRep.isExist(lectureId)) {
            System.out.println("Lecture not exist");
            return;
        }
        String response = conversationService.getResponse("Do you want to watch the homeworks(print \"1\") or \n " +
                "additional materials(print \"2\") from this lecture? (If no - print \"no\")", ValidationType.ANYTHING);

        switch (response) {
            case "1":
                homeworkService.printHomeworksFromLecture(lectureId);
                try {
                    workWithLectureHomework(lectureId);
                } catch (EntityNotFoundException e) {
                    Log.error(this.getClass().getName(), "method showAssociated", e);
                }
                return;
            case "2":
                additionalMaterialsService.printHomeworksFromLecture(lectureId);
                try {
                    workWithLectureAdditionalMaterials(lectureId);
                } catch (EntityNotFoundException e) {
                    Log.error(this.getClass().getName(), "method showAssociated", e);
                }
                return;
            case "no":
                return;
            default:
                try {
                    throw new IncorrectSymbolException("Wrong response");
                } catch (IncorrectSymbolException e) {
                    Log.error(this.getClass().getName(), "method showAssociated", e);
                    showAssociated(lectureId);
                }
        }
    }

    private void workWithLectureHomework(int lectureId) throws EntityNotFoundException {
        while (true) {
            String response = conversationService.getResponse("Do you want (1) add or (2) delete, or (3)back to main menu?",
                    ValidationType.ANYTHING);
            Lecture lecture;
            switch (response) {
                case "1":
                    lecture = addHomeworks(lectureRep.get(lectureId));
                    lectureRep.update(lectureId, lecture);
                    break;
                case "2":
                    lecture = deleteHomeworks(lectureRep.get(lectureId));
                    lectureRep.update(lectureId, lecture);
                    break;
                case "3":
                    return;
                default:
                    try {
                        throw new IncorrectSymbolException("Wrong response");
                    } catch (IncorrectSymbolException e) {
                        Log.error(this.getClass().getName(), "method showAssociated", e);
                    }
            }
        }
    }

    private Lecture deleteHomeworks(Lecture lecture) {
        boolean run;

        do {
            lecture = deleteOneHomework(lecture);
            run = wantContinue("Do you want to delete another homework?");
        } while (run);
        return lecture;
    }

    private boolean wantContinue(String massage) {
        String response = conversationService.getResponse(massage, ValidationType.ANYTHING);
        switch (response) {
            case "yes":
                return true;
            case "no":
                return false;
            default:
                System.out.println("Print yes or no");
                return wantContinue(massage);
        }
    }

    private Lecture deleteOneHomework(Lecture lecture) {
        ArrayList<Homework> homeworks = lecture.getHomework();
        int id = Integer.parseInt(conversationService.getResponse("Print homework id:", ValidationType.DIGIT));

        for (int i = 0; i < homeworks.size(); i++) {
            if (homeworks.get(i).getId() == id) {
                homeworks.remove(i);
                lecture.setHomework(homeworks);
                homeworkService.delete(id);
                System.out.println("Deleted");
                return lecture;
            }
        }
        System.out.println("Homework with this id not exist");
        return lecture;
    }

    private Lecture addHomeworks(Lecture lecture) {
        boolean run;

        do {
            lecture = addOneHomework(lecture);
            run = wantContinue("Do you want to add another homework?");
        } while (run);
        return lecture;
    }

    private Lecture addOneHomework(Lecture lecture) {
        ArrayList<Homework> homeworks = lecture.getHomework();
        Homework homework = homeworkService.create(lecture);
        homeworks.add(homework);
        lecture.setHomework(homeworks);
        System.out.println(ADDED);
        return lecture;
    }

    private void workWithLectureAdditionalMaterials(int lectureId) throws EntityNotFoundException {
        while (true) {
            String response = conversationService.getResponse("Do you want (1) add or (2) delete, or (3)back to main menu?",
                    ValidationType.ANYTHING);
            switch (response) {
                case "1":
                    addAdditionalMaterials(lectureRep.get(lectureId));
                    break;
                case "2":
                    deleteAdditionalMaterials(lectureRep.get(lectureId));
                    break;
                case "3":
                    return;
                default:
                    try {
                        throw new IncorrectSymbolException("Wrong response");
                    } catch (IncorrectSymbolException e) {
                        Log.error(this.getClass().getName(), "method workWithLectureAdditionalMaterials", e);
                    }
            }
        }
    }

    private void deleteAdditionalMaterials(Lecture lecture) {
        boolean run;

        do {
            deleteOneAdditionalMaterial(lecture);
            run = wantContinue("Do you want to delete another additional materials?");
        } while (run);
    }


    private void deleteOneAdditionalMaterial(Lecture lecture) {

        int id = Integer.parseInt(conversationService.getResponse("Print id:", ValidationType.DIGIT));
        if (id < 1) {
            System.out.println("Additional materials with this id not exist");
            return;
        }

        if (!additionalMaterialsService.belongsToLecture(lecture.getId(), id)) {
            System.out.println("Additional materials with this id not exist in this lecture");
            return;
        }

        try {
            additionalMaterialsService.delete(id);
            System.out.println(DELETED);

        } catch (EntityNotFoundException e) {

            System.out.println("Additional materials with this id not exist");
        }
    }

    private void addAdditionalMaterials(Lecture lecture) {
        boolean run;
        do {
            addOneAdditionalMaterials(lecture);
            run = wantContinue("Do you want to add another additional materials?");
        } while (run);
    }

    private Lecture addOneAdditionalMaterials(Lecture lecture) {
        additionalMaterialsService.create(lecture.getId());
        System.out.println("Added");
        return lecture;
    }

}
