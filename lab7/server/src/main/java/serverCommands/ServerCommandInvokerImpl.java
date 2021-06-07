package serverCommands;

import command.RequiringArg;
import exceptions.InvalidArgumentTypeException;
import exceptions.NeedObjectException;
import exceptions.NoArgumentException;
import identification.Identification;
import messages.RequestType;
import spaceMarine.SpaceMarinesGetter;

public class ServerCommandInvokerImpl implements ServerCommandInvoker{
    private String arg;
    private SpaceMarinesGetter spaceMarine;
    private RequestType type;
    private String output;
    private Identification identification;

    @Override
    public void setIdentToCommand(RequiringIdentification command) throws NoArgumentException {
        command.setIdentification(identification);
    }

    @Override
    public void setIdentArgToCommand(RequiringArg<Identification> command) {
        command.setArg(Identification.parse(arg));
    }

    @Override
    public void setIdent(Identification identification) {
        this.identification = identification;
    }

    @Override
    public void invokeCommand(ServerCommand command) {
        output = command.execute();
    }

    @Override
    public void setIntegerArgToCommand(RequiringArg<Integer> command) throws InvalidArgumentTypeException, NoArgumentException {
        if (type == RequestType.ARG_REQUEST || type == RequestType.ARG_OBJECT_REQUEST){
            try {
                command.setArg(Integer.parseInt(arg));
            }catch (NumberFormatException e){
                throw new InvalidArgumentTypeException();
            }
        }else{
            throw new NoArgumentException();
        }
    }

    @Override
    public void setStringArgToCommand(RequiringArg<String> command) throws NoArgumentException {
        if (type == RequestType.ARG_REQUEST || type == RequestType.ARG_OBJECT_REQUEST){
            command.setArg(arg);
        }else {
            throw new NoArgumentException();
        }
    }

    @Override
    public void setObjectToCommand(RequiringObject command) throws NeedObjectException {
        if (type == RequestType.OBJECT_REQUEST || type == RequestType.ARG_OBJECT_REQUEST){
            command.setObject(spaceMarine);
        }else {
            throw new NeedObjectException();
        }
    }

    @Override
    public void setArg(String arg) {
        this.arg = arg;
    }

    @Override
    public void setObject(SpaceMarinesGetter spaceMarine) {
        this.spaceMarine = spaceMarine;
    }

    @Override
    public void setType(RequestType type) {
        this.type = type;
    }

    @Override
    public String getCommandOutput() {
        return this.output;
    }
}
