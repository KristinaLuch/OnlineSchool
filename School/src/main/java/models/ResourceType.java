package models;

public enum ResourceType {

    BOOK("book"),

    VIDEO("video"),

    URL("url");

    public final String typeMessage;

    ResourceType(String typeMessage) {
        this.typeMessage = typeMessage;
    }
}
