package inputConsole;

import spaceMarine.SpaceMarine;

import java.time.ZonedDateTime;

/**
 * Collection element reader class interface
 * @author neocortex
 */
public interface ISpaceMarineReader {
    /**
     * Method that reads the collection item
     * @return collection item
     */
    SpaceMarine readSpaceMarine() throws NoSuchFieldException;

    /**
    * Method that reads a collection item to update an existing item
    * @param id id of an existing element
    * @param creationDate creation date of an existing item
    * @return updated collection item
    */
    SpaceMarine readSpaceMarine(Integer id, ZonedDateTime creationDate) throws NoSuchFieldException;
}
