package repository;

import entity.Materials;

public class MaterialsRep {

    private Materials[] materialsArray;

    public MaterialsRep() {
        this.materialsArray = new Materials[10];
    }

    public boolean add(Materials materials) {
        if (materials == null) {
            return false;
        }
        for (int i = 0; i < materialsArray.length; i++) {
            if (materialsArray[i] == null) {
                materialsArray[i] = materials;
                if (i == materialsArray.length - 1) {
                    increase();
                }
                break;
            }
        }
        return true;
    }

    private void increase() {
        int newLength = (materialsArray.length * 3) / 2 + 1;
        Materials[] tmp = new Materials[newLength];
        for (int i = 0; i < materialsArray.length; i++) {
            tmp[i] = materialsArray[i];
        }
        materialsArray = tmp;
    }

    public Materials get(int id) {
        if (id < 0) {
            return null;
        }
        if (id == materialsArray[id].getId()) {
            return materialsArray[id];
        } //if id == i
        for (int i = 0; i < materialsArray.length; i++) {
            if (materialsArray[i].getId() == id) {
                return materialsArray[i];
            }
        }
        return null;
    }

    public Materials[] getAll() {
        return materialsArray;
    }

}
