package exceptions;

public class EntityNotFoundException extends Exception{

    public EntityNotFoundException() {
        super("Entity not found");
    }

}
