package command;

import exceptions.InvalidArgumentTypeException;
import exceptions.NoArgumentException;
import managers.ISpaceMarineManager;
import messageManager.IMessageMessenger;
import outputConsole.IConsoleOutputManager;

/**
 * The class that implements the command to remove an item from a collection by its id
 */
public class RemoveById implements Command, RequiringArg<Integer> {
    private ISpaceMarineManager spaceMarineManager;
    private IMessageMessenger messageMessenger;
    private IConsoleOutputManager consoleOutputManager;
    private Integer arg;

    /**
     * Constructor for RemoveById
     * @param spaceMarineManager collection manager
     * @param messageMessenger message messenger
     * @param consoleOutputManager console output manager
     */
    public RemoveById (ISpaceMarineManager spaceMarineManager, IMessageMessenger messageMessenger, IConsoleOutputManager consoleOutputManager){
        this.spaceMarineManager = spaceMarineManager;
        this.consoleOutputManager = consoleOutputManager;
        this.messageMessenger = messageMessenger;
    }

    @Override
    public void execute() {
        if (!spaceMarineManager.removeById(arg)){
            consoleOutputManager.printError(messageMessenger.getExceptionMess("noSuchId") + "\n");
        }
    }

    @Override
    public void setArg(Integer arg) {
        this.arg = arg;
    }

    @Override
    public void acceptInvoker(InvokerInterface invokerInterface) throws NoArgumentException, InvalidArgumentTypeException, NoSuchFieldException {
        invokerInterface.setIntegerArg(this);
        invokerInterface.invokeCommand(this);
    }
}
