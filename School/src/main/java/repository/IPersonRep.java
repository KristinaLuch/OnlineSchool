package repository;

import exceptions.EntityNotFoundException;
import models.Lecture;
import models.Person;

public interface IPersonRep {

    boolean add(Person person);

    boolean update (int id, Person newPerson) throws EntityNotFoundException;

    boolean delete (int id) throws EntityNotFoundException;

    Person get(int id) throws EntityNotFoundException;

    Rep<Person> getAll();

}
