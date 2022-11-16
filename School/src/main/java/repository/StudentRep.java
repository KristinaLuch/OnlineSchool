package repository;

import models.Student;

public class StudentRep extends SchoolRep{

    public StudentRep() {
        this.schoolEntities = new Student[10];
    }


}
