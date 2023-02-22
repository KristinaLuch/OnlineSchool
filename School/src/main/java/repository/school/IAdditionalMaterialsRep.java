package repository.school;

import exceptions.EntityNotFoundException;
import models.school_object.AdditionalMaterials;

import java.util.ArrayList;
import java.util.Map;

public interface IAdditionalMaterialsRep {

    boolean add(AdditionalMaterials additionalMaterials);

    boolean update(int id, AdditionalMaterials newAdditionalMaterials) throws EntityNotFoundException;

    boolean delete(int id) throws EntityNotFoundException;

    AdditionalMaterials get(int id) throws EntityNotFoundException;

    Map<Integer, ArrayList<AdditionalMaterials>> getAll();

    ArrayList<AdditionalMaterials> getAdditionalMaterials(int lectureId);


}
