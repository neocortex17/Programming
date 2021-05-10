package serverCommands;

import collectionManager.CollectionManager;
import messages.Message;

/**
 * The class that implements the command to clear the collection
 */
public class Clear implements ServerCommand {
    private final CollectionManager collectionManager;
    private final Message message;

    public Clear (CollectionManager collectionManager, Message message) {
        this.collectionManager = collectionManager;
        this.message = message;
    }

    @Override
    public String execute() {
        collectionManager.clear();
        return message.getMessage("clearOutput");
    }

}
