package repository;

import models.Homework;
import models.Lecture;

public class HomeworkRep implements IHomeworkRep{
    protected Rep<Homework> homeworks;

    public HomeworkRep(Rep<Homework> homeworks) {
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
    public boolean update(int id, Homework newHomework) {
        if (id <= 0) {
            return false;
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
        return false;
    }

    @Override
    public Homework get(int id) {
        if (id <= 0) {
            return null;
        }
        Homework findObj;
        for (int i = 0; i < homeworks.size(); i++) {
            findObj = homeworks.get(i);
            if (findObj.getId() == id) {
                return findObj;
            }
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        if (id <= 0) {
            return false;
        }
        int indObj;
        for (int i = 0; i < homeworks.size(); i++) {
            indObj = homeworks.get(i).getId();
            if (indObj == id) {
                homeworks.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public Rep<Homework> getAll() {
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
