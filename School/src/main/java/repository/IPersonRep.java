package repository;

import models.Lecture;
import models.Person;

public interface IPersonRep {

    boolean add(Person person);

    boolean update (int id, Person newPerson);

    boolean delete (int id);

    Person get(int id);

    Rep<Person> getAll();

}
