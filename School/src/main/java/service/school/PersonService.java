package service.school;


import constants.ValidationType;
import models.Role;
import models.Person;
import repository.PersonRep;
import repository.SchoolRep;
import service.ConversationService;

import java.util.Scanner;

public class PersonService extends SchoolService {

    public static final String PRINT_FIRSTNAME = "Print person`s firstname";
    public static final String PRINT_LASTNAME = "Print person`s lastname";

    public static final String PRINT_ROLE = "Print role: student or teacher";

    public static final String ANSWER_ROLE_STUDENT = "student";

    public static final String ANSWER_ROLE_TEACHER = "teacher";

    public static final String PRINT_PHONE_NUMBER = "Print phone number";
    public static final String PRINT_EMAIL = "Print e-mail";

    private ConversationService conversationService;

    public PersonService(SchoolRep schoolRep, ConversationService conversationService) {
        super(schoolRep);
        this.conversationService = conversationService;
    }

    public Person create(){
        Role role = getRole();
        String firstName = conversationService.getResponse(PRINT_FIRSTNAME, ValidationType.NAME);
        String lastName = conversationService.getResponse(PRINT_LASTNAME, ValidationType.NAME);
        String phone = conversationService.getResponse(PRINT_PHONE_NUMBER, ValidationType.PHONE);
        String email = conversationService.getResponse(PRINT_EMAIL, ValidationType.EMAIL);
        Person person = new Person(role, firstName, lastName, phone, email);
//        schoolRep.add(person);
        conversationService.print(person.toString());
        return person;
    }

    private Role getRole(){

        String roleAnswer = conversationService.getResponse(PRINT_ROLE, ValidationType.NAME);
        if (roleAnswer == null){
            System.out.println("Wrong command(null)");
            return getRole();
        }
        if (roleAnswer.equalsIgnoreCase(ANSWER_ROLE_TEACHER)) {
            return Role.TEACHER;
        }
        else if (roleAnswer.equalsIgnoreCase(ANSWER_ROLE_STUDENT)) {

            return Role.STUDENT;
        }
        else {
            System.out.println("Wrong command");
            return getRole();
        }
    }

}
