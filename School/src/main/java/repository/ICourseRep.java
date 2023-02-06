package repository;

import exceptions.EntityNotFoundException;
import models.Course;

public interface ICourseRep {

    boolean add(Course course);

    boolean update (int id, Course newCourse) throws EntityNotFoundException;

    boolean delete (int id) throws EntityNotFoundException;

    Course get(int id) throws EntityNotFoundException;

    Rep<Course> getAll();


}
