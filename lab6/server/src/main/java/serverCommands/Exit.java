package serverCommands;

import application.Application;
import collectionManager.CollectionManager;
import messages.Message;

/**
 * The class of the command that terminates the program
 */
public class Exit implements ServerCommand {
    private final Application application;
    private final Message message;
    private final CollectionManager collectionManager;

    /**
     * Constructor for Exit
     * @param application object of Application
     */
    public Exit (CollectionManager collectionManager, Application application, Message message) {
        this.application = application;
        this.collectionManager = collectionManager;
        this.message = message;
    }

    @Override
    public String execute() {
        collectionManager.saveSpaceMarines();
        application.exit();
        return message.getMessage("exitOutput");
    }
}
