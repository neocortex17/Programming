package serverCommands;

import command.RequiringArg;
import exceptions.*;
import fileManager.DataManager;
import ident.IdentManager;
import identification.Identification;
import messages.Message;

public class Reg implements ServerCommand, RequiringIdentification, RequiringArg<Identification> {
    private Identification identification;
    private Identification arg;
    private final Message message;
    private final IdentManager identManager;
    private final DataManager dataManager;

    public Reg(Message message, IdentManager identManager, DataManager dataManager){
        this.message = message;
        this.identManager = identManager;
        this.dataManager = dataManager;
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
        try {
            dataManager.addUser(arg);
            identManager.addUser(arg);
            identManager.addOnlineUser(arg);
        }catch (DBException e){
            throw new IdentException();
        }
        return message.getMessage("regOutput");
    }

    @Override
    public void acceptInvoker(ServerCommandInvoker commandInvoker) throws NoArgumentException, InvalidArgumentTypeException, NeedObjectException {
        commandInvoker.setIdentToCommand(this);
        commandInvoker.setIdentArgToCommand(this);
        commandInvoker.invokeCommand(this);
    }
}
