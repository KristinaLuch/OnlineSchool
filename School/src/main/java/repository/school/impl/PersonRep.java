package repository.school.impl;


import exceptions.EntityNotFoundException;
import models.Role;
import models.school_object.Person;
import repository.school.IPersonRep;

import java.util.ArrayList;

abstract public class PersonRep implements IPersonRep {

    protected ArrayList<Person> students;
    protected ArrayList<Person> teachers;

    public PersonRep(ArrayList<Person> teachers, ArrayList<Person> students) {
        this.teachers = teachers;
        this.students = students;

    }

}
