package exceptions;

/**
 * An exception class that is thrown if an error occurs while processing data
 */
public class DataException extends Exception{
    public DataException(String mess){
        super(mess);
    }
}
