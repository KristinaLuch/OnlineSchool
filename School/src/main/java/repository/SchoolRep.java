package repository;

import models.Course;
import models.SchoolEntity;

public abstract class SchoolRep {
    protected SchoolEntity[] schoolEntities;

    public boolean add(SchoolEntity schoolEntity) {
        if (schoolEntity == null) {
            return false;
        }
        for (int i = 0; i < schoolEntities.length; i++) {
            if (schoolEntities[i] == null) {
                schoolEntities[i] = schoolEntity;
                if (i == schoolEntities.length - 1) {
                    increase();
                }
                break;
            }
        }
        return true;
    }

    private void increase() {
        int newLength = (schoolEntities.length * 3) / 2 + 1;
        SchoolEntity[] tmp = new Course[newLength];
        for (int i = 0; i < schoolEntities.length; i++) {
            tmp[i] = schoolEntities[i];
        }
        schoolEntities = tmp;
    }

    public SchoolEntity getById(int id) {
        if (id < 0) {
            return null;
        }
        if (id == schoolEntities[id].getId()) {
            return schoolEntities[id];
        } //if id == i
        for (int i = 0; i < schoolEntities.length; i++) {
            if (schoolEntities[i].getId() == id) {
                return schoolEntities[i];
            }
        }
        return null;
    }

    public SchoolEntity[] getAll() {
        return schoolEntities;
    }

}
