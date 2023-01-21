package repository;

import models.Homework;
import models.SchoolObject;

public class HomeworkRep extends SchoolRep{

    public HomeworkRep(Rep<SchoolObject> schoolObjects) {
        super(schoolObjects);
    }
}
