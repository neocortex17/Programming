package exceptions;

/**
 * An exception class that is thrown if there is no collection data
 */
public class NoDataException extends DataException{
    public NoDataException(String mess){
        super(mess);
    }
}
