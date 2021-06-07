package serverCommands;

import command.RequiringArg;
import exceptions.IdentException;
import exceptions.InvalidArgumentTypeException;
import exceptions.NeedObjectException;
import exceptions.NoArgumentException;
import ident.IdentManager;
import identification.Identification;
import messages.Message;

public class Ident implements ServerCommand, RequiringIdentification, RequiringArg<Identification> {
    private Identification identification;
    private Identification arg;
    private final Message message;
    private final IdentManager identManager;

    public Ident(Message message, IdentManager identManager){
        this.message = message;
        this.identManager = identManager;
    }

    @Override
    public void setArg(Identification arg) {
        this.arg = arg;
    }

    @Override
    public void setIdentification(Identification identification) {
        this.identification = identification;
    }

    @Override
    public String execute() {
        identManager.removeOnlineUser(identification);
        if (identManager.checkIdent(arg) && !identManager.isOnline(arg)){
            identManager.addOnlineUser(arg);
            return message.getMessage("identOutput");
        }else {
            throw new IdentException();
        }
    }

    @Override
    public void acceptInvoker(ServerCommandInvoker commandInvoker) throws NoArgumentException, InvalidArgumentTypeException, NeedObjectException {
        commandInvoker.setIdentToCommand(this);
        commandInvoker.setIdentArgToCommand(this);
        commandInvoker.invokeCommand(this);
    }
}
