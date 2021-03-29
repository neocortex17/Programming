package inputConsole;

import messageManager.IMessageMessenger;
import spaceMarine.SpaceMarine;
import outputConsole.IConsoleOutputManager;

import java.time.ZonedDateTime;
import java.util.Scanner;

/**
 * Implementation of the IConsoleInputManager interface for reading from the console
 */
public class ConsoleInputManager implements IConsoleInputManager{
    private Scanner scanner;
    private CommandReader commandReader;
    private ISpaceMarineReader spaceMarineReader;

    /**
     * Constructor for ConsoleInputManager
     * @param messageMessenger message messenger
     * @param outputManager console output manager
     */
    public ConsoleInputManager (IMessageMessenger messageMessenger, IConsoleOutputManager outputManager){
        scanner = new Scanner(System.in);
        commandReader = new CommandReader(scanner);
        spaceMarineReader = new SpaceMarineReader(scanner, messageMessenger, outputManager);
    }

    @Override
    public boolean ready() {
        return scanner.hasNext();
    }

    @Override
    public String readCommand() {
        return commandReader.readCommand();
    }

    @Override
    public SpaceMarine readSpaceMarine() throws NoSuchFieldException {
        return spaceMarineReader.readSpaceMarine();
    }

    @Override
    public SpaceMarine readSpaceMarine(Integer id, ZonedDateTime creationDate) throws NoSuchFieldException {
        return spaceMarineReader.readSpaceMarine(id,creationDate);
    }
}
