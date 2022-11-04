package service;

import entity.Homework;
import entity.Materials;
import repository.MaterialsRep;

import java.util.ArrayList;
import java.util.Scanner;

public class MaterialService {

    private static final String PRINT_MATERIALS= "Print materials";

    public MaterialsRep materialsRep;

    private Scanner scanner;

    public MaterialService(MaterialsRep materialsRep, Scanner scanner) {
        this.materialsRep = materialsRep;
        this.scanner = scanner;
    }

    public Materials crete(){

        System.out.println(PRINT_MATERIALS);

        String materialsString = scanner.next();

        Materials materials = new Materials(materialsString);

        materialsRep.add(materials);

        return materials;
    }
}
