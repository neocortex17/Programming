package command;

import inputConsole.IConsoleInputManager;
import managers.ISpaceMarineManager;
import spaceMarine.SpaceMarine;
    /**
    * The class of the command that adds a new item to the collection if it is greater than the maximum
    */

public class AddIfMax implements Command {
    private ISpaceMarineManager spaceMarineManager;
    private IConsoleInputManager consoleInputManager;

    /**
     * Constructor for AddIfMax
     * @param spaceMarineManager collection manager
     * @param consoleInputManager console input manager
     */

    public AddIfMax(ISpaceMarineManager spaceMarineManager, IConsoleInputManager consoleInputManager){
        this.spaceMarineManager = spaceMarineManager;
        this.consoleInputManager = consoleInputManager;
    }

    @Override
    public void execute() throws NoSuchFieldException {
        SpaceMarine spaceMarine = consoleInputManager.readSpaceMarine();
        if (spaceMarine.compareTo(spaceMarineManager.getSpaceMarineStream().max(SpaceMarine::compareTo).get()) >= 0);
        spaceMarineManager.addSpaceMarine(spaceMarine);
    }
}
