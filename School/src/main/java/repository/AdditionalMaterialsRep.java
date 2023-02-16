package repository;

import exceptions.EntityNotFoundException;
import models.school_object.AdditionalMaterials;
import models.school_object.Homework;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class AdditionalMaterialsRep implements IAdditionalMaterialsRep{

    private Map<Integer, ArrayList<AdditionalMaterials>> additionalMaterialsMap;

    public AdditionalMaterialsRep(Map<Integer, ArrayList<AdditionalMaterials>> additionalMaterialsMap) {
        this.additionalMaterialsMap = additionalMaterialsMap;
    }

    @Override
    public boolean add(AdditionalMaterials additionalMaterials) {
        if (additionalMaterials == null) {
            return false;
        }
        int key = additionalMaterials.getLectureId();
        ArrayList<AdditionalMaterials> value = additionalMaterialsMap.get(key);

        if (value == null){
            value = new ArrayList<>();
        }
        value.add(additionalMaterials);
        additionalMaterialsMap.put(key, value);
        return true;
    }

    @Override
    public boolean update(int id, AdditionalMaterials newAdditionalMaterials) throws EntityNotFoundException {
        if (id <= 0) {
            throw new EntityNotFoundException();
        }
        if (id <= 0) {
            throw new EntityNotFoundException();
        }
        AdditionalMaterials oldAdditionalMaterials = get(id);
        int key = oldAdditionalMaterials.getLectureId();
        ArrayList<AdditionalMaterials> list = additionalMaterialsMap.get(key);
        int index = list.indexOf(oldAdditionalMaterials);
        list.add(index, newAdditionalMaterials);
        additionalMaterialsMap.put(key, list);
        return true;
    }

    @Override
    public boolean delete(int id) throws EntityNotFoundException {
        if (id <= 0) {
            throw new EntityNotFoundException();
        }

        AdditionalMaterials findAddMat = get(id);
        ArrayList<AdditionalMaterials> findAlist = additionalMaterialsMap.get(findAddMat.getLectureId());
        findAlist.remove(findAddMat);
        additionalMaterialsMap.put(findAddMat.getLectureId(), findAlist);
        return true;
    }

    @Override
    public AdditionalMaterials get(int id) throws EntityNotFoundException {
        if (id <= 0) {
            throw new EntityNotFoundException();
        }
        ArrayList<AdditionalMaterials> findAlist;
        AdditionalMaterials findAddMat;
        int findId;
        Set<Integer> keySet = additionalMaterialsMap.keySet();

        for(int key:keySet){
            findAlist = additionalMaterialsMap.get(key);
            for (int ind = 0; ind <findAlist.size(); ind++){
                findAddMat = findAlist.get(ind);
                findId = findAddMat.getId();
                if (findId == id) {
                    return findAddMat;
                }
            }
        }
        throw new EntityNotFoundException();
    }

    @Override
    public Map<Integer, ArrayList<AdditionalMaterials>> getAll() {
        return additionalMaterialsMap;
    }

    @Override
    public ArrayList<AdditionalMaterials> getAdditionalMaterials(int lectureId) {
        return additionalMaterialsMap.get(lectureId);
    }

    public boolean belongsToLecture(int lectureId, int id){
        if (lectureId <= 0 || id <=0){
            return false;
        }
        ArrayList<AdditionalMaterials> alist = additionalMaterialsMap.get(lectureId);
        if(alist == null){
            return false;
        }
        for (int i = 0; i < alist.size(); i++) {
            if (alist.get(i).getId() == id){
                return true;
            }
        }
        return false;
    }

}
