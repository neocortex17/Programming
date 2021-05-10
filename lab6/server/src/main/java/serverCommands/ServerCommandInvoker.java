package serverCommands;

import command.RequiringArg;
import exceptions.InvalidArgumentTypeException;
import exceptions.NeedObjectException;
import exceptions.NoArgumentException;
import messages.RequestType;
import spaceMarine.SpaceMarinesGetter;

public interface ServerCommandInvoker {
    void invokeCommand(ServerCommand command);
    void setIntegerArgToCommand(RequiringArg<Integer> command) throws InvalidArgumentTypeException, NoArgumentException;
    void setStringArgToCommand(RequiringArg<String> command) throws NoArgumentException;
    void setObjectToCommand(RequiringObject command) throws NeedObjectException;
    void setArg(String arg);
    void setObject(SpaceMarinesGetter spaceMarine);
    void setType(RequestType type);
    String getCommandOutput();
}
