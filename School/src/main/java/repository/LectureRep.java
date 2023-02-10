package repository;

import exceptions.EntityNotFoundException;
import models.Lecture;

import java.util.ArrayList;

public class LectureRep implements ILectureRep{

    protected ArrayList<Lecture> lectures;

    public LectureRep(ArrayList<Lecture> lectures) {
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
    public boolean update(int id, Lecture newLecture) throws EntityNotFoundException {
        if (id <= 0) {
            throw new EntityNotFoundException();
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
        throw new EntityNotFoundException();
    }

    @Override
    public boolean delete(int id) throws EntityNotFoundException {
        if (id <= 0) {
            throw new EntityNotFoundException();
        }
        int indObj;
        for (int i = 0; i < lectures.size(); i++) {
            indObj = lectures.get(i).getId();
            if (indObj == id) {
                lectures.remove(i);
                return true;
            }
        }
       throw new EntityNotFoundException();
    }

    @Override
    public Lecture get(int id) throws EntityNotFoundException {
        if (id <= 0) {
            throw new EntityNotFoundException();
        }
        Lecture findObj;
        for (int i = 0; i < lectures.size(); i++) {
            findObj = lectures.get(i);
            if (findObj.getId() == id) {
                return findObj;
            }
        }
        throw new EntityNotFoundException();
    }

    @Override
    public ArrayList<Lecture> getAll() {
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
