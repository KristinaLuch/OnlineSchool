package service.school;

import loger.Log;
import models.school_object.Person;
import models.school_object.StudentContr;
import repository.school.impl.StudentRep;
import repository.school.impl.TeacherRep;
import service.conversation.ConversationService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

abstract public class PersonService implements SchoolService{

    public static final String FILE_PATH = "School/src/main/java/file/students_emails.txt";
    public static final String PRINT_FIRSTNAME = "Print person`s firstname";
    public static final String PRINT_LASTNAME = "Print person`s lastname";

    public static final String PRINT_ROLE = "Print role: student or teacher";

    public static final String ANSWER_ROLE_STUDENT = "student";

    public static final String ANSWER_ROLE_TEACHER = "teacher";

    public static final String PRINT_PHONE_NUMBER = "Print phone number";
    public static final String PRINT_EMAIL = "Print e-mail";

    protected File file;
    protected final StudentRep studentRep;
    protected final TeacherRep teacherRep;
    protected final ConversationService conversationService;

    public PersonService(StudentRep studentRep, TeacherRep teacherRep, ConversationService conversationService) {
        this.studentRep = studentRep;
        this.teacherRep = teacherRep;
        this.conversationService = conversationService;
    }

//    protected Role getRole(){
//
//        String roleAnswer = conversationService.getResponse(PRINT_ROLE, ValidationType.NAME);
//        if (roleAnswer == null){
//            System.out.println("Wrong command(null)");
//            return getRole();
//        }
//        if (roleAnswer.equalsIgnoreCase(ANSWER_ROLE_TEACHER)) {
//            return Role.TEACHER;
//        }
//        else if (roleAnswer.equalsIgnoreCase(ANSWER_ROLE_STUDENT)) {
//
//            return Role.STUDENT;
//        }
//        else {
//            System.out.println("Wrong command");
//            return getRole();
//        }
//    }

    public StudentContr createSystemStudent(String firstname, String lastname, String phone, String email){
        StudentContr student = new StudentContr(firstname, lastname, phone, email);
        studentRep.add(student);
        return student;
    }

    public void printEmailLastnameMap(){
        ArrayList<Person> persons = new ArrayList<>();
        Stream.of(studentRep.getAll(), teacherRep.getAll()).forEach(persons::addAll);
        Map<String, ArrayList<String>> emailLastnameMap = persons.stream()
                .collect(Collectors.toMap(person -> person.getEmail(), person -> new ArrayList<>(Arrays.asList(person.getFirstname(), person.getLastname()))));
        System.out.println(emailLastnameMap);
    }

    protected void createFile() {
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
        List<String> emails = studentRep.getAll().stream()
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
