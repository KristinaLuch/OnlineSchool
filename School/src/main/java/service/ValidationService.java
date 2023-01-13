package service;
import constants.ValidationType;
import java.util.regex.Pattern;
public class ValidationService {

    public static final String REGEX_CORRECT_EMAIL = "^[a-zA-Z0-9+_\\.-]{1,32}+@[a-zA-Z0-9-]{1,32}\\.[a-zA-Z0-9-]{2,6}+$";
    public static final String REGEX_CORRECT_PHONE_NUMBER = "\\+[1-9]\\d{8,14}";
    public static final String REGEX_CORRECT_NAME= "[A-Za-z]{2,20}";
    public static final int DESCRIPTION_MAX_SYMBOLS = 80;

    public boolean isCorrectResponse(String response, ValidationType type){
        boolean isCorrect;
        switch (type){
            case NAME -> {
                isCorrect = isCorrectName(response);
                break;
            }
            case EMAIL -> {
                isCorrect = isCorrectEmail(response);
                break;
            }
            case PHONE -> {
                isCorrect = isCorrectPhone(response);
                break;
            }
            case DESCRIPTION -> {
                isCorrect = isCorrectDescription(response);
                break;
            }
            case DIGIT -> {
                isCorrect = isDigitPositiveNotZero(response);
                break;
            }
            case ANYTHING -> {
                if (response.isBlank()){
                    return false;
                }
                isCorrect = true;
            }
            default -> {
                System.out.println("Incorrect type");
                return false;
            }
        }
        return isCorrect;
    }

    public boolean isCorrectName(String name){
        return isCorrect(name, REGEX_CORRECT_NAME);
    }

    public boolean isCorrectDescription(String description){
        return description.length() <= DESCRIPTION_MAX_SYMBOLS;
    }

    public boolean isCorrectEmail(String email){
        return isCorrect(email, REGEX_CORRECT_EMAIL);
    }

    public boolean isCorrectPhone(String phone){
        return isCorrect(phone, REGEX_CORRECT_PHONE_NUMBER);
    }

    public boolean isDigitPositiveNotZero(String digit){
        if (digit.length() == 0){
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
        if (checkField.isBlank()){
            return false;
        }
        return Pattern.compile(regex).matcher(checkField).matches();
    }

}
