package serverCommands;

import collectionManager.CollectionManager;
import exceptions.InvalidArgumentTypeException;
import exceptions.NeedObjectException;
import exceptions.NoArgumentException;
import fileManager.DataManager;
import identification.Identification;
import messages.Message;

/**
 * The class that implements the command to clear the collection
 */
public class Clear implements ServerCommand, RequiringIdentification {
    private final CollectionManager collectionManager;
    private final DataManager dataManager;
    private final Message message;
    private Identification identification;

    public Clear (CollectionManager collectionManager, DataManager dataManager, Message message) {
        this.collectionManager = collectionManager;
        this.dataManager = dataManager;
        this.message = message;
    }

    @Override
    public void setIdentification(Identification identification) {
        this.identification = identification;
    }

    @Override
    public void acceptInvoker(ServerCommandInvoker commandInvoker) throws NoArgumentException, InvalidArgumentTypeException, NeedObjectException {
        commandInvoker.setIdentToCommand(this);
        commandInvoker.invokeCommand(this);
    }

    @Override
    public String execute() {
        dataManager.clearElements(identification);
        collectionManager.clear();
        collectionManager.setSpaceMarines(dataManager.readElements());
        return message.getMessage("clearOutput");
    }

}
