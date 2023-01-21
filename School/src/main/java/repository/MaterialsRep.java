package repository;

import models.Materials;
import models.SchoolObject;

public class MaterialsRep extends SchoolRep {
    public MaterialsRep(Rep<SchoolObject> schoolObjects) {
        super(schoolObjects);
    }
}
