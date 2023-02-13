package repository;

import exceptions.EntityNotFoundException;
import models.school_object.Materials;

import java.util.ArrayList;

public interface IMaterialsRep extends ISchoolRep<Materials>{

    boolean add(Materials materials);

    boolean update (int id, Materials newMaterials) throws EntityNotFoundException;

    boolean delete (int id) throws EntityNotFoundException;

    Materials get(int id) throws EntityNotFoundException;

    ArrayList<Materials> getAll();

}
