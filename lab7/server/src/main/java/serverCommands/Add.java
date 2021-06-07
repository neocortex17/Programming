package serverCommands;

import collectionManager.CollectionManager;
import exceptions.InvalidArgumentTypeException;
import exceptions.NeedObjectException;
import exceptions.NoArgumentException;
import fileManager.DataManager;
import identification.Identification;
import messages.Message;
import spaceMarine.SpaceMarinesGetter;

/**
 * The class of the command that adds a new item to the collection
 */
public class Add implements ServerCommand,RequiringObject, RequiringIdentification {
    private final CollectionManager collectionManager;
    private final Message message;
    private final DataManager dataManager;
    private Identification identification;
    private SpaceMarinesGetter spaceMarine;

    public Add(CollectionManager collectionManager, DataManager dataManager, Message message){
        this.collectionManager = collectionManager;
        this.dataManager = dataManager;
        this.message = message;
    }

    @Override
    public String execute() {
        collectionManager.addSpaceMarine(dataManager.addElement(spaceMarine,identification));
        return message.getMessage("addOutput");
    }

    @Override
    public void acceptInvoker(ServerCommandInvoker commandInvoker) throws NoArgumentException, InvalidArgumentTypeException, NeedObjectException {
        commandInvoker.setIdentToCommand(this);
        commandInvoker.setObjectToCommand(this);
        commandInvoker.invokeCommand(this);
    }

    @Override
    public void setObject(SpaceMarinesGetter spaceMarine) {
        this.spaceMarine = spaceMarine;
    }

    @Override
    public void setIdentification(Identification identification) {
        this.identification = identification;
    }
}
