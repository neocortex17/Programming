package serverCommands;

import command.RequiringArg;
import exceptions.InvalidArgumentTypeException;
import exceptions.NeedObjectException;
import exceptions.NoArgumentException;
import ident.IdentManager;
import identification.Identification;
import messages.RequestType;
import spaceMarine.SpaceMarinesGetter;

public interface ServerCommandInvoker {
    void invokeCommand(ServerCommand command);
    void setIntegerArgToCommand(RequiringArg<Integer> command) throws InvalidArgumentTypeException, NoArgumentException;
    void setStringArgToCommand(RequiringArg<String> command) throws NoArgumentException;
    void setObjectToCommand(RequiringObject command) throws NeedObjectException;
    void setIdentToCommand(RequiringIdentification command) throws NoArgumentException;
    void setIdentArgToCommand(RequiringArg<Identification> command);
    void setArg(String arg);
    void setIdent(Identification identification);
    void setObject(SpaceMarinesGetter spaceMarine);
    void setType(RequestType type);
    String getCommandOutput();
}
