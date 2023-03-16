package service.school;

import constants.ValidationType;
import exceptions.EntityNotFoundException;
import models.Role;
import models.school_object.Person;
import models.school_object.Student;
import repository.school.impl.PersonRep;
import service.conversation.ConversationService;

import java.util.ArrayList;
import java.util.Collections;

public class PersonService implements SchoolService{

    public static final String PRINT_FIRSTNAME = "Print person`s firstname";
    public static final String PRINT_LASTNAME = "Print person`s lastname";

    public static final String PRINT_ROLE = "Print role: student or teacher";

    public static final String ANSWER_ROLE_STUDENT = "student";

    public static final String ANSWER_ROLE_TEACHER = "teacher";

    public static final String PRINT_PHONE_NUMBER = "Print phone number";
    public static final String PRINT_EMAIL = "Print e-mail";

    private final PersonRep personRep;
    private final ConversationService conversationService;

    public PersonService(PersonRep personRep, ConversationService conversationService) {
        this.personRep = personRep;
        this.conversationService = conversationService;
    }

    public Person create(){
        Role role = getRole();
        String firstName = conversationService.getResponse(PRINT_FIRSTNAME, ValidationType.NAME);
        String lastName = conversationService.getResponse(PRINT_LASTNAME, ValidationType.NAME);
        String phone = conversationService.getResponse(PRINT_PHONE_NUMBER, ValidationType.PHONE);
        String email = conversationService.getResponse(PRINT_EMAIL, ValidationType.EMAIL);
        Person person = new Person(role, firstName, lastName, phone, email);
        conversationService.print(person.toString());
        personRep.add(person);
        System.out.println("Person created");
        return person;
    }

    @Override
    public void read_by_id(int id) throws EntityNotFoundException {
        System.out.println(personRep.get(id));
    }

    @Override
    public void readAll() {
        ArrayList<Person> persons = personRep.getAll();
        Collections.sort(persons);
        persons.forEach(System.out::println);
    }

    @Override
    public boolean delete(int id) throws EntityNotFoundException {
        personRep.delete(id);
        return true;
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

    public Student createSystemStudent(String firstname, String lastname, String phone, String email){
        Student student = new Student(firstname, lastname, phone, email);
        personRep.add(student);
        return student;
    }

    private Person createAdmin(Role role, int courseID, String firstname, String lastname, String phone, String email){
        Person person = new Person(courseID, role, firstname, lastname, phone, email);
        personRep.add(person);
        return person;
    }

    public Person createStudentAdmin(int courseID, String firstname, String lastname, String phone, String email){
        return createAdmin(Role.STUDENT, courseID, firstname, lastname, phone, email);
    }
    public Person createTeacherAdmin(int courseID, String firstname, String lastname, String phone, String email){
        return createAdmin(Role.TEACHER, courseID, firstname, lastname, phone, email);
    }

    public void printTeacherBeforeN(){
        personRep.printTeacherBeforeN();
    }

}
