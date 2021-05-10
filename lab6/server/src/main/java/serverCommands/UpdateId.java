package serverCommands;

import collectionManager.CollectionManager;
import collectionManager.SpaceMarineIdManager;
import command.RequiringArg;
import exceptions.InvalidArgumentTypeException;
import exceptions.NeedObjectException;
import exceptions.NoArgumentException;
import messages.Message;
import spaceMarine.SpaceMarine;
import spaceMarine.SpaceMarinesGetter;

/**
 * The class that implements the command to update the value of the collection element whose id is equal to the given one
 */
public class UpdateId implements ServerCommand, RequiringArg<Integer>, RequiringObject{
    private final CollectionManager collectionManager;
    private final Message message;
    private int arg;
    private SpaceMarinesGetter spaceMarine;

    /**
     * Constructor for UpdateId
     */
    public UpdateId (CollectionManager collectionManager, Message message){
        this.collectionManager = collectionManager;
        this.message = message;
    }

    @Override
    public String execute() {
        if (!SpaceMarineIdManager.INSTANCE.idIsFree(arg)){
            SpaceMarineIdManager.INSTANCE.removeId(arg);
           SpaceMarine oldSpaceMarine = collectionManager.getSpaceMarineStream().filter(spaceMarine -> spaceMarine.getId() == arg).findAny().get();
            SpaceMarine newSpaceMarine = new SpaceMarine(oldSpaceMarine.getId(), spaceMarine, oldSpaceMarine.getCreationDate());
            collectionManager.removeById(arg);
            collectionManager.addSpaceMarine(spaceMarine);
            return message.getMessage("updateOutput");
        } else {
            return message.getMessage("notUpdateOutput");
        }
    }

    @Override
    public void setArg(Integer arg) {
        this.arg = arg;
    }

    @Override
    public void acceptInvoker(ServerCommandInvoker commandInvoker) throws NoArgumentException, InvalidArgumentTypeException, NeedObjectException {
        commandInvoker.setIntegerArgToCommand(this);
        commandInvoker.setObjectToCommand(this);
        commandInvoker.invokeCommand(this);
    }

    @Override
    public void setObject(SpaceMarinesGetter spaceMarine) {
        this.spaceMarine = spaceMarine;
    }
}
