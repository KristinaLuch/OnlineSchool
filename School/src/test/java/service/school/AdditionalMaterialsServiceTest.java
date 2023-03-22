package service.school;

import comparator_add_materials.ComparatorId;
import comparator_add_materials.ComparatorLectureId;
import comparator_add_materials.ComparatorResourceType;
import constants.ValidationType;
import exceptions.EntityNotFoundException;
import models.ResourceType;
import models.school_object.AdditionalMaterials;
import models.school_object.Lecture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import repository.school.impl.AdditionalMaterialsRep;
import repository.school.impl.LectureRep;
import service.conversation.ConversationService;

import static service.school.AdditionalMaterialsService.*;

public class AdditionalMaterialsServiceTest {
    ConversationService conversationService = Mockito.mock(ConversationService.class);
    AdditionalMaterialsRep additionalMaterialsRep = Mockito.mock(AdditionalMaterialsRep.class);
    LectureRep lectureRep = Mockito.mock(LectureRep.class);
    ComparatorId comparatorId = Mockito.mock(ComparatorId.class);
    ComparatorLectureId comparatorLectureId = Mockito.mock(ComparatorLectureId.class);
    ComparatorResourceType comparatorResourceType = Mockito.mock(ComparatorResourceType.class);

    AdditionalMaterialsService cut =
            new AdditionalMaterialsService(conversationService, additionalMaterialsRep, lectureRep,
                    comparatorId, comparatorLectureId, comparatorResourceType);

    public static final AdditionalMaterials additionalMaterials = new AdditionalMaterials(1, "Roma", 1, ResourceType.BOOK);
    public static final AdditionalMaterials additionalMaterials1 = new AdditionalMaterials(2, "Roma", 1, ResourceType.URL);
    public static final AdditionalMaterials additionalMaterials2 = new AdditionalMaterials(3, "Roma", 1, ResourceType.VIDEO);

    static Arguments[] createTestArgs() {
        return new Arguments[]{
                Arguments.arguments("1", 1, "Roma", "book", additionalMaterials, additionalMaterials),
                Arguments.arguments("1", 1, "Roma", "url", additionalMaterials1, additionalMaterials1),
                Arguments.arguments("1", 1, "Roma", "video", additionalMaterials2, additionalMaterials2),
        };
    }

    @MethodSource("createTestArgs")
    @ParameterizedTest
    void createTest(String lectureIdStr, int lectureId, String name, String resourceTypeStr, AdditionalMaterials additionalMaterials, AdditionalMaterials expected) throws EntityNotFoundException {
        Mockito.when(conversationService.getResponse(PRINT_LECTURE_ID, ValidationType.DIGIT)).thenReturn(lectureIdStr);
        Mockito.when(lectureRep.get(lectureId)).thenReturn(new Lecture());
        Mockito.when(conversationService.getResponse(PRINT_NAME, ValidationType.NAME)).thenReturn(name);
        Mockito.when(conversationService.getResponse(PRINT_RESOURCE_TYPE, ValidationType.ANYTHING)).thenReturn(resourceTypeStr);
        Mockito.when(additionalMaterialsRep.add(additionalMaterials)).thenReturn(true);
        AdditionalMaterials actual = cut.create();
        Assertions.assertEquals(expected, actual);
    }

    static Arguments[] createExceptionTestArgs() {
        return new Arguments[]{
                Arguments.arguments("1", 1, "Roma",
                        "buk", "book", additionalMaterials, additionalMaterials),
        };
    }

    @MethodSource("createExceptionTestArgs")
    @ParameterizedTest
    void createExceptionTest(String lectureIdStr, int lectureId,
                             String name, String resourceTypeStr, String resourceTypeStr1,
                             AdditionalMaterials additionalMaterials,
                             AdditionalMaterials expected) throws EntityNotFoundException {

            Mockito.when(conversationService.getResponse(PRINT_LECTURE_ID, ValidationType.DIGIT)).thenReturn(lectureIdStr);
            Mockito.when(lectureRep.get(lectureId)).thenReturn(new Lecture());
            Mockito.when(conversationService.getResponse(PRINT_NAME, ValidationType.NAME)).thenReturn(name);

                Mockito.when(conversationService.getResponse(PRINT_RESOURCE_TYPE, ValidationType.ANYTHING))
                        .thenReturn(resourceTypeStr);
            Mockito.when(conversationService.getResponse(PRINT_RESOURCE_TYPE, ValidationType.ANYTHING))
                    .thenReturn(resourceTypeStr1);
            AdditionalMaterials actual = cut.create();
//            try (MockedStatic<Log> logMockedStatic = Mockito.mockStatic(Log.class)) {
//                logMockedStatic.verify(() -> Log
//                        .error(AdditionalMaterialsService.class.getName(),
//                                "method getResourceType", new IncorrectSymbolException("Choose " +
//                                        "\"book\", \"video\" or \"url\"")));
//            }
//            try (MockedStatic<LogRepository> utilities = Mockito.mockStatic(LogRepository.class)) {
//                utilities.verify(() -> LogRepository
//                        .add(new Log(AdditionalMaterialsService.class.getName(),
//                                Level.ERROR, "method getResourceType",
//                                LocalDateTime.now(), "")));
//            }
//                        .thenReturn(resourceTypeStr1)
//                        .thenReturn(resourceTypeStr2);
        Mockito.when(additionalMaterialsRep.add(additionalMaterials)).thenReturn(true);
//        AdditionalMaterials actual = cut.create();
//                });

//        AdditionalMaterials actual = cut.create();
//        Assertions.assertEquals(expected, actual);

    }
}

