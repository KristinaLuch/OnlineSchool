package repository;

import exceptions.EntityNotFoundException;
import models.Homework;

public interface IHomeworkRep {

    boolean add(Homework homework);

    boolean update (int id, Homework newHomework) throws EntityNotFoundException;

    boolean delete (int id) throws EntityNotFoundException;

    Homework get(int id) throws EntityNotFoundException;

    Rep<Homework> getAll();
    
}
