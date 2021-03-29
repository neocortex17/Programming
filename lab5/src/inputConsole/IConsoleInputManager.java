package inputConsole;

import spaceMarine.SpaceMarine;

import java.time.ZonedDateTime;

/**
 * Input manager interface
 * @author neocortex
 */
public interface IConsoleInputManager {
    /**
     * @return true - ready to read, false - not ready
     */
    boolean ready();

    /**
     * Method that reads the command
     * @return command line
     */
    String readCommand();

    /**
     * Method that reads an item from the collection
     * @return collection item
     * @throws NoSuchFieldException if the object field is not found
     */
    SpaceMarine readSpaceMarine() throws NoSuchFieldException;

    /**
     * A method that reads a collection item to update an existing item
     * @param id id of an existing element
     * @param creationDate creation date of an existing item
     * @return updated collection item
     */
    SpaceMarine readSpaceMarine(Integer id, ZonedDateTime creationDate) throws NoSuchFieldException;
}
