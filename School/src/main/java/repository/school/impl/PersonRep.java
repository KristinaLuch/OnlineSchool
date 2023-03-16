package repository.school.impl;


import exceptions.EntityNotFoundException;
import models.Role;
import models.school_object.Person;
import repository.school.IPersonRep;

import java.util.ArrayList;

public class PersonRep implements IPersonRep {

    protected ArrayList<Person> persons;

    public PersonRep(ArrayList<Person> persons) {
        this.persons = persons;
    }

    @Override
    public boolean add(Person person) {
        if (person == null) {
            return false;
        }
        persons.add(person);
        return true;
    }

    @Override
    public boolean update(int id, Person newPerson) throws EntityNotFoundException {
        if (id <= 0) {
            throw new EntityNotFoundException();
        }
        Person findPerson;
        int index;
        for (int i = 0; i < persons.size(); i++) {
            findPerson = persons.get(i);
            if (findPerson.getId() == id) {
                index = i;
                persons.add(index, newPerson);
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
        for (int i = 0; i < persons.size(); i++) {
            findObj = persons.get(i);
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
        for (int i = 0; i < persons.size(); i++) {
            indObj = persons.get(i).getId();
            if (indObj == id) {
                persons.remove(i);
                return true;
            }
        }
        throw new EntityNotFoundException();
    }

    @Override
    public ArrayList<Person> getAll() {
        return persons;
    }

    public void printAll(){

        if (persons == null||persons.size() == 0){
            System.out.println("You haven't created anything yet");
        }
        Person obj;
        for (int i = 0; i<persons.size(); i++){
            obj = persons.get(i);
            if(obj != null) {
                System.out.println("id = " +obj.getId() + " - "+obj);
            }
        }
    }

    public void printTeacherBeforeN(){
        persons.stream().filter(person -> person.getRole().equals(Role.TEACHER))
                .filter(person -> person.getLastname().toLowerCase().charAt(0)<'n').forEach(System.out::println);
    }
}
