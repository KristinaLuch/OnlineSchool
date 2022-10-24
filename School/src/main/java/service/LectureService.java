package service;

import entity.Lecture;

public class LectureService {


    public Lecture createLecture(String subject, String homework, String materials){

        return new Lecture(subject, homework, materials);

    }

}
