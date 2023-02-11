package models.school_object;

import models.ResourceType;

public class AdditionalMaterials implements SchoolObject {

    private final Integer id;

    private static int count = 0;
    private String name;

    private int lectureId;

    private ResourceType resourceType;

    public AdditionalMaterials() {
        count++;
        id = count;
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
}
