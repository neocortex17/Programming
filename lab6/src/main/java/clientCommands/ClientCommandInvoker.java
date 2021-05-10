package clientCommands;

import command.RequiringArg;
import exceptions.NoArgumentException;

public interface ClientCommandInvoker {
    void invokeCommand(ClientCommand command);

    void setStringArgToCommand(RequiringArg<String> command) throws NoArgumentException;

    void setArg(String arg);

    String getCommandOutput();
}
