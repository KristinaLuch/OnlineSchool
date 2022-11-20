package repository;

import models.Course;
import models.Lecture;
import models.SchoolObject;

import java.util.Arrays;

public abstract class SchoolRep {
    protected SchoolObject[] schoolObjects;

    public SchoolObject[] getAll() {
        return schoolObjects;
    }

    public boolean add(SchoolObject schoolObject) {
        if (schoolObject == null) {
            return false;
        }
        for (int i = 0; i < schoolObjects.length; i++) {
            if (schoolObjects[i] == null) {
                schoolObjects[i] = schoolObject;
                if (i == schoolObjects.length - 1) {
                    increase();
                }
                break;
            }
        }
        return true;
    }

    public SchoolObject getById(int id) {
        if (id <= 0) {
            return null;
        }

        for (int i = 0; i < schoolObjects.length; i++) {
            if (schoolObjects[i] == null){
                continue;
            }
            if (schoolObjects[i].getId() == id) {
                return schoolObjects[i];
            }
        }
        return null;
    }
    public boolean deleteById(int id) {
        if (id <= 0) {
            return false;
        }
        for (int i = 0; i < schoolObjects.length; i++) {
            if (schoolObjects[i].getId() == id) {
                schoolObjects[i] = null;
                defragmentation();
                return true;
            }
        }
        return false;
}

    private void defragmentation() {
        for (int i = 0; i < schoolObjects.length; i++) {
            if (schoolObjects[i] == null) {
                for (int j = i; j < schoolObjects.length; j++) {
                    if (schoolObjects[j] != null) {
                        schoolObjects[i] = schoolObjects[j];
                        schoolObjects[j] = null;
                    }
                }
            }
        }
    }

    private void increase() {
        int newLength = (schoolObjects.length * 3) / 2 + 1;
        SchoolObject[] tmp = new Course[newLength];
        for (int i = 0; i < schoolObjects.length; i++) {
            tmp[i] = schoolObjects[i];
        }
        schoolObjects = tmp;
    }


}
