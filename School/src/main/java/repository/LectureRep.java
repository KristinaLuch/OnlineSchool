package repository;

import models.Lecture;
import models.SchoolObject;

public class LectureRep extends SchoolRep {

    public LectureRep(Rep<SchoolObject> schoolObjects) {
        super(schoolObjects);
    }
}
