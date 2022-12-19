package service.school;


import constants.Role;
import models.Person;
import repository.PersonRep;

import java.util.Scanner;

public class PersonService extends SchoolService {

    public static final String PRINT_FIRSTNAME = "Print person`s firstname";
    public static final String PRINT_LASTNAME = "Print person`s lastname";

    public static final String PRINT_ROLE = "Print role: student or teacher";

    public static final String ANSWER_ROLE_STUDENT = "student";

    public static final String ANSWER_ROLE_TEACHER = "teacher";

    public static final String PRINT_PHONE_NUMBER = "Print phone number";
    public static final String PRINT_EMAIL = "Print e-mail";

    
    private PersonRep schoolRep;
    private Scanner scanner;

    public PersonService(PersonRep schoolRep, Scanner scanner) {
        this.schoolRep = schoolRep;
        this.scanner = scanner;
    }

    public Person create(){
        Role role = getRole();
        System.out.println(PRINT_FIRSTNAME);
        String firstName = scanner.next();
        System.out.println(PRINT_LASTNAME);
        String lastName = scanner.next();
        System.out.println(PRINT_PHONE_NUMBER);
        String phone = scanner.next();
        System.out.println(PRINT_EMAIL);
        String email = scanner.next();
        Person person = new Person(role, firstName, lastName, phone, email);
//        schoolRep.add(person);
        System.out.println(person);
        return person;
    }

    private Role getRole(){

        System.out.println(PRINT_ROLE);
        String roleAnswer = scanner.next();
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
