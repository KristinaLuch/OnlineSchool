package repository.school.impl;

import dao.DBHomeworkService;
import exceptions.EntityNotFoundException;
import models.school_object.Homework;
import repository.school.IHomeworkRep;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class HomeworkRep implements IHomeworkRep {
    private Map<Integer, ArrayList<Homework>> homeworks;
    private DBHomeworkService dbHomeworkService;

    public HomeworkRep(Map<Integer, ArrayList<Homework>> homeworks, DBHomeworkService dbHomeworkService) {
        this.homeworks = homeworks;
        this.dbHomeworkService = dbHomeworkService;
        download();
    }

    private void download(){
        homeworks = dbHomeworkService.getAllFromBD();
    }

    @Override
    public boolean add(Homework homework) {
        if (homework == null) {
            return false;
        }
        int key = homework.getLectureId();
        ArrayList<Homework> value = homeworks.get(key);

        if (value == null){
            value = new ArrayList<>();
        }
        value.add(homework);
        homeworks.put(key, value);
        return true;
    }

    public boolean isExist(int id){
        try {
            Homework homework = get(id);
        } catch (EntityNotFoundException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean update(int id, Homework newHomework) throws EntityNotFoundException {
        if (id <= 0) {
            throw new EntityNotFoundException();
        }
        Homework oldHomework = get(id);
        int key = oldHomework.getLectureId();
        ArrayList<Homework> list = homeworks.get(key);
        int index = list.indexOf(oldHomework);
        list.add(index, newHomework);
        homeworks.put(key, list);
        return true;
    }

    @Override
    public Homework get(int id) throws EntityNotFoundException {
        if (id <= 0) {
            throw new EntityNotFoundException();
        }
        ArrayList<Homework> findAlist;
        Homework findHomework;
        int findId;

        Set<Integer> keySet = homeworks.keySet();

        for(int key:keySet){
            findAlist = homeworks.get(key);
            for (int ind = 0; ind <findAlist.size(); ind++){
                findHomework = findAlist.get(ind);
                findId = findHomework.getId();
                if (findId == id) {
                    return findHomework;
                }
            }
        }
        throw new EntityNotFoundException();
    }

    @Override
    public Map<Integer, ArrayList<Homework>> getAll() {
        return homeworks;
    }

    @Override
    public ArrayList<Homework> getHomeworks(int lectureId) {
        return homeworks.get(lectureId);
    }

    @Override
    public boolean delete(int id) throws EntityNotFoundException {
        if (id <= 0) {
            throw new EntityNotFoundException();
        }
        Homework homework = get(id);
        ArrayList<Homework> findAlist = homeworks.get(homework.getLectureId());
        findAlist.remove(homework);
        homeworks.put(homework.getLectureId(), findAlist);

        throw new EntityNotFoundException();
    }

}
