package comparator_add_materials;

import models.school_object.AdditionalMaterials;

import java.util.Comparator;

public class ComparatorResourceType implements Comparator<AdditionalMaterials> {
    @Override
    public int compare(AdditionalMaterials o1, AdditionalMaterials o2) {
       return o1.getResourceType().compareTo(o2.getResourceType());
    }
}
