package serverCommands;

import collectionManager.CollectionManager;
import exceptions.InvalidArgumentTypeException;
import exceptions.NeedObjectException;
import exceptions.NoArgumentException;
import messages.Message;
import spaceMarine.*;

import java.util.LinkedHashSet;
import java.util.stream.Collectors;

/**
 * The class that implements the command to remove all elements from the collection that exceed the specified one
 */
public class RemoveGreater implements ServerCommand, RequiringObject {
    private final CollectionManager collectionManager;
    private final Message message;
    private SpaceMarinesGetter spaceMarine;

    public RemoveGreater(CollectionManager collectionManager, Message message) {
        this.collectionManager = collectionManager;
        this.message = message;
    }

    @Override
    public String execute() {
       LinkedHashSet<SpaceMarine> test = collectionManager.getSpaceMarineStream()
               .filter(x -> spaceMarine.compareTo(x.getSpaceMarinesGetter()) > 0 )
               .collect(Collectors.toCollection(LinkedHashSet::new));
       collectionManager.setSpaceMarines(test);
       return message.getMessage("removeGreaterOutput");
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

