package command;

import exceptions.InvalidArgumentTypeException;
import inputConsole.IConsoleInputManager;
import messageManager.IMessageMessenger;
import outputConsole.IConsoleOutputManager;
import spaceMarine.*;
import managers.ISpaceMarineManager;
import managers.ValidateField;

import java.util.Scanner;

/**
 * The class that implements the command to remove all elements from the collection that exceed the specified one
 */
public class RemoveGreater implements Command {
    private ISpaceMarineManager spaceMarineManager;
    private IMessageMessenger messageMessenger;
    private IConsoleInputManager consoleInputManager;
    private IConsoleOutputManager consoleOutputManager;

    /**
     *Constructor for RemoveGreater
     * @param spaceMarineManager collection manager
     * @param consoleInputManager console input manager
     * @param consoleOutputManager console output manager
     * @param messageMessenger message messenger
     */
     public RemoveGreater (ISpaceMarineManager spaceMarineManager, IMessageMessenger messageMessenger, IConsoleOutputManager consoleOutputManager, IConsoleInputManager consoleInputManager){
        this.spaceMarineManager = spaceMarineManager;
        this.messageMessenger = messageMessenger;
        this.consoleInputManager = consoleInputManager;
        this.consoleOutputManager = consoleOutputManager;
    }

    @Override
    public void execute() throws NoSuchFieldException {
        SpaceMarine spaceMarine = consoleInputManager.readSpaceMarine();
        int point = 0;
        do {
            if (spaceMarine.compareTo(spaceMarineManager.getSpaceMarineStream().max(SpaceMarine::compareTo).get()) < 0) {
                spaceMarineManager.removeById(spaceMarineManager.getSpaceMarineStream().max(SpaceMarine::compareTo).get().getId());
                point = 1;
            } else {
                point = 0;
            }
        } while (point == 1);
    }
}

