package command;

import managers.ISpaceMarineManager;

/**
 * The class that implements the command to clear the collection
 */
public class Clear implements Command {
    private ISpaceMarineManager spaceMarineManager;

    /**
     * Constructor for Clear
     * @param spaceMarineManager collection manager
     */
    public Clear (ISpaceMarineManager spaceMarineManager){
        this.spaceMarineManager=spaceMarineManager;
    }

    @Override
    public void execute() {
        spaceMarineManager.clear();
    }
}
