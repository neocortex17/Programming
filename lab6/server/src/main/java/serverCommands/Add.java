package serverCommands;

import collectionManager.CollectionManager;
import exceptions.InvalidArgumentTypeException;
import exceptions.NeedObjectException;
import exceptions.NoArgumentException;
import messages.Message;
import spaceMarine.SpaceMarinesGetter;

/**
 * The class of the command that adds a new item to the collection
 */
public class Add implements ServerCommand,RequiringObject {
    private final CollectionManager collectionManager;
    private final Message message;
    private SpaceMarinesGetter spaceMarine;

    public Add(CollectionManager collectionManager, Message message){
        this.collectionManager = collectionManager;
        this.message = message;
    }

    @Override
    public String execute() {
        collectionManager.addSpaceMarine(spaceMarine);
        return message.getMessage("addOutput");
    }

    @Override
    public void acceptInvoker(ServerCommandInvoker commandInvoker) throws NoArgumentException, InvalidArgumentTypeException, NeedObjectException {
        commandInvoker.setObjectToCommand(this);
        commandInvoker.invokeCommand(this);
    }

    @Override
    public void setObject(SpaceMarinesGetter spaceMarine) {
        this.spaceMarine = spaceMarine;
    }
}
