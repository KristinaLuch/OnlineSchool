package repository;

import exceptions.EntityNotFoundException;
import models.school_object.Homework;

import java.util.ArrayList;

public class HomeworkRep implements IHomeworkRep{
    protected ArrayList<Homework> homeworks;

    public HomeworkRep(ArrayList<Homework> homeworks) {
        this.homeworks = homeworks;
    }

    @Override
    public boolean add(Homework homework) {
        if (homework == null) {
            return false;
        }
        homeworks.add(homework);
        return true;
    }

    @Override
    public boolean update(int id, Homework newHomework) throws EntityNotFoundException {
        if (id <= 0) {
            throw new EntityNotFoundException();
        }
        Homework findHomework;
        int index;
        for (int i = 0; i < homeworks.size(); i++) {
            findHomework = homeworks.get(i);
            if (findHomework.getId() == id) {
                index = i;
                homeworks.add(index, newHomework);
                return true;
            }
        }
        throw new EntityNotFoundException();
    }

    @Override
    public Homework get(int id) throws EntityNotFoundException {
        if (id <= 0) {
            throw new EntityNotFoundException();
        }
        Homework findObj;
        for (int i = 0; i < homeworks.size(); i++) {
            findObj = homeworks.get(i);
            if (findObj.getId() == id) {
                return findObj;
            }
        }
        throw new EntityNotFoundException();
    }

    @Override
    public boolean delete(int id) throws EntityNotFoundException {
        if (id <= 0) {
            throw new EntityNotFoundException();
        }
        int indObj;
        for (int i = 0; i < homeworks.size(); i++) {
            indObj = homeworks.get(i).getId();
            if (indObj == id) {
                homeworks.remove(i);
                return true;
            }
        }
        throw new EntityNotFoundException();
    }

    @Override
    public ArrayList<Homework> getAll() {
        return homeworks;
    }

    public void printAll(){

        if (homeworks == null||homeworks.size() == 0){
            System.out.println("You haven't created anything yet");
        }
        Homework obj;
        for (int i = 0; i<homeworks.size(); i++){
            obj = homeworks.get(i);
            if(obj != null) {
                System.out.println("id = " +obj.getId() + " - "+obj);
            }
        }
    }
}
