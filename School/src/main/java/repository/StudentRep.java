package repository;

import entity.Student;

public class StudentRep {

    private Student[] students;

    public StudentRep() {
        this.students = new Student[10];
    }

    public boolean add(Student student) {
        if (student == null) {
            return false;
        }
        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) {
                students[i] = student;
                if (i == students.length - 1) {
                    increase();
                }
                break;
            }
        }
        return true;
    }

    private void increase() {
        int newLength = (students.length * 3) / 2 + 1;
        Student[] tmp = new Student[newLength];
        for (int i = 0; i < students.length; i++) {
            tmp[i] = students[i];
        }
        students = tmp;
    }

    public Student get(int id) {
        if (id < 0) {
            return null;
        }
        if (id == students[id].getId()) {
            return students[id];
        } //if id == i
        for (int i = 0; i < students.length; i++) {
            if (students[i].getId() == id) {
                return students[i];
            }
        }
        return null;
    }

    public Student[] getAll() {
        return students;
    }

}
