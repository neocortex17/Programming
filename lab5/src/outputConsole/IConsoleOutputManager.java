package outputConsole;

/**
 * Output manager interface
 */
public interface IConsoleOutputManager {

    /**
     * Display message
     * @param mess
     */
    void printMess(String mess);

    /**
     * Display error
     * @param mess
     */
    void printError(String mess);
}
