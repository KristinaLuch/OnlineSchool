package repository.school;

import exceptions.EntityNotFoundException;
import models.school_object.Homework;

import java.util.ArrayList;
import java.util.Map;

public interface IHomeworkRep {

    boolean add(Homework homework);

    boolean update (int id, Homework newHomework) throws EntityNotFoundException;

    boolean delete (int id) throws EntityNotFoundException;

    Homework get(int id) throws EntityNotFoundException;

    Map<Integer, ArrayList<Homework>> getAll();

    ArrayList<Homework> getHomeworks(int lectureId);
    
}
