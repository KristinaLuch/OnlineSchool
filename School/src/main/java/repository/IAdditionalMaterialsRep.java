package repository;

import exceptions.EntityNotFoundException;
import models.school_object.AdditionalMaterials;
import models.school_object.Course;

import java.util.ArrayList;

public interface IAdditionalMaterialsRep extends ISchoolRep<AdditionalMaterials>{

    boolean add(AdditionalMaterials additionalMaterials);

    boolean update (int id, AdditionalMaterials newAdditionalMaterials) throws EntityNotFoundException;

    boolean delete (int id) throws EntityNotFoundException;

    AdditionalMaterials get(int id) throws EntityNotFoundException;

    ArrayList<AdditionalMaterials> getAll();


}
