package service.school;

import models.Teacher;
import repository.TeacherRep;

import java.util.Scanner;

public class TeacherService extends SchoolService {

    private static final String PRINT_SUBJECT = "Print subject";
    public static final String PRINT_NAME = "Print teacher`s name";
    public static final String PRINT_SURNAME = "Print teacher`s surname";
    private Scanner scanner;

    public TeacherService(TeacherRep schoolRep, Scanner scanner) {
        this.schoolRep = schoolRep;
        this.scanner = scanner;
    }

    public Teacher create() {

        System.out.println(PRINT_SUBJECT);
        String subject = scanner.next();
        System.out.println(PRINT_NAME);
        String name = scanner.next();
        System.out.println(PRINT_SURNAME);
        String surname = scanner.next();
        Teacher teacher = new Teacher(name, surname, subject);
        schoolRep.add(teacher);
        return teacher;
    }

}
