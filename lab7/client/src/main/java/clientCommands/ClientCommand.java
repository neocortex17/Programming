package clientCommands;

import exceptions.NoArgumentException;

public interface ClientCommand {
    String execute();
    default void acceptInvoker(ClientCommandInvoker commandInvoker) throws NoArgumentException {
        commandInvoker.invokeCommand(this);
    }
}
