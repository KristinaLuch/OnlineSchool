package service.school;

import exceptions.EntityNotFoundException;
import models.school_object.SchoolObject;

public interface SchoolService {

    SchoolObject create();

    void read_by_id(int id) throws EntityNotFoundException;

    void readAll();

    boolean delete(int id) throws EntityNotFoundException;
}
