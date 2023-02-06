package repository;

import exceptions.EntityNotFoundException;
import models.Materials;

public interface IMaterialsRep {

    boolean add(Materials materials);

    boolean update (int id, Materials newMaterials) throws EntityNotFoundException;

    boolean delete (int id) throws EntityNotFoundException;

    Materials get(int id) throws EntityNotFoundException;

    Rep<Materials> getAll();

}
