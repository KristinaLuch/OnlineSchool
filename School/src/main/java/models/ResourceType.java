package models;

public enum ResourceType {

    URL("url"),

    VIDEO("video"),

    BOOK("book");

    public final String typeMessage;

    ResourceType(String typeMessage) {
        this.typeMessage = typeMessage;
    }
}
