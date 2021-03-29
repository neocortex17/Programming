package exceptions;

/**
 * An exception class that is thrown if there is a conflict of id of objects in the collection
 */
public class NotUniqueIdException extends InvalidFieldException{
    public NotUniqueIdException(String mess) {
        super(mess);
    }
}
