package command;

import exceptions.InvalidArgumentTypeException;
import exceptions.NoArgumentException;

/**
 * Interface of commands
 * @author Vera Potapova
 * @version 1.0
 */
public interface Command {
    /**
     * Method that executes command
     */
    void execute() throws NoSuchFieldException;
    default void acceptInvoker(InvokerInterface invokerInterface) throws NoArgumentException, InvalidArgumentTypeException, NoSuchFieldException {
        invokerInterface.invokeCommand(this);
    }
}
