package service.conversation;

import constants.ValidationType;
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
        String response = scanner.nextLine();
        if (validationService.isCorrectResponse(response, type)){
            return response;
        }
        System.out.println(type.errorMessage);
        return getResponse(request, type);
    }
    public void print(String text){
        System.out.println(text);
    }



}
