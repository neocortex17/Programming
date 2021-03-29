package command;

import application.IApplication;
import exceptions.*;
import inputConsole.IConsoleInputManager;
import inputConsole.ScriptInputManager;
import managers.ISpaceMarineManager;
import messageManager.IMessageMessenger;
import outputConsole.IConsoleOutputManager;

import java.io.*;
import java.lang.NoSuchFieldException;

/**
 * The class of the command that reads and executes the script from the specified file
 */
public class ExecuteScript implements Command, RequiringArg<String> {
    private IConsoleOutputManager consoleOutputManager;
    private ISpaceMarineManager spaceMarineManager;
    private IMessageMessenger messageMessenger;
    private IApplication application;
    private String arg;

    /**
     * Constructor for ExecuteScript
     * @param messageMessenger message messenger
     * @param consoleOutputManager console output manager
     * @param spaceMarineManager collection manager
     * @param application object of Application
     */
    public ExecuteScript(IMessageMessenger messageMessenger, IConsoleOutputManager consoleOutputManager, ISpaceMarineManager spaceMarineManager, IApplication application){
        this.application = application;
        this.messageMessenger = messageMessenger;
        this.consoleOutputManager = consoleOutputManager;
        this.spaceMarineManager = spaceMarineManager;
    }

    @Override
    public void execute() throws NoSuchFieldException {
        try {
            IConsoleInputManager consoleInputManager = new ScriptInputManager(new BufferedReader(new FileReader(arg)),messageMessenger);
            ICommandManager commandManager = new CommandManager(spaceMarineManager, application, messageMessenger,consoleOutputManager, consoleInputManager);
            if (commandManager.scriptIsUsed(arg)) {
                commandManager.clearScript();
                throw new ScriptRecursionException(messageMessenger.getExceptionMess("scriptRecursion"));
            }
            commandManager.scriptAddHistory(arg);
            while (consoleInputManager.ready()){
                try {
                    String inputString = consoleInputManager.readCommand();
                    String[] input = inputString.split("\\s+",2);
                    if (input.length < 2){
                        commandManager.executeCommand(input[0],"");
                    }else {
                        commandManager.executeCommand(input[0],input[1]);
                    }
                }catch (NoSuchCommandException | ScriptException e){
                    consoleOutputManager.printError(messageMessenger.getExceptionMess("script") + "\n");
                    return;
                }
            }
            commandManager.scriptRemove(arg);
        }catch (IOException e){
            consoleOutputManager.printError(messageMessenger.getExceptionMess("noFile") + "\n");
        }
    }

    @Override
    public void setArg(String arg) {
        this.arg = arg;
    }

    @Override
    public void acceptInvoker(InvokerInterface invokerInterface) throws NoArgumentException, NoSuchFieldException {
        invokerInterface.setStringArg(this);
        invokerInterface.invokeCommand(this);
    }
}
