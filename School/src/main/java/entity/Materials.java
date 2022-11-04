package entity;

public class Materials {

    public static int count = 0;
    private int id;
    private String materials;

    public Materials(String materials) {
        this.materials = materials;
        this.id = ++count;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Materials{" +
                "id=" + id +
                ", materials='" + materials + '\'' +
                '}';
    }
}
