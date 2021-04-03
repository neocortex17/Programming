package command;

import managers.ISpaceMarineManager;
import outputConsole.IConsoleOutputManager;

/**
 * The class that implements the command to display the values of the weaponType field of all elements in descending order
 */
public class PrintFieldDescendingWeaponType implements Command {
    private ISpaceMarineManager spaceMarineManager;
    private IConsoleOutputManager consoleOutputManager;

    /**
     * Constructor for PrintFieldDescendingWeaponType
     * @param spaceMarineManager collection manager
     * @param consoleOutputManager console output manager
     */
    public PrintFieldDescendingWeaponType(ISpaceMarineManager spaceMarineManager, IConsoleOutputManager consoleOutputManager){
        this.spaceMarineManager = spaceMarineManager;
        this.consoleOutputManager = consoleOutputManager;
    }

    @Override
    public void execute() {
        spaceMarineManager.getSpaceMarineStream()
                .sorted((first, second) -> second.getWeaponType().getWeaponInInt() - first.getWeaponType().getWeaponInInt())
                .map(spaceMarine -> spaceMarine.getWeaponType().toString().concat("\n"))
                .forEachOrdered(consoleOutputManager :: printMess);
    }
}
