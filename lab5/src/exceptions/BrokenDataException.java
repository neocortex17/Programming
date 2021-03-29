package exceptions;

/**
 * An exception class that is thrown if an error occurs in the data structure
 */
public class BrokenDataException extends DataException{
    public BrokenDataException(String mess){
        super(mess);
    }
}
