package fileManager;

import exceptions.*;
import spaceMarine.SpaceMarine;

import java.util.Collection;

/**
 * Data reader class interface
 */
public interface Reader {
    /**
     * The method that reads the collection
     * @return collection
     * @throws InvalidFieldException if an invalid object field is specified
     * @throws NoEvnVarException if the required environment variable is missing
     * @throws NoDataException if there is no collection data
     * @throws BrokenDataException if an error occurs in the data structure
     * @throws InvalidArgumentTypeException an invalid data type is specified for an argument
     */
    Collection<? extends SpaceMarine> read() throws InvalidFieldException, NoEvnVarException, NoDataException, BrokenDataException, InvalidArgumentTypeException;

}
