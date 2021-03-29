package exceptions;

/**
 * An exception class that is thrown if the object field is not found
 */
public class NoSuchFieldException extends Exception{
    public NoSuchFieldException (String mess){
        super(mess);
    }
}
