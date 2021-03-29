package command;

import exceptions.NoSuchCommandException;
import messageManager.IMessageMessenger;
import outputConsole.IConsoleOutputManager;

import java.util.Set;

/**
 * Command class that displays help for available commands
 */
public class Help implements Command {
    private Set<String> commands;
    private IMessageMessenger messageMessenger;
    private IConsoleOutputManager consoleOutputManager;

    /**
     * Constructor for Help
     * @param commands command list
     * @param messageMessenger message messenger
     * @param consoleOutputManager console output manager
     */
    public Help(Set<String> commands, IMessageMessenger messageMessenger, IConsoleOutputManager consoleOutputManager){
        this.commands = commands;
        this.messageMessenger = messageMessenger;
        this.consoleOutputManager = consoleOutputManager;
    }

    @Override
    public void execute() {
        for (String commandName : commands){
            try {
                consoleOutputManager.printMess(messageMessenger.getCommandMess(commandName) + "\n");
            }catch (NoSuchCommandException e){
                consoleOutputManager.printError(e.getMessage() + "\n");
            }
        }
    }
}
