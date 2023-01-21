package repository;


import models.Person;
import models.SchoolObject;

public class PersonRep extends SchoolRep{

    public PersonRep(Rep<SchoolObject> schoolObjects) {
        super(schoolObjects);
    }
}
