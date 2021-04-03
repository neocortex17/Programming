package command;

import managers.ISpaceMarineManager;
import messageManager.IMessageMessenger;
import outputConsole.IConsoleOutputManager;

/**
 * The class that implements the command to output to the standard output stream all the elements of the collection in string representation
 */
public class Show implements Command{
    private ISpaceMarineManager spaceMarineManager;
    private IMessageMessenger messageMessenger;
    private IConsoleOutputManager consoleOutputManager;

    /**
     * Constructor for Show
     * @param spaceMarineManager collection manager
     * @param messageMessenger message messenger
     * @param consoleOutputManager console output manager
     */
    public Show (ISpaceMarineManager spaceMarineManager, IMessageMessenger messageMessenger, IConsoleOutputManager consoleOutputManager){
        this.spaceMarineManager = spaceMarineManager;
        this.messageMessenger = messageMessenger;
        this.consoleOutputManager = consoleOutputManager;
    }

    @Override
    public void execute() {
        spaceMarineManager.getSpaceMarineStream()
                .forEachOrdered(spaceMarine -> consoleOutputManager.printMess(messageMessenger.getSpaceMarineString(spaceMarine) + "\n"));
    }
}
