package models.school_object;

import java.io.Serializable;
import java.util.Objects;

public class Homework implements Serializable {

    private static int count = 0;
    private final Integer id;

    private final int lectureId;
    private final String task;

    public Homework(int lectureId, String task) {
        this.lectureId = lectureId;
        this.task = task;
        this.id = ++count;
    }

    public int getId() {
        return id;
    }

    public int getLectureId() {
        return lectureId;
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
        return id.equals(homework1.id) && Objects.equals(task, homework1.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, task);
    }
}
