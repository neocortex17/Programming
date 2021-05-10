package clientCommands;

import client.ClientApplication;
import messages.Message;

public class Exit implements ClientCommand{
    private final ClientApplication application;
    private final Message message;

    public Exit(ClientApplication application, Message message){
        this.application = application;
        this.message = message;
    }

    @Override
    public String execute() {
        application.exit();
        return message.getMessage("exitOutput");
    }
}
