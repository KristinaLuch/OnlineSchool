package models.school_object;

import models.ResourceType;

import java.io.Serializable;
import java.util.Objects;

public class AdditionalMaterials implements Serializable, Comparable<AdditionalMaterials> {

    private final Integer id;
    private static int count = 0;
    private String name;

    private int lectureId;

    private ResourceType resourceType;

    public AdditionalMaterials(int id, String name, int lectureId, ResourceType resourceType) {
        this.id = id;
        this.name = name;
        this.lectureId = lectureId;
        this.resourceType = resourceType;
    }

    public AdditionalMaterials() {
        count++;
        id = count;
    }

    public static void setCount(int count) {
        AdditionalMaterials.count = count;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLectureId() {
        return lectureId;
    }

    public void setLectureId(int lectureId) {
        this.lectureId = lectureId;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    @Override
    public String toString() {
        return "AdditionalMaterials{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lectureId=" + lectureId +
                ", resourceType=" + resourceType +
                '}';
    }


    @Override
    public int compareTo(AdditionalMaterials o) {
        return Integer.compare(this.getLectureId(), o.getLectureId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdditionalMaterials that)) return false;
        return lectureId == that.lectureId && Objects.equals(id, that.id) && Objects.equals(name, that.name) && resourceType == that.resourceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lectureId, resourceType);
    }
}
