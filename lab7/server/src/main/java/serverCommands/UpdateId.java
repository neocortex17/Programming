package serverCommands;

import collectionManager.CollectionManager;
import collectionManager.SpaceMarineIdManager;
import command.RequiringArg;
import exceptions.InvalidArgumentTypeException;
import exceptions.NeedObjectException;
import exceptions.NoArgumentException;
import exceptions.NoSuchIdException;
import fileManager.DataManager;
import identification.Identification;
import messages.Message;
import spaceMarine.SpaceMarine;
import spaceMarine.SpaceMarinesGetter;

/**
 * The class that implements the command to update the value of the collection element whose id is equal to the given one
 */
public class UpdateId implements ServerCommand, RequiringArg<Integer>, RequiringObject, RequiringIdentification {
    private final CollectionManager collectionManager;
    private final Message message;
    private final DataManager dataManager;
    private int arg;
    private Identification identification;
    private SpaceMarinesGetter spaceMarine;

    /**
     * Constructor for UpdateId
     */
    public UpdateId (CollectionManager collectionManager, DataManager dataManager, Message message){
        this.collectionManager = collectionManager;
        this.dataManager = dataManager;
        this.message = message;
    }

    @Override
    public String execute() {
        try {
            SpaceMarine newSpaceMarine = dataManager.updateElement(spaceMarine, arg, identification);
            collectionManager.removeById(arg);
            collectionManager.addSpaceMarine(newSpaceMarine);
            return message.getMessage("updateOutput");
        }catch (NoSuchIdException e){
            return message.getMessage("notUpdateOutput");
        }
    }

    @Override
    public void setArg(Integer arg) {
        this.arg = arg;
    }

    @Override
    public void acceptInvoker(ServerCommandInvoker commandInvoker) throws NoArgumentException, InvalidArgumentTypeException, NeedObjectException {
        commandInvoker.setIdentToCommand(this);
        commandInvoker.setIntegerArgToCommand(this);
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
