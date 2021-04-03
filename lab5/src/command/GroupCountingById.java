package command;

import managers.SpaceMarineIdManager;
import messageManager.IMessageMessenger;
import outputConsole.IConsoleOutputManager;
import spaceMarine.SpaceMarine;
import managers.ISpaceMarineManager;

/**
 * The class of the command that groups the elements of the collection by the value of the id field
 * and displays the number of elements in each group
 */
public class GroupCountingById implements Command {
    private ISpaceMarineManager spaceMarineManager;
    private IConsoleOutputManager consoleOutputManager;

    /**
     * Constructor for GroupCountingById
     * @param spaceMarineManager collection manager
     * @param consoleOutputManager console output manager
     */
    public GroupCountingById (ISpaceMarineManager spaceMarineManager, IConsoleOutputManager consoleOutputManager){
        this.spaceMarineManager = spaceMarineManager;
        this.consoleOutputManager = consoleOutputManager;

    }

    @Override
    public void execute() {
        SpaceMarineIdManager.getInstance().groupByIdModulo3(consoleOutputManager);
    }
}
