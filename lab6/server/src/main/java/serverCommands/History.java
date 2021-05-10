package serverCommands;

import messages.Message;

import java.util.Stack;

/**
 * The class that implements the command print the last 8 commands (without their arguments)
 */
public class History implements ServerCommand {
    private final Stack<String> history;
    private final Message message;

    /**
     * Constructor for History
     */
    History(Stack<String> history, Message message){
        this.history = history;
        this.message = message;
    }

    @Override
    public String execute() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(message.getMessage("historyOutput"))
                .append(":\n");
        history.forEach(command -> stringBuilder.append(command).append("\n"));
        return stringBuilder.toString();
    }

}
