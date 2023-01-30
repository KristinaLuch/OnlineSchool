package repository;

import models.Course;
import models.Lecture;

public class LectureRep implements ILectureRep{

    protected Rep<Lecture> lectures;

    public LectureRep(Rep<Lecture> lectures) {
        this.lectures = lectures;
    }

    @Override
    public boolean add(Lecture lecture) {
        if (lecture == null) {
            return false;
        }
        lectures.add(lecture);
        return true;
    }

    @Override
    public boolean update(int id, Lecture newLecture) {
        if (id <= 0) {
            return false;
        }
        Lecture findLecture;
        int index;
        for (int i = 0; i < lectures.size(); i++) {
            findLecture = lectures.get(i);
            if (findLecture.getId() == id) {
                index = i;
                lectures.add(index, newLecture);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        if (id <= 0) {
            return false;
        }
        int indObj;
        for (int i = 0; i < lectures.size(); i++) {
            indObj = lectures.get(i).getId();
            if (indObj == id) {
                lectures.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public Lecture get(int id) {
        if (id <= 0) {
            return null;
        }
        Lecture findObj;
        for (int i = 0; i < lectures.size(); i++) {
            findObj = lectures.get(i);
            if (findObj.getId() == id) {
                return findObj;
            }
        }
        return null;
    }

    @Override
    public Rep<Lecture> getAll() {
        return lectures;
    }


    public void printAll(){

        if (lectures == null||lectures.size() == 0){
            System.out.println("You haven't created anything yet");
        }
        Lecture obj;
        for (int i = 0; i<lectures.size(); i++){
            obj = lectures.get(i);
            if(obj != null) {
                System.out.println("id = " +obj.getId() + " - "+obj);
            }
        }
    }
}
