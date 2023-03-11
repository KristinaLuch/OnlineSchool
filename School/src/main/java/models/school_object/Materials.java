package models.school_object;

import java.io.Serializable;
import java.util.Objects;

public class Materials implements Serializable {

    private static int count = 0;
    private final Integer id;
    private final String materials;

    public Materials(String materials) {
        this.materials = materials;
        this.id = ++count;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Materials materials1)) return false;
        return Objects.equals(materials, materials1.materials);
    }

    @Override
    public int hashCode() {
        return Objects.hash(materials);
    }
}
