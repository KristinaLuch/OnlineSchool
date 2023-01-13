package constants;

public enum ValidationType {
    NAME("Incorrect data. Enter Latin letters (0 - 20 symbols)"),
    EMAIL("It is not email"),
    PHONE("Incorrect phone. Please, print your phone number with \"+\" and country code"),
    DESCRIPTION("Incorrect data. Maximum number of symbols - 80"),
    DIGIT("It's not a number"),
    ANYTHING("");

    public final String errorMessage;

    ValidationType(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
