package command;

import exceptions.InvalidArgumentTypeException;
import exceptions.NoArgumentException;
import inputConsole.IConsoleInputManager;
import managers.ISpaceMarineManager;
import managers.SpaceMarineIdManager;
import messageManager.IMessageMessenger;
import outputConsole.IConsoleOutputManager;
import spaceMarine.SpaceMarine;

/**
 * The class that implements the command to update the value of the collection element whose id is equal to the given one
 */
public class UpdateId implements Command, RequiringArg<Integer>{
    private ISpaceMarineManager spaceMarineManager;
    private IMessageMessenger messageMessenger;
    private IConsoleInputManager consoleInputManager;
    private IConsoleOutputManager consoleOutputManager;
    private Integer arg;

    /**
     * Constructor for UpdateId
     * @param spaceMarineManager collection manager
     * @param messageMessenger message messenger
     * @param consoleOutputManager console output manager
     * @param consoleInputManager input manager
     */
    public UpdateId (ISpaceMarineManager spaceMarineManager, IMessageMessenger messageMessenger, IConsoleOutputManager consoleOutputManager, IConsoleInputManager consoleInputManager){
        this.spaceMarineManager = spaceMarineManager;
        this.messageMessenger = messageMessenger;
        this.consoleInputManager = consoleInputManager;
        this.consoleOutputManager = consoleOutputManager;
    }

    @Override
    public void execute() throws NoSuchFieldException {
        if (!SpaceMarineIdManager.getInstance().idIsFree(arg)){
            SpaceMarineIdManager.getInstance().removeId(arg);
            SpaceMarine oldSpaceMarine = spaceMarineManager.getSpaceMarineStream().filter(spaceMarine -> spaceMarine.getId() == arg).findAny().get();
            SpaceMarine spaceMarine = consoleInputManager.readSpaceMarine(oldSpaceMarine.getId(), oldSpaceMarine.getCreationDate());
            spaceMarineManager.removeById(arg);
            spaceMarineManager.addSpaceMarine(spaceMarine);
        }else {
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
