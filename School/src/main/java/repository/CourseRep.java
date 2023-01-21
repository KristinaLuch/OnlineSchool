package repository;

import models.Course;
import models.SchoolObject;

public class CourseRep extends SchoolRep{

    public CourseRep(Rep<SchoolObject> schoolObjects) {
        super(schoolObjects);
    }
}
