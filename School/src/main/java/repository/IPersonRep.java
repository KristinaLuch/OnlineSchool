package repository;

import exceptions.EntityNotFoundException;
import models.school_object.Person;

import java.util.ArrayList;

public interface IPersonRep {

    boolean add(Person person);

    boolean update (int id, Person newPerson) throws EntityNotFoundException;

    boolean delete (int id) throws EntityNotFoundException;

    Person get(int id) throws EntityNotFoundException;

    ArrayList<Person> getAll();

}
