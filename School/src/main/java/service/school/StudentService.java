package service.school;

import models.Student;
import models.Teacher;
import repository.StudentRep;

import java.util.Scanner;

public class StudentService extends SchoolService {

    public static final String PRINT_NAME = "Print student`s name";
    public static final String PRINT_SURNAME = "Print student`s surname";
    
    private Scanner scanner;

    public StudentService(StudentRep schoolRep, Scanner scanner) {
        this.schoolRep = schoolRep;
        this.scanner = scanner;
    }

    public Student create(){

        System.out.println(PRINT_NAME);
        String name = scanner.next();
        System.out.println(PRINT_SURNAME);
        String surname = scanner.next();
        Student student = new Student(name, surname);
        schoolRep.add(student);
        return student;
    }

}
