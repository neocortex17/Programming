package serverCommands;

import collectionManager.CollectionManager;
import command.RequiringArg;
import exceptions.InvalidArgumentTypeException;
import exceptions.NoArgumentException;
import exceptions.NoSuchIdException;
import fileManager.DataManager;
import identification.Identification;
import messages.Message;

/**
 * The class that implements the command to remove an item from a collection by its id
 */
public class RemoveById implements ServerCommand, RequiringArg<Integer>, RequiringIdentification {
    private final CollectionManager collectionManager;
    private final Message message;
    private final DataManager dataManager;
    private Integer arg;
    private Identification identification;

    /**
     * Constructor for RemoveById
     */
    public RemoveById (CollectionManager collectionManager, DataManager dataManager, Message message){
        this.collectionManager = collectionManager;
        this.dataManager = dataManager;
        this.message = message;
    }

    @Override
    public String execute() {
        try {
            dataManager.removeElement(arg, identification);
            collectionManager.removeById(arg);
            return message.getMessage("removeOutput");
        }catch (NoSuchIdException e){
            return message.getMessage("notRemoveOutput");
        }
    }

    @Override
    public void setArg(Integer arg) {
        this.arg = arg;
    }

    @Override
    public void acceptInvoker(ServerCommandInvoker commandInvoker) throws NoArgumentException, InvalidArgumentTypeException {
        commandInvoker.setIdentToCommand(this);
        commandInvoker.setIntegerArgToCommand(this);
        commandInvoker.invokeCommand(this);
    }

    @Override
    public void setIdentification(Identification identification) {
        this.identification = identification;
    }
}
