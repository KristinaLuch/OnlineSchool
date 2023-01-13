package service.school;

import models.SchoolObject;
import repository.SchoolRep;

public abstract class SchoolService {

    public SchoolService(SchoolRep schoolRep) {
        this.schoolRep = schoolRep;
    }

    protected SchoolRep schoolRep;

    public void printAll(){
        SchoolObject[] entitiesToPrint = schoolRep.getAll();
        if (entitiesToPrint == null||entitiesToPrint.length == 0){
            System.out.println("You haven't created anything yet");
        }
        for (int i = 0; i<entitiesToPrint.length; i++){
            if(entitiesToPrint[i] != null) {
                System.out.println("id = " +entitiesToPrint[i].getId() + " - "+entitiesToPrint[i]);
            }
        }
    }

    public void addToRep(SchoolObject schoolObject){
        schoolRep.add(schoolObject);
    }
}
