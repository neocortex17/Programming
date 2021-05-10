package serverCommands;

import collectionManager.CollectionManager;
import messages.Message;

/**
 * The class that implements the command to save a collection to a file
 */
public class Save implements ServerCommand {
    private final CollectionManager collectionManager;
    private final Message message;

    /**
     * Constructor for Save
     */
   public Save(CollectionManager collectionManager, Message message){
       this.collectionManager = collectionManager;
       this.message = message;
   }

    @Override
    public String execute() {
        collectionManager.saveSpaceMarines();
        return message.getMessage("saveOutput");
    }
}
