package managers;

import outputConsole.IConsoleOutputManager;

public interface ISpaceMarineIdManager {

    /**
      * Method that checks if id is free
      * @param id id value
      * @return true if free, false if busy
      */
    boolean idIsFree(Integer id);

    /**
      * @return first free identifier
      */
    Integer getFreeId();

    /**
     * Method that adds identifier to used
     * @param id id value
     */
    void addId(Integer id);

    /**
     * Method that removes identifier from used
     * @param id id value
     */
    void removeId(Integer id);

    /**
     * Method that removes all used identifiers
     */
    void clearAllOccupiedId();

    /**
     * A method that groups id by modulo 3
     * @param consoleOutputManager console output manager
     */
    void groupByIdModulo3(IConsoleOutputManager consoleOutputManager);
}
