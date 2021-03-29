package exceptions;

/**
 * An exception class that is thrown if the command is missing
 */
public class NoSuchCommandException extends Exception{
    public NoSuchCommandException(String mess){
        super(mess);
    }
}
