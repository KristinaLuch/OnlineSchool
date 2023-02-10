package repository;

import exceptions.EntityNotFoundException;
import models.Homework;

import java.util.ArrayList;

public interface IHomeworkRep {

    boolean add(Homework homework);

    boolean update (int id, Homework newHomework) throws EntityNotFoundException;

    boolean delete (int id) throws EntityNotFoundException;

    Homework get(int id) throws EntityNotFoundException;

    ArrayList<Homework> getAll();
    
}
