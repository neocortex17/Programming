package command;

import outputConsole.IConsoleOutputManager;

import java.util.Stack;

/**
 * The class that implements the command print the last 8 commands (without their arguments)
 */
public class History implements Command {
    private Stack<String> history;
    private IConsoleOutputManager consoleOutputManager;

    /**
     * Constructor for History
     */
    History(Stack<String> history, IConsoleOutputManager consoleOutputManager){
        this.history = history;
        this.consoleOutputManager = consoleOutputManager;
    }

    @Override
    public void execute() {
        for (int i = history.size() - 1; i >= 0; i --){
            consoleOutputManager.printMess(history.get(i) + "\n");
        }
    }
}
