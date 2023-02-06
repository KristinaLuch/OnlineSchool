package service.conversation;

import constants.ValidationType;
import exceptions.ValidationException;
import service.ValidationService;

import java.util.Scanner;

public class ConversationService {
    private Scanner scanner;
    private ValidationService validationService;

    public ConversationService(Scanner scanner, ValidationService validationService) {
        this.scanner = scanner;
        this.validationService = validationService;
    }

    public String getResponse(String request, ValidationType type){
        System.out.println(request);
        String response = scanner.next();
        try {
            validationService.isCorrectResponse(response, type);
        }catch (ValidationException e){
            e.printStackTrace();
            return getResponse(request, type);
        }
        return response;
    }
    public void print(String text){
        System.out.println(text);
    }



}
