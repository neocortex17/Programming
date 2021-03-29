package outputConsole;

/**
 * Implementing the IConsoleOutputManager interface for console output
 */
public class ConsoleOutputManager implements IConsoleOutputManager{

    @Override
    public void printMess(String mess) {
        System.out.println(mess);
    }

    @Override
    public void printError(String mess) {
        System.err.println(mess);
    }
}
