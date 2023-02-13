package comparator_add_materials;

import models.school_object.AdditionalMaterials;

import java.util.Comparator;

public class ComparatorId implements Comparator<AdditionalMaterials> {
    @Override
    public int compare(AdditionalMaterials o1, AdditionalMaterials o2) {
        if (o1.getId() > o2.getId()){
            return 1;
        }
        return -1;
    }
}
