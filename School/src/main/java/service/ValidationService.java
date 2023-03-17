package service;

import constants.ValidationType;
import exceptions.ValidationException;
import loger.Log;
import repository.school.impl.PersonRep;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class ValidationService {

    private final PersonRep personRep;

    public ValidationService(PersonRep personRep) {
        this.personRep = personRep;
    }

    public static final String REGEX_CORRECT_EMAIL = "^[a-zA-Z0-9+_\\.-]{1,32}+@[a-zA-Z0-9-]{1,32}\\.[a-zA-Z0-9-]{2,6}+$";
    public static final String REGEX_CORRECT_PHONE_NUMBER = "\\+[1-9]\\d{8,14}";
    public static final String REGEX_CORRECT_NAME = "[A-Za-z]{2,20}";
    public static final int DESCRIPTION_MAX_SYMBOLS = 80;

    public static final String LECTURE_DATE_FORMAT = "dd.MM.yyyy HH:mm:ss";

    public boolean isCorrectResponse(String response, ValidationType type) throws ValidationException {
        boolean isCorrect;

        switch (type) {
            case NAME -> {
                isCorrect = isCorrectName(response);
            }
            case EMAIL -> {
                isCorrect = isCorrectEmail(response);
            }
            case PHONE -> {
                isCorrect = isCorrectPhone(response);
            }
            case DESCRIPTION -> {
                isCorrect = isCorrectDescription(response);
            }
            case DIGIT -> {
                isCorrect = isDigitPositiveNotZero(response);
            }
            case LECTURE_DATE -> {
                isCorrect = isLectureDate(response);
            }
            case ANYTHING -> {
                if (response.isBlank()) {
                    return false;
                }
                isCorrect = true;
            }
            default -> {
                Log.warning(this.getClass().getName(), "Incorrect type");
                return false;
            }
        }
        if (!isCorrect)
            throw new ValidationException(type.errorMessage);
        return true;
    }

    public boolean isLectureDate(String dateString){
        try {
           LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern(LECTURE_DATE_FORMAT));
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean isCorrectName(String name) {
        return isCorrect(name, REGEX_CORRECT_NAME);
    }

    public boolean isCorrectDescription(String description) {
        return description.length() <= DESCRIPTION_MAX_SYMBOLS;
    }

    public boolean isCorrectEmail(String email) {
        if(isCorrect(email, REGEX_CORRECT_EMAIL)){
            return !personRep.isDuplicate(email);
        }
        return false;
    }

    public boolean isCorrectPhone(String phone) {
        return isCorrect(phone, REGEX_CORRECT_PHONE_NUMBER);
    }

    public boolean isDigitPositiveNotZero(String digit) {
        if (digit.length() == 0) {
            return false;
        }
        try {
            Integer.parseInt(digit);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isCorrect(String checkField, String regex) {
        if (checkField.isBlank()) {
            return false;
        }
        return Pattern.compile(regex).matcher(checkField).matches();
    }

}
