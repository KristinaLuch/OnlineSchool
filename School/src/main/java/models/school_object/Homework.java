package models.school_object;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

public class Homework implements Serializable {
    private static int count = 0;
    private final Integer id;
    private final int lectureId;
    private final String task;

    private LocalDateTime deadline;

    public Homework(Integer id, int lectureId, String task) {
        this.id = id;
        this.lectureId = lectureId;
        this.task = task;
        count++;
    }

    public Homework(int lectureId, String task) {
        this.lectureId = lectureId;
        this.task = task;
        this.id = ++count;
    }

    public static void setCount(int count) {
        Homework.count = count;
    }

    public int getId() {
        return id;
    }

    public int getLectureId() {
        return lectureId;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "Homework{" +
                "id=" + id +
                ", lectureId=" + lectureId +
                ", task='" + task + '\'' +
                deadline() +
                '}';
    }

    private String deadline(){
        if(deadline == null){
            return "";
        }
        return ", deadline=" + deadline.format(DateTimeFormatter.ofPattern("MMM d, HH:mm", Locale.ENGLISH));
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
