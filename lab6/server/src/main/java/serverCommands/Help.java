package serverCommands;

import exceptions.NoSuchCommandException;
import messages.Message;

import java.util.Set;

/**
 * Command class that displays help for available commands
 */
public class Help implements ServerCommand {
    private final Set<String> commands;
    private final Message message;

    /**
     * Constructor for Help
     */
    public Help(Set<String> commands, Message message){
        this.commands = commands;
        this.message = message;
    }

    @Override
    public String execute() {
        StringBuilder stringBuilder = new StringBuilder();
        commands.stream().forEach(command -> stringBuilder.append(message.getMessage(command )).append("\n"));
        return stringBuilder.toString();
    }
}
