package repository;

import models.Lecture;

public interface ILectureRep {

    boolean add(Lecture lecture);

    boolean update (int id, Lecture newLecture);

    boolean delete (int id);

    Lecture get(int id);

    Rep<Lecture> getAll();
}
