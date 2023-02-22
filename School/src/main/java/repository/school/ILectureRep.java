package repository.school;

import exceptions.EntityNotFoundException;
import models.school_object.Lecture;

import java.util.ArrayList;

public interface ILectureRep extends ISchoolRep<Lecture> {

    boolean add(Lecture lecture);

    boolean update(int id, Lecture newLecture) throws EntityNotFoundException;

    boolean delete(int id) throws EntityNotFoundException;

    Lecture get(int id) throws EntityNotFoundException;

    ArrayList<Lecture> getAll();
}
