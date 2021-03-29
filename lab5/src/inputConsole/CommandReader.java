package inputConsole;

import java.util.Scanner;

/**
 * Implementation of the ICommandReader interface for reading from the console
 */
public class CommandReader implements ICommandReader{
    private Scanner scanner;

    /**
     * Constructor for CommandReader
     * @param scanner object of Scanner
     */
    public CommandReader(Scanner scanner){
        this.scanner = scanner;
    }

    @Override
    public String readCommand() {
        String input = scanner.nextLine();
        return input.trim().toLowerCase();
    }
}
