package service.conversation;

import constants.ValidationType;
import exceptions.ValidationException;
import loger.Log;
import service.ValidationService;

import java.util.Scanner;

public class ConversationService {
    private final Scanner scanner;
    private final ValidationService validationService;

    public ConversationService(Scanner scanner, ValidationService validationService) {
        this.scanner = scanner;
        this.validationService = validationService;
    }

    public String getResponse(String request, ValidationType type) {
        System.out.println(request);
        String response = scanner.next();
        try {
            validationService.isCorrectResponse(response, type);
        } catch (ValidationException e) {
            Log.error(this.getClass().getName(), "method getResponse", e);
            return getResponse(request, type);
        }
        return response;
    }

    public void print(String text) {
        System.out.println(text);
    }

}
