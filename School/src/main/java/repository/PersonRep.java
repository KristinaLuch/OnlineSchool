package repository;


import models.Lecture;
import models.Person;

public class PersonRep implements IPersonRep{

    protected Rep<Person> persons;

    public PersonRep(Rep<Person> persons) {
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
    public boolean update(int id, Person newPerson) {
        if (id <= 0) {
            return false;
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
        return false;
    }

    @Override
    public Person get(int id) {
        if (id <= 0) {
            return null;
        }
        Person findObj;
        for (int i = 0; i < persons.size(); i++) {
            findObj = persons.get(i);
            if (findObj.getId() == id) {
                return findObj;
            }
        }
        return null;
    }

    public boolean delete(int id) {
        if (id <= 0) {
            return false;
        }
        int indObj;
        for (int i = 0; i < persons.size(); i++) {
            indObj = persons.get(i).getId();
            if (indObj == id) {
                persons.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public Rep<Person> getAll() {
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
}
