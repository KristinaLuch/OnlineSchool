package models;

import java.util.Objects;

public class Homework {

    private static int count = 0;
    private int id;

    private int lectureId;
    private String task;

    public Homework(int lectureId, String task) {
        this.lectureId = lectureId;
        this.task = task;
        this.id = ++count;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getId() {
        return id;
    }

    public static int getCount() {
        return count;
    }

    public int getLectureId() {
        return lectureId;
    }

    public void setLectureId(int lectureId) {
        this.lectureId = lectureId;
    }

    @Override
    public String toString() {
        return "Homework{" +
                "id=" + id +
                ", homework='" + task + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Homework homework1)) return false;
        return id == homework1.id && Objects.equals(task, homework1.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, task);
    }
}
