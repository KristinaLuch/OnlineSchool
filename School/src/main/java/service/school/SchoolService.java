package service.school;

import exceptions.EntityNotFoundException;

public interface SchoolService<T> {

    T create();

    void read_by_id(int id) throws EntityNotFoundException;

    void readAll();

    boolean delete(int id) throws EntityNotFoundException;
}
