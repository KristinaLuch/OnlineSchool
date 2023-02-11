package repository;

import exceptions.EntityNotFoundException;
import models.school_object.Materials;

import java.util.ArrayList;

public class MaterialsRep implements IMaterialsRep{
    protected ArrayList<Materials> materialsRep;

    public MaterialsRep(ArrayList<Materials> materialsRep) {
        this.materialsRep = materialsRep;
    }

    @Override
    public boolean add(Materials materials) {
        if (materials == null) {
            return false;
        }
        materialsRep.add(materials);
        return true;
    }

    @Override
    public boolean update(int id, Materials newMaterials) throws EntityNotFoundException {
        if (id <= 0) {
            throw new EntityNotFoundException();
        }
        Materials findMaterials;
        int index;
        for (int i = 0; i < materialsRep.size(); i++) {
            findMaterials = materialsRep.get(i);
            if (findMaterials.getId() == id) {
                index = i;
                materialsRep.add(index, newMaterials);
                return true;
            }
        }
        throw new EntityNotFoundException();
    }

    @Override
    public Materials get(int id) throws EntityNotFoundException {
        if (id <= 0) {
            throw new EntityNotFoundException();
        }
        Materials findObj;
        for (int i = 0; i < materialsRep.size(); i++) {
            findObj = materialsRep.get(i);
            if (findObj.getId() == id) {
                return findObj;
            }
        }
        throw new EntityNotFoundException();
    }

    @Override
    public boolean delete(int id) throws EntityNotFoundException {
        if (id <= 0) {
            throw new EntityNotFoundException();
        }
        int indObj;
        for (int i = 0; i < materialsRep.size(); i++) {
            indObj = materialsRep.get(i).getId();
            if (indObj == id) {
                materialsRep.remove(i);
                return true;
            }
        }
        throw new EntityNotFoundException();
    }

    @Override
    public ArrayList<Materials> getAll() {
        return materialsRep;
    }

    public void printAll(){

        if (materialsRep == null||materialsRep.size() == 0){
            System.out.println("You haven't created anything yet");
        }
        Materials obj;
        for (int i = 0; i<materialsRep.size(); i++){
            obj = materialsRep.get(i);
            if(obj != null) {
                System.out.println("id = " +obj.getId() + " - "+obj);
            }
        }
    }
}
