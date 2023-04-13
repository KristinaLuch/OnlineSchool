package service.school;

import constants.ValidationType;
import exceptions.EntityNotFoundException;
import models.Role;
import models.school_object.Person;
import repository.school.impl.StudentRep;
import repository.school.impl.TeacherRep;
import service.conversation.ConversationService;

import java.util.ArrayList;
import java.util.Collections;

public class StudentService extends PersonService{

    public StudentService(StudentRep studentRep, TeacherRep teacherRep, ConversationService conversationService) {
        super(studentRep, teacherRep, conversationService);
    }


    public Person create() {
        Role role = getRole();
        String firstName = conversationService.getResponse(PRINT_FIRSTNAME, ValidationType.NAME);
        String lastName = conversationService.getResponse(PRINT_LASTNAME, ValidationType.NAME);
        String phone = conversationService.getResponse(PRINT_PHONE_NUMBER, ValidationType.PHONE);
        String email = conversationService.getResponse(PRINT_EMAIL, ValidationType.EMAIL_STUDENT);
        Person person = new Person(role, firstName, lastName, phone, email);
        conversationService.print(person.toString());
        studentRep.add(person);
        System.out.println("Person created");
        return person;

    }
    @Override
    public void read_by_id(int id) throws EntityNotFoundException {
        System.out.println(studentRep.get(id));
    }

    @Override
    public void readAll() {
        ArrayList<Person> persons = studentRep.getAll();
        Collections.sort(persons);
        persons.forEach(System.out::println);
    }

    @Override
    public boolean delete(int id) throws EntityNotFoundException {
        studentRep.delete(id);
        return true;
    }

    protected Person createAdmin(int courseID, String firstname, String lastname, String phone, String email){
        Person person = new Person(courseID, Role.STUDENT, firstname, lastname, phone, email);
        studentRep.add(person);
        return person;
    }

}
