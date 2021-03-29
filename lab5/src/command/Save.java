package command;

import managers.ISpaceMarineManager;

/**
 * The class that implements the command to save a collection to a file
 */
public class Save implements Command{
   private ISpaceMarineManager spaceMarineManager;

    /**
     * Constructor for Save
     * @param spaceMarineManager collection manager
     */
   public Save(ISpaceMarineManager spaceMarineManager){
       this.spaceMarineManager = spaceMarineManager;
   }

    @Override
    public void execute() {
        spaceMarineManager.saveSpaceMarines();
    }

}
