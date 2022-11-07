package repository;

import entity.Course;

public class CourseRep {

    private Course[] courses;

    public CourseRep() {
        this.courses = new Course[10];
    }

    public boolean add(Course course) {
        if (course == null) {
            return false;
        }
        for (int i = 0; i < courses.length; i++) {
            if (courses[i] == null) {
                courses[i] = course;
                if (i == courses.length - 1) {
                    increase();
                }
                break;
            }
        }
        return true;
    }

    private void increase() {
        int newLength = (courses.length * 3) / 2 + 1;
        Course[] tmp = new Course[newLength];
        for (int i = 0; i < courses.length; i++) {
            tmp[i] = courses[i];
        }
        courses = tmp;
    }

    public Course get(int id) {
        if (id < 0) {
            return null;
        }
        if (id == courses[id].getId()) {
            return courses[id];
        } //if id == i
        for (int i = 0; i < courses.length; i++) {
            if (courses[i].getId() == id) {
                return courses[i];
            }
        }
        return null;
    }

    public Course[] getAll() {
        return courses;
    }

}
