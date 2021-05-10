package serverCommands;

import exceptions.InvalidArgumentTypeException;
import exceptions.NeedObjectException;
import exceptions.NoArgumentException;
import serverCommands.ServerCommandInvoker;

/**
 * Interface of commands
 * @author Vera Potapova
 * @version 1.0
 */
public interface ServerCommand {
    /**
     * Method that executes command
     */
    String execute();
    default void acceptInvoker(ServerCommandInvoker commandInvoker) throws NoArgumentException, InvalidArgumentTypeException, NeedObjectException {
        commandInvoker.invokeCommand(this);
    }
}
