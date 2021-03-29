package exceptions;

/**
 * An exception class that is thrown if an invalid object field is specified
 */
public class InvalidFieldException extends Exception{
    public InvalidFieldException (String mess){
        super(mess);
    }
}
