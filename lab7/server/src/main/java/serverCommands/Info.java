package serverCommands;

import collectionManager.CollectionManager;
import messages.Message;

/**
 * The class that implements the command to print information about the collection (type, initialization date, number of elements, etc.) to the standard output stream
 */
public class Info implements ServerCommand {
    private final CollectionManager collectionManager;
    private final Message message;

    /**
     * Constructor for Info
     */
    public Info(CollectionManager collectionManager, Message message){
        this.collectionManager = collectionManager;
        this.message = message;
    }

    @Override
    public String execute() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(message.getMessage("collectionType"))
                .append(": ")
                .append(collectionManager.getType().getSimpleName())
                .append("\n")
                .append(message.getMessage("collectionInitDate"))
                .append(": ")
                .append(collectionManager.getInitDate())
                .append("\n")
                .append(message.getMessage("collectionSize"))
                .append(": ")
                .append(collectionManager.getLength());
        return stringBuilder.toString();
    }
}
