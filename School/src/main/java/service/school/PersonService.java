package service.school;

import constants.ValidationType;
import exceptions.EntityNotFoundException;
import loger.Log;
import models.Role;
import models.school_object.Person;
import models.school_object.Student;
import repository.school.impl.PersonRep;
import service.conversation.ConversationService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class PersonService implements SchoolService{

    public static final String FILE_PATH = "School/src/main/java/file/students_emails.txt";
    public static final String PRINT_FIRSTNAME = "Print person`s firstname";
    public static final String PRINT_LASTNAME = "Print person`s lastname";

    public static final String PRINT_ROLE = "Print role: student or teacher";

    public static final String ANSWER_ROLE_STUDENT = "student";

    public static final String ANSWER_ROLE_TEACHER = "teacher";

    public static final String PRINT_PHONE_NUMBER = "Print phone number";
    public static final String PRINT_EMAIL = "Print e-mail";

    private File file;
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

    public void printEmailLastnameMap(){
        Map<String, ArrayList<String>> emailLastnameMap = personRep.getAll().stream()
                .collect(Collectors.toMap(person -> person.getEmail(), person -> new ArrayList<>(Arrays.asList(person.getFirstname(), person.getLastname()))));
        System.out.println(emailLastnameMap);
    }

    private void createFile() {
        file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                Log.info(this.getClass().getName(), "createFile mtd, create file");
                file.createNewFile();
            } catch (IOException e) {
                Log.error(this.getClass().getName(), "method createFile", e);
            }
        }
    }

    public void saveStudentsInFile(){
        List<String> emails = personRep.getAll().stream()
                .filter(person -> person.getRole().equals(Role.STUDENT))
                .map(Person::getEmail)
                .sorted(Comparator.comparing(email -> email.charAt(0))).toList();
        if(file==null||!file.exists()){
            createFile();
        }
        try (FileWriter fileWriter = new FileWriter(FILE_PATH)) {
            for (String email:emails) {
                fileWriter.write(email);
            }
            System.out.println("E-mails saved");
        } catch (IOException e) {
            Log.error(this.getClass().getName(), "saveStudentsInFile mtd", e);
        }
    }


}
