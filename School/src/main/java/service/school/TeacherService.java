package service.school;

import constants.ValidationType;
import exceptions.EntityNotFoundException;
import models.Role;
import models.school_object.Person;
import repository.school.impl.StudentRep;
import repository.school.impl.TeacherRep;
import service.conversation.ConversationService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class TeacherService extends PersonService{

    public TeacherService(StudentRep studentRep, TeacherRep teacherRep, ConversationService conversationService) {
        super(studentRep, teacherRep, conversationService);
    }

    public Person create() {
        Role role = Role.TEACHER;
        String firstName = conversationService.getResponse(PRINT_FIRSTNAME, ValidationType.NAME);
        String lastName = conversationService.getResponse(PRINT_LASTNAME, ValidationType.NAME);
        String phone = conversationService.getResponse(PRINT_PHONE_NUMBER, ValidationType.PHONE);
        String email = conversationService.getResponse(PRINT_EMAIL, ValidationType.EMAIL_TEACHER);
        Person person = new Person(role, firstName, lastName, phone, email);
        conversationService.print(person.toString());
        teacherRep.add(person);
        System.out.println("Person created");
        return person;
    }
    @Override
    public void read_by_id(int id) throws EntityNotFoundException {
        System.out.println(teacherRep.get(id));
    }

    @Override
    public void readAll() {
        ArrayList<Person> persons = teacherRep.getAll();
        Collections.sort(persons);
        persons.forEach(System.out::println);
    }

    @Override
    public boolean delete(int id) throws EntityNotFoundException {
        teacherRep.delete(id);
        return true;
    }

    protected Person createAdmin(int courseID, String firstname, String lastname, String phone, String email){
        Person person = new Person(courseID, Role.STUDENT, firstname, lastname, phone, email);
        teacherRep.add(person);
        return person;
    }

    public void printTeacherBeforeN(){
        teacherRep.printTeacherBeforeN(); }

    public void printEmailLastnameMap(){
        Map<String, ArrayList<String>> emailLastnameMap = studentRep.getAll().stream()
                .collect(Collectors.toMap(Person::getEmail, person -> new ArrayList<>(Arrays.asList(person.getFirstname(), person.getLastname()))));
        System.out.println(emailLastnameMap);
    }
}
