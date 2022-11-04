package service;

import entity.Student;
import entity.Teacher;
import repository.TeacherRep;

import java.util.ArrayList;
import java.util.Scanner;

public class TeacherService {

    private static final String PRINT_SUBJECT = "Print subject";
    public static final String PRINT_NAME = "Print teacher`s name";
    public static final String PRINT_SURNAME = "Print teacher`s surname";

    public TeacherRep teachers;
    private Scanner scanner;

    public TeacherService(TeacherRep teachers, Scanner scanner) {
        this.teachers = teachers;
        this.scanner = scanner;
    }

    public Teacher create(){

        System.out.println(PRINT_SUBJECT);
        String subject = scanner.next();
        System.out.println(PRINT_NAME);
        String name = scanner.next();
        System.out.println(PRINT_SURNAME);
        String surname = scanner.next();
        Teacher teacher = new Teacher(name, surname, subject);
        teachers.add(teacher);
        return teacher;
    }

}
