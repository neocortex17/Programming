package serverCommands;

import exceptions.NoSuchCommandException;

/**
 * Command manager interface
 */

public interface ICommandManager {

    /**
     * Method that runs the command
     * @param command command name
     * @param arg command argument
     * @throws NoSuchCommandException if no such command exists
     * @throws NoSuchFieldException if no such field exists
     */
    void executeCommand(String command, String arg) throws NoSuchCommandException, NoSuchFieldException;
    /**
     * Method that adds a script to the list of used
     * @param scriptName script name
     */
    void scriptAddHistory(String scriptName);
    /**
     * @param scriptName script name
     * @return true if used, false if not
     */
    boolean scriptIsUsed (String scriptName);

    /**
     * Method that clears the list of used scripts
     */
    void clearScript();

    /**
     * Method that removes a script from the list of used
     * @param scriptName script name
     */
    void scriptRemove(String scriptName);
}
