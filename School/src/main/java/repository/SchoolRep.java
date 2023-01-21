package repository;


import models.SchoolObject;


public class SchoolRep{
    protected Rep<SchoolObject> schoolObjects;

    public SchoolRep(Rep<SchoolObject> schoolObjects) {
        this.schoolObjects = schoolObjects;
    }

    public SchoolObject[] getAll() {
        return schoolObjects.toArray();
    }

    public boolean add(SchoolObject schoolObject) {
        if (schoolObject == null) {
            return false;
        }
        schoolObjects.add(schoolObject);
        return true;
    }

    public SchoolObject getById(int id) {
        if (id <= 0) {
            return null;
        }
        SchoolObject findObj;
        for (int i = 0; i < schoolObjects.size(); i++) {
             findObj = schoolObjects.get(i);
            if (findObj.getId() == id) {
                return findObj;
            }
        }
        return null;
    }

    public boolean deleteById(int id) {
        if (id <= 0) {
            return false;
        }
        int indObj;
        for (int i = 0; i < schoolObjects.size(); i++) {
            indObj = schoolObjects.get(i).getId();
            if (indObj == id) {
                schoolObjects.remove(i);
                return true;
            }
        }
        return false;
}

    public void printAll(){
        
        if (schoolObjects == null||schoolObjects.size() == 0){
            System.out.println("You haven't created anything yet");
        }
        SchoolObject obj;
        for (int i = 0; i<schoolObjects.size(); i++){
            obj = schoolObjects.get(i);
            if(obj != null) {
                System.out.println("id = " +obj.getId() + " - "+obj);
            }
        }
    }

}
