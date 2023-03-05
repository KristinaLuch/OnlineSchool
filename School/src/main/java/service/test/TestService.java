package service.test;

import loger.Log;
import models.school_object.Student;
import service.school.PersonService;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class TestService {
    private PersonService personService;
    private ArrayList<Integer> repeatingNumberTask;

    public TestService(PersonService personService) {
        repeatingNumberTask = new ArrayList<>();
        this.personService = personService;
    }

    public void runTest() {
        CopyOnWriteArrayList<Student> students = createTenStudents();
        Thread thread;
        for (int i = 0; i < students.size(); i++) {
            students.get(i).setTaskNumber(getRandomTask());
            System.out.println(students.get(i));
            thread = new Thread(students.get(i));
            thread.start();
        }
        int sequence = 1;
        for (int i = 0; i < 12; i++) {
            try {
                Thread.sleep(1000);
                System.out.println(i + 1);
            } catch (InterruptedException e) {
                Log.error(this.getClass().getName(), "method runTest", e);
            }
            for (Student student : students) {
                if (student.getTime() == i) {
                    printCompletedTaskStudent(student, sequence);
                    students.remove(student);
                    sequence++;
                }
            }
        }
        System.out.println("\nStudents who failed to complete the task: ");

        for (Student student : students) {
            System.out.println(student);
        }

    }

    private void printCompletedTaskStudent(Student student, int sequence) {
        String end = "th";
        if (sequence <= 3) {
            if (sequence == 1) {
                end = "st";
            }
            if (sequence == 2) {
                end = "nd";
            }
            if (sequence == 3) {
                end = "rd";
            }
        }
        System.out.println("Completed the task " + sequence + end + " " + student);
    }

    private CopyOnWriteArrayList<Student> createTenStudents() {
        CopyOnWriteArrayList<Student> persons = new CopyOnWriteArrayList();
        Student person;
        for (int i = 1; i <= 10; i++) {
            String name = "Name" + i;
            String lastname = "Lname" + i;
            String phone = "+" + i + "888888" + i;
            String email = "mail" + i + i + "@ii.com";
            person = personService.createSystemStudent(name, lastname, phone, email);
            persons.add(person);
        }
        return persons;
    }


    public int getRandomTask() {
        int randomNumber = (int) (1 + Math.random() * 10);
        while (isRepeat(randomNumber)) {
            randomNumber = (int) (1 + Math.random() * 10);
        }
        repeatingNumberTask.add(randomNumber);
        return randomNumber;
    }

    private boolean isRepeat(int number) {
        if (repeatingNumberTask.size() == 10) {
            repeatingNumberTask.clear();
        }
        for (int ind = 0; ind < repeatingNumberTask.size(); ind++) {
            if (repeatingNumberTask.get(ind) == number) {
                return true;
            }
        }
        return false;
    }

}
