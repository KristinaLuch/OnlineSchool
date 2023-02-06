package repository;

import exceptions.EntityNotFoundException;
import models.Course;

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
    public boolean update(int id, Course newCourse) throws EntityNotFoundException {
        if (id <= 0) {
            throw new EntityNotFoundException();
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
        throw new EntityNotFoundException();
    }

    @Override
    public Course get(int id) throws EntityNotFoundException {
        if (id <= 0) {
            throw new EntityNotFoundException();
        }
        Course findObj;
        for (int i = 0; i < courses.size(); i++) {
            findObj = courses.get(i);
            if (findObj.getId() == id) {
                return findObj;
            }
        }
        throw new EntityNotFoundException();
    }

    @Override
    public boolean delete(int id) throws EntityNotFoundException {
        if (id <= 0) {
            throw new EntityNotFoundException();
        }
        int indObj;
        for (int i = 0; i < courses.size(); i++) {
            indObj = courses.get(i).getId();
            if (indObj == id) {
                courses.remove(i);
                return true;
            }
        }
        throw new EntityNotFoundException();
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
