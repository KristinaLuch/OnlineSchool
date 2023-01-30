package repository;

import models.Homework;

public interface IHomeworkRep {

    boolean add(Homework homework);

    boolean update (int id, Homework newHomework);

    boolean delete (int id);

    Homework get(int id);

    Rep<Homework> getAll();
    
}
