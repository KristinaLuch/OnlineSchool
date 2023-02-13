package repository;

import exceptions.EntityNotFoundException;
import models.school_object.Course;

import java.util.ArrayList;

public interface ICourseRep extends ISchoolRep<Course>{

    boolean add(Course course);

    boolean update (int id, Course newCourse) throws EntityNotFoundException;

    boolean delete (int id) throws EntityNotFoundException;

    Course get(int id) throws EntityNotFoundException;

    ArrayList<Course> getAll();

}
