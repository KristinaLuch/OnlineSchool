package service;

import entity.Lecture;

public class LectureService {

    public int countLecture = 0;

    public Lecture createLecture(String subject, String homework, String materials){
        countLecture++;
        int idCourse = countLecture;
        Lecture lecture = new Lecture(idCourse, subject, homework, materials);

        return lecture;
    }

}
