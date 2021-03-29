package managers;

import exceptions.*;
import messageManager.IMessageMessenger;
import spaceMarine.SpaceMarine;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

/**
 * Data reader class interface
 */
public interface Reader {
    /**
     * The method that reads the collection
     * @param messageMessenger message messenger
     * @return collection
     * @throws InvalidFieldException if an invalid object field is specified
     * @throws NoEvnVarException if the required environment variable is missing
     * @throws NoDataException if there is no collection data
     * @throws BrokenDataException if an error occurs in the data structure
     * @throws InvalidArgumentTypeException an invalid data type is specified for an argument
     */
    Collection<? extends SpaceMarine> read(IMessageMessenger messageMessenger) throws InvalidFieldException, NoEvnVarException, NoDataException, BrokenDataException, InvalidArgumentTypeException;

}
