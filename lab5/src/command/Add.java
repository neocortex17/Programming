package command;

import inputConsole.IConsoleInputManager;
import managers.ISpaceMarineManager;

/**
 * The class of the command that adds a new item to the collection
 */
public class Add implements Command {
    private ISpaceMarineManager spaceMarineManager;
    private IConsoleInputManager consoleInputManager;

    /**
     * Constructor for Add
     * @param spaceMarineManager collection manager
     * @param consoleInputManager console input manager
     */
    public Add(ISpaceMarineManager spaceMarineManager, IConsoleInputManager consoleInputManager){
        this.spaceMarineManager = spaceMarineManager;
        this.consoleInputManager = consoleInputManager;
    }
    @Override
    public void execute() throws NoSuchFieldException {
        spaceMarineManager.addSpaceMarine(consoleInputManager.readSpaceMarine());
    }

}
