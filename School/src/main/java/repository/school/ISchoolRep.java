package repository.school;

import exceptions.EntityNotFoundException;

import java.util.ArrayList;

public interface ISchoolRep<T> {

    boolean add(T element);

    boolean update(int id, T newElement) throws EntityNotFoundException;

    boolean delete(int id) throws EntityNotFoundException;

    T get(int id) throws EntityNotFoundException;

    ArrayList<T> getAll();


}
