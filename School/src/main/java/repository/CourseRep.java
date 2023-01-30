package repository;

import models.Course;
import models.Lecture;
import models.Person;

import java.util.List;

public class CourseRep implements ICourseRep{

    protected Rep<Course> courses;

    public CourseRep(Rep<Course> courses) {
        this.courses = courses;
    }

   @Override
    public boolean add(Course course) {
        if (course == null) {
            return false;
        }
        courses.add(course);
        return true;
    }

    @Override
    public boolean update(int id, Course newCourse) {
        if (id <= 0) {
            return false;
        }
        Course findCourse;
        int index;
        for (int i = 0; i < courses.size(); i++) {
            findCourse = courses.get(i);
            if (findCourse.getId() == id) {
               index = i;
               courses.add(index, newCourse);
               return true;
            }
        }
        return false;
    }

    @Override
    public Course get(int id) {
        if (id <= 0) {
            return null;
        }
        Course findObj;
        for (int i = 0; i < courses.size(); i++) {
            findObj = courses.get(i);
            if (findObj.getId() == id) {
                return findObj;
            }
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        if (id <= 0) {
            return false;
        }
        int indObj;
        for (int i = 0; i < courses.size(); i++) {
            indObj = courses.get(i).getId();
            if (indObj == id) {
                courses.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public Rep<Course> getAll() {
        return courses;
    }


    public void printAll(){

        if (courses == null||courses.size() == 0){
            System.out.println("You haven't created anything yet");
        }
        Course obj;
        for (int i = 0; i<courses.size(); i++){
            obj = courses.get(i);
            if(obj != null) {
                System.out.println("id = " +obj.getId() + " - "+obj);
            }
        }
    }
}
