package service.school;

import models.SchoolEntity;
import models.Student;
import repository.SchoolRep;

public abstract class SchoolService {

    protected SchoolRep schoolRep;

    public void printAll(){
        SchoolEntity[] entitiesToPrint = schoolRep.getAll();
        if (entitiesToPrint == null||entitiesToPrint.length == 0){
            System.out.println("You haven't created anything yet");
        }
        for (int i = 0; i<entitiesToPrint.length; i++){
            if(entitiesToPrint[i] != null) {
                System.out.println("id = " +entitiesToPrint[i].getId() + " - "+entitiesToPrint[i]);
            }
        }
    }

    public void addToRep(SchoolEntity schoolEntity){
        schoolRep.add(schoolEntity);
    }
}
