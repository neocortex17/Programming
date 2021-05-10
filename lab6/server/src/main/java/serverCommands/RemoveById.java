package serverCommands;

import collectionManager.CollectionManager;
import command.RequiringArg;
import exceptions.InvalidArgumentTypeException;
import exceptions.NoArgumentException;
import messages.Message;

/**
 * The class that implements the command to remove an item from a collection by its id
 */
public class RemoveById implements ServerCommand, RequiringArg<Integer> {
    private final CollectionManager collectionManager;
    private final Message message;
    private Integer arg;

    /**
     * Constructor for RemoveById
     */
    public RemoveById (CollectionManager collectionManager, Message message){
        this.collectionManager = collectionManager;
        this.message = message;
    }

    @Override
    public String execute() {
        if(collectionManager.removeById(arg)){
            return message.getMessage("removeOutput");
        } else {
            return message.getMessage("notRemoveOutput");
        }
    }

    @Override
    public void setArg(Integer arg) {
        this.arg = arg;
    }

    @Override
    public void acceptInvoker(ServerCommandInvoker commandInvoker) throws NoArgumentException, InvalidArgumentTypeException {
        commandInvoker.setIntegerArgToCommand(this);
        commandInvoker.invokeCommand(this);
    }
}
