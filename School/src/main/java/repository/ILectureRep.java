package repository;

import exceptions.EntityNotFoundException;
import models.Lecture;

public interface ILectureRep {

    boolean add(Lecture lecture);

    boolean update (int id, Lecture newLecture) throws EntityNotFoundException;

    boolean delete (int id) throws EntityNotFoundException;

    Lecture get(int id) throws EntityNotFoundException;

    Rep<Lecture> getAll();
}
