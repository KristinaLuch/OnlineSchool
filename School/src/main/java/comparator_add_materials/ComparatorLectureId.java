package comparator_add_materials;

import models.school_object.AdditionalMaterials;

import java.util.Comparator;

public class ComparatorLectureId implements Comparator<AdditionalMaterials> {
    @Override
    public int compare(AdditionalMaterials o1, AdditionalMaterials o2) {

        if(o1.getLectureId() > o2.getLectureId()){
            return 1;
        }
        if(o1.getLectureId()<o2.getLectureId()){
            return -1;
        }
        return 0;
    }
}
