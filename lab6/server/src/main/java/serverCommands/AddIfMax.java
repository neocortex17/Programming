package serverCommands;

import collectionManager.CollectionManager;
import exceptions.InvalidArgumentTypeException;
import exceptions.NeedObjectException;
import exceptions.NoArgumentException;
import messages.Message;
import spaceMarine.SpaceMarine;
import spaceMarine.SpaceMarinesGetter;

/**
* The class of the command that adds a new item to the collection if it is greater than the maximum
*/

public class AddIfMax implements ServerCommand, RequiringObject {
    private final CollectionManager collectionManager;
    private final Message message;
    private SpaceMarinesGetter spaceMarine;

    public AddIfMax(CollectionManager collectionManager, Message message) {
        this.collectionManager = collectionManager;
        this.message = message;
    }

    @Override
    public String execute() {
        if (spaceMarine.compareTo(collectionManager.getSpaceMarineStream().max(SpaceMarine::compareTo).get().getSpaceMarinesGetter()) > 0){
            collectionManager.addSpaceMarine(spaceMarine);
            return message.getMessage("addOutput");
        }else{
            return message.getMessage("notAddOutput");
        }
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
