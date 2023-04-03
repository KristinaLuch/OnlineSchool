package repository.school.impl;

import exceptions.EntityNotFoundException;
import models.Role;
import models.school_object.Person;

import java.util.ArrayList;

public class TeacherRep extends PersonRep{
    public TeacherRep(ArrayList<Person> teachers, ArrayList<Person> students) {
        super(teachers, students);
    }
    @Override
    public boolean add(Person person) {
        if (person == null) {
            return false;
        }
        teachers.add(person);
        return true;
    }

    @Override
    public boolean update(int id, Person newPerson) throws EntityNotFoundException {
        if (id <= 0) {
            throw new EntityNotFoundException();
        }
        Person findPerson;
        int index;

        for (int i = 0; i < teachers.size(); i++) {
            findPerson = teachers.get(i);
            if (findPerson.getId() == id) {
                index = i;
                teachers.add(index, newPerson);
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

        for (int i = 0; i < teachers.size(); i++) {
            findObj = teachers.get(i);
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
        for (int i = 0; i < teachers.size(); i++) {
            indObj = teachers.get(i).getId();
            if (indObj == id) {
                teachers.remove(i);
                return true;
            }
        }
        throw new EntityNotFoundException();
    }

    @Override
    public ArrayList<Person> getAll() {
        return teachers;
    }

    public void printAll(){

        if (teachers == null||teachers.size() == 0){
            System.out.println("You haven't created anything yet");
        }
        Person obj;
        for (int i = 0; i<teachers.size(); i++){
            obj = teachers.get(i);
            if(obj != null) {
                System.out.println("id = " +obj.getId() + " - "+obj);
            }
        }
    }

    public void printTeacherBeforeN(){
        teachers.stream()
                .filter(person -> person.getLastname().toLowerCase().charAt(0)<'n').forEach(System.out::println);
    }

    public boolean isDuplicate(String email){
        return teachers.stream().anyMatch(p -> p.getEmail().equals(email));
    }

}
