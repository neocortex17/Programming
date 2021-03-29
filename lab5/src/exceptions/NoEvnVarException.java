package exceptions;

/**
 * An exception class that is thrown if the required environment variable is missing
 */
public class NoEvnVarException extends DataException{
    public NoEvnVarException(String mess){
        super(mess);
    }
}
