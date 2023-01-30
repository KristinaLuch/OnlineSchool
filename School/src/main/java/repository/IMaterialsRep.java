package repository;

import models.Materials;

public interface IMaterialsRep {

    boolean add(Materials materials);

    boolean update (int id, Materials newMaterials);

    boolean delete (int id);

    Materials get(int id);

    Rep<Materials> getAll();

}
