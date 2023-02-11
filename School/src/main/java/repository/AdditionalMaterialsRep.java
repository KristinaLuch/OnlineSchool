package repository;

import exceptions.EntityNotFoundException;
import models.school_object.AdditionalMaterials;

import java.util.ArrayList;

public class AdditionalMaterialsRep implements IAdditionalMaterialsRep{

    private ArrayList<AdditionalMaterials> additionalMaterialsList;

    public AdditionalMaterialsRep(ArrayList<AdditionalMaterials> additionalMaterialsList) {
        this.additionalMaterialsList = additionalMaterialsList;
    }

    @Override
    public boolean add(AdditionalMaterials additionalMaterials) {
        if (additionalMaterials == null) {
            return false;
        }
        additionalMaterialsList.add(additionalMaterials);
        return true;
    }

    @Override
    public boolean update(int id, AdditionalMaterials newAdditionalMaterials) throws EntityNotFoundException {
        if (id <= 0) {
            throw new EntityNotFoundException();
        }
        AdditionalMaterials findAdditionalMaterials;
        int index;
        for (int i = 0; i < additionalMaterialsList.size(); i++) {
            findAdditionalMaterials = additionalMaterialsList.get(i);
            if (findAdditionalMaterials.getId() == id) {
                index = i;
                additionalMaterialsList.add(index, newAdditionalMaterials);
                return true;
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
        for (int i = 0; i < additionalMaterialsList.size(); i++) {
            indObj = additionalMaterialsList.get(i).getId();
            if (indObj == id) {
                additionalMaterialsList.remove(i);
                return true;
            }
        }
        throw new EntityNotFoundException();
    }

    @Override
    public AdditionalMaterials get(int id) throws EntityNotFoundException {
        if (id <= 0) {
            throw new EntityNotFoundException();
        }
        AdditionalMaterials findObj;
        for (int i = 0; i < additionalMaterialsList.size(); i++) {
            findObj = additionalMaterialsList.get(i);
            if (findObj.getId() == id) {
                return findObj;
            }
        }
        throw new EntityNotFoundException();
    }

    @Override
    public ArrayList<AdditionalMaterials> getAll() {
        return additionalMaterialsList;
    }

    public void printAll(){

        if (additionalMaterialsList == null||additionalMaterialsList.size() == 0){
            System.out.println("You haven't created anything yet");
        }
        AdditionalMaterials obj;
        for (int i = 0; i<additionalMaterialsList.size(); i++){
            obj = additionalMaterialsList.get(i);
            if(obj != null) {
                System.out.println("id = " +obj.getId() + " - "+obj);
            }
        }
    }
}
