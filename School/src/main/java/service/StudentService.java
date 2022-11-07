package service;

import entity.Student;
import repository.StudentRep;

import java.util.Scanner;

public class StudentService {

    public static final String PRINT_NAME = "Print student`s name";
    public static final String PRINT_SURNAME = "Print student`s surname";

    private StudentRep students;
    private Scanner scanner;

    public StudentService(StudentRep students, Scanner scanner) {
        this.students = students;
        this.scanner = scanner;
    }

    public Student create(){

        System.out.println(PRINT_NAME);
        String name = scanner.next();
        System.out.println(PRINT_SURNAME);
        String surname = scanner.next();
        Student student = new Student(name, surname);
        students.add(student);
        return student;
    }

}
