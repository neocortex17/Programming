package command;

import exceptions.InvalidArgumentTypeException;
import exceptions.NoArgumentException;
import inputConsole.IConsoleInputManager;
import managers.ISpaceMarineManager;
import messageManager.IMessageMessenger;
import outputConsole.IConsoleOutputManager;
import spaceMarine.Weapon;

/**
 * The class that implements the command to print the number of elements, the value of the weaponType field of which is equal to the specified
 * @author neocortex
 */
public class CountByWeaponType implements Command, RequiringArg<String> {
    private ISpaceMarineManager spaceMarineManager;
    private IConsoleOutputManager consoleOutputManager;
    private IMessageMessenger messageMessenger;
    private String arg;

    /**
     * Constructor for CountByWeaponType
     * @param spaceMarineManager collection manager
     * @param messageMessenger message messenger
     * @param consoleOutputManager console output manager
     */
    public CountByWeaponType ( ISpaceMarineManager spaceMarineManager, IMessageMessenger messageMessenger, IConsoleOutputManager consoleOutputManager){
        this.spaceMarineManager = spaceMarineManager;
        this.messageMessenger = messageMessenger;
        this.consoleOutputManager = consoleOutputManager;
    }

    @Override
    public void execute() {
        try {
            Weapon weapon = Weapon.valueOf(arg.trim().toUpperCase());
            consoleOutputManager.printMess(String.valueOf(spaceMarineManager.countByWeaponType(weapon)));
        }catch (IllegalArgumentException e){
            consoleOutputManager.printError(messageMessenger.getExceptionMess("noEnum") + "\n");
        }
    }

    @Override
    public void setArg(String arg) {
        this.arg = arg;
    }

    @Override
    public void acceptInvoker(InvokerInterface invokerInterface) throws NoArgumentException, InvalidArgumentTypeException, NoSuchFieldException {
        invokerInterface.setStringArg(this);
        invokerInterface.invokeCommand(this);
    }
}
