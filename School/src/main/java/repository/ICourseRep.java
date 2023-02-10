package repository;

import exceptions.EntityNotFoundException;
import models.Course;

import java.util.ArrayList;

public interface ICourseRep {

    boolean add(Course course);

    boolean update (int id, Course newCourse) throws EntityNotFoundException;

    boolean delete (int id) throws EntityNotFoundException;

    Course get(int id) throws EntityNotFoundException;

    ArrayList<Course> getAll();


}
