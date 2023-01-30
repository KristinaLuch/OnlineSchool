package repository;

import models.Course;
import models.Lecture;
import models.Person;

public interface ICourseRep {

    boolean add(Course course);

    boolean update (int id, Course newCourse);

    boolean delete (int id);

    Course get(int id);

    Rep<Course> getAll();


}
