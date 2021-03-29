package exceptions;

/**
 * An exception class that is thrown if the command has no argument
 */
public class NoArgumentException extends Exception{
    public NoArgumentException(String mess){
        super(mess);
    }
}
