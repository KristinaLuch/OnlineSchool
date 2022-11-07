package repository;

import entity.Homework;

public class HomeworkRep {
    private Homework[] homeworks;

    public HomeworkRep() {
        this.homeworks = new Homework[10];
    }

    public boolean add(Homework homework) {
        if (homework == null) {
            return false;
        }
        for (int i = 0; i < homeworks.length; i++) {
            if (homeworks[i] == null) {
                homeworks[i] = homework;
                if (i == homeworks.length - 1) {
                    increase();
                }
                break;
            }
        }
        return true;
    }

    private void increase() {
        int newLength = (homeworks.length * 3) / 2 + 1;
        System.out.println(newLength);
        Homework[] tmp = new Homework[newLength];
        for (int i = 0; i < homeworks.length; i++) {
            tmp[i] = homeworks[i];
        }
        homeworks = tmp;
    }

    public Homework get(int id) {
        if (id < 0) {
            return null;
        }
        if (id == homeworks[id].getId()) {
            return homeworks[id];
        } //if id == i
        for (int i = 0; i < homeworks.length; i++) {
            if (homeworks[i].getId() == id) {
                return homeworks[i];
            }
        }
        return null;
    }

    public Homework[] getAll() {
        return homeworks;
    }

}
