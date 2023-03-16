package repository.school.impl;

import exceptions.EntityNotFoundException;
import models.school_object.Lecture;
import repository.school.ILectureRep;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class LectureRep implements ILectureRep {

    protected ArrayList<Lecture> lectures;

    public LectureRep(ArrayList<Lecture> lectures) {
        this.lectures = lectures;
    }

    @Override
    public boolean add(Lecture lecture) {
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
        for (Lecture lecture : lectures) {
            findObj = lecture;
            if (findObj.getId() == id) {
                return findObj;
            }
        }
        throw new EntityNotFoundException();
    }

    public boolean isExist(int id){
        try {
            Lecture lecture = get(id);
        } catch (EntityNotFoundException e) {
            return false;
        }
        return true;
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
        for (Lecture lecture : lectures) {
            obj = lecture;
            if (obj != null) {
                System.out.println("id = " + obj.getId() + " - " + obj);
            }
        }
    }

    public void printLectureBeforeDate(LocalDateTime date){
        Predicate<Lecture> pred = lecture -> lecture.getLectureDate().get().isBefore(date);
        List<Lecture> lecturesBeforeDate = lectures.stream()
                .filter(lecture -> lecture.getLectureDate().isPresent())
                .filter(pred).toList();
        System.out.println(lecturesBeforeDate);
    }

    public void printLectureAfterDate(LocalDateTime date){

        Predicate<Lecture> pred = lecture -> lecture.getLectureDate().get().isAfter(date);
        List<Lecture> lecturesAfterDate = lectures.stream()
                .filter(lecture -> lecture.getLectureDate().isPresent()).filter(pred).toList();
        System.out.println(lecturesAfterDate);
    }

    public void printLectureFromDateToDate(LocalDateTime dateFrom, LocalDateTime dateTo){
        Predicate<Lecture> predFrom = lecture -> lecture.getLectureDate().get().isBefore(dateTo);
        Predicate<Lecture> predTo = lecture -> lecture.getLectureDate().get().isAfter(dateFrom);
        List<Lecture> lecturesFromTo = lectures.stream()
                .filter(lecture -> lecture.getLectureDate().isPresent())
                .filter(predFrom).filter(predTo).toList();
        System.out.println(lecturesFromTo);
    }


}
