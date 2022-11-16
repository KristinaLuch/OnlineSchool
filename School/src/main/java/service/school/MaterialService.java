package service.school;

import models.Materials;
import repository.MaterialsRep;

import java.util.Scanner;

public class MaterialService extends SchoolService {

    private static final String PRINT_MATERIALS= "Print materials";

    private Scanner scanner;

    public MaterialService(MaterialsRep schoolRep, Scanner scanner) {
        this.schoolRep = schoolRep;
        this.scanner = scanner;
    }

    public Materials crete(){

        System.out.println(PRINT_MATERIALS);

        String materialsString = scanner.next();

        Materials materials = new Materials(materialsString);

        schoolRep.add(materials);

        return materials;
    }
}
