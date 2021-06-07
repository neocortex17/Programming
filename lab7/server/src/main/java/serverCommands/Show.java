package serverCommands;

import collectionManager.CollectionManager;
import messages.Message;

/**
 * The class that implements the command to output to the standard output stream all the elements of the collection in string representation
 */
public class Show implements ServerCommand {
    private final CollectionManager collectionManager;
    private final Message message;

    /**
     * Constructor for Show
     */
    public Show (CollectionManager collectionManager, Message message){
        this.collectionManager = collectionManager;
        this.message = message;
    }

    @Override
    public String  execute() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(message.getMessage("showOutput"))
                .append(" (")
                .append(collectionManager.getLength())
                .append("):\n");
        collectionManager.getSpaceMarineStream()
                .forEachOrdered(spaceMarine -> stringBuilder.append(spaceMarine).append("\n"));
        return stringBuilder.toString();
    }
}
