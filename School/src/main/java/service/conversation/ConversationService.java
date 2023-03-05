package service.conversation;

import constants.ValidationType;
import exceptions.ValidationException;
import loger.Log;
import repository.log.LogRepository;
import service.ValidationService;

import java.util.Scanner;

public class ConversationService {
    private final Scanner scanner;
    private final ValidationService validationService;

    private LogRepository logRepository;

    public ConversationService(Scanner scanner, ValidationService validationService, LogRepository logRepository) {
        this.scanner = scanner;
        this.validationService = validationService;
        this.logRepository = logRepository;
    }

    public String getResponse(String request, ValidationType type) {
        Log.warning("nn", "getResponse ConvServ");
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
