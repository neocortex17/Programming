package exceptions;

/**
 * An exception class that is thrown when an invalid data type is specified for an argument
 */
public class InvalidArgumentTypeException extends Exception{

    public InvalidArgumentTypeException(String mess){
        super(mess);
    }
}
