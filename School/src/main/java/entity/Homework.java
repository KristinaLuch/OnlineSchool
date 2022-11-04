package entity;

public class Homework {

    public static int count = 0;
    private int id;
    private String homework;

    public Homework(String homework) {
        this.homework = homework;
        this.id = ++count;
    }

    public String getHomework() {
        return homework;
    }

    public void setHomework(String homework) {
        this.homework = homework;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Homework{" +
                "id=" + id +
                ", homework='" + homework + '\'' +
                '}';
    }
}
