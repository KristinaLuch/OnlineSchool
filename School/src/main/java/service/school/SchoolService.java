package service.school;

import models.SchoolObject;
import repository.SchoolRep;

public abstract class SchoolService {

    public SchoolService(SchoolRep schoolRep) {
        this.schoolRep = schoolRep;
    }

    protected SchoolRep schoolRep;

    public void addToRep(SchoolObject schoolObject){
        schoolRep.add(schoolObject);
    }
}
