package repository.school.impl;

import exceptions.EntityNotFoundException;
import models.school_object.Person;

import java.util.ArrayList;

public class StudentRep extends PersonRep{
    public StudentRep(ArrayList<Person> teachers, ArrayList<Person> students) {
        super(teachers, students);
    }

    @Override
    public boolean add(Person person) {
        if (person == null) {
            return false;
        }
        students.add(person);
        return true;
    }

    @Override
    public boolean update(int id, Person newPerson) throws EntityNotFoundException {
        if (id <= 0) {
            throw new EntityNotFoundException();
        }
        Person findPerson;
        int index;

        for (int i = 0; i < students.size(); i++) {
            findPerson = students.get(i);
            if (findPerson.getId() == id) {
                index = i;
                students.add(index, newPerson);
                return true;
            }
        }

        throw new EntityNotFoundException();
    }

    @Override
    public Person get(int id) throws EntityNotFoundException {
        if (id <= 0) {
            throw new EntityNotFoundException();
        }
        Person findObj;

        for (int i = 0; i < students.size(); i++) {
            findObj = students.get(i);
            if (findObj.getId() == id) {
                return findObj;
            }
        }
        throw new EntityNotFoundException();
    }

    public boolean delete(int id) throws EntityNotFoundException {
        if (id <= 0) {
            throw new EntityNotFoundException();
        }
        int indObj;
        for (int i = 0; i < students.size(); i++) {
            indObj = students.get(i).getId();
            if (indObj == id) {
                students.remove(i);
                return true;
            }
        }
        throw new EntityNotFoundException();
    }
    @Override
    public ArrayList<Person> getAll() {
        return students;
    }

    public void printAll(){

        if (students == null||students.size() == 0){
            System.out.println("You haven't created anything yet");
        }
        Person obj;
        for (int i = 0; i<students.size(); i++){
            obj = students.get(i);
            if(obj != null) {
                System.out.println("id = " +obj.getId() + " - "+obj);
            }
        }
    }


    public boolean isDuplicate(String email){
        return students.stream().anyMatch(p -> p.getEmail().equals(email));
    }
}
