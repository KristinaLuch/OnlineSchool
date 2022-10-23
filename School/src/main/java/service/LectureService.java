package service;

import entity.Lecture;

public class LectureService {

    public int countLecture = 0;

    public void addLecture (int id, String subject, String homework, String materials){
        Lecture lecture = new Lecture(id, subject, homework, materials);
        countLecture++;
    }

}
