package managers;


import exceptions.*;
import spaceMarine.SpaceMarine;

import java.util.Collection;

/**
 * The interface of the class responsible for working with files
 */
public interface IFileManager {
    /**
     * The method that reads the collection
     * @return collection
     * @throws NoEvnVarException if the required environment variable is missing
     * @throws InvalidFieldException if an invalid object field is specified
     * @throws NoDataException if there is no collection data
     * @throws BrokenDataException if an error occurs in the data structure
     * @throws InvalidArgumentTypeException an invalid data type is specified for an argument
     */
    Collection<? extends SpaceMarine> read() throws NoEvnVarException, InvalidFieldException, NoDataException, BrokenDataException, InvalidArgumentTypeException;

    /**
     * The method that writes the collection to a file
     * @param collection collection
     */
    void  write(Collection<? extends SpaceMarine> collection);

}
