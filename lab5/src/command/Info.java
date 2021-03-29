package command;

import managers.ISpaceMarineManager;
import messageManager.IMessageMessenger;
import outputConsole.IConsoleOutputManager;

/**
 * The class that implements the command to print information about the collection (type, initialization date, number of elements, etc.) to the standard output stream
 */
public class Info implements Command {
    private ISpaceMarineManager spaceMarineManager;
    private IMessageMessenger messageMessenger;
    private IConsoleOutputManager consoleOutputManager;

    /**
     * Constructor for Info
     * @param spaceMarineManager collection manager
     * @param messageMessenger message messenger
     * @param consoleOutputManager console output manager
     */
    public Info(ISpaceMarineManager spaceMarineManager, IMessageMessenger messageMessenger, IConsoleOutputManager consoleOutputManager){
        this.spaceMarineManager = spaceMarineManager;
        this.consoleOutputManager = consoleOutputManager;
        this.messageMessenger = messageMessenger;
    }

    @Override
    public void execute() {
        consoleOutputManager.printMess(messageMessenger.getCollectionTypeMess() + ": " + spaceMarineManager.getType().getSimpleName() +
        "\n" + messageMessenger.getCollectionDateMess() + ": " + spaceMarineManager.getInitDate() + "\n" +
        messageMessenger.getCollectionSizeMess() + ": " + spaceMarineManager.getLength() + "\n");
    }
}
