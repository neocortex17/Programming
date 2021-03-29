package command;

import application.IApplication;
import exceptions.InvalidArgumentTypeException;
import exceptions.NoArgumentException;
import exceptions.NoSuchCommandException;
import inputConsole.IConsoleInputManager;
import managers.ISpaceMarineManager;
import messageManager.IMessageMessenger;
import outputConsole.IConsoleOutputManager;

import java.util.*;

/**
 * Implementing the CommandManager Interface
 * @author neocortex
 */
public class CommandManager implements ICommandManager{
     private InvokerInterface invokerInterface;
     private IMessageMessenger messageMessenger;
     private IConsoleOutputManager consoleOutputManager;
     private Stack<String> history = new Stack<>();
     private Map<String, Command> commandMap;
     private static Set<String> script = new HashSet<>();

    /**
     * Constructor for CommandManager
     * commandMap this is a collection with command names and their implementation classes
     * @param spaceMarineManager collection manager
     * @param application application class
     * @param messageMessenger message messenger
     * @param consoleOutputManager console output manager
     * @param consoleInputManager console input manager
     */
    public CommandManager(ISpaceMarineManager spaceMarineManager, IApplication application, IMessageMessenger messageMessenger, IConsoleOutputManager consoleOutputManager, IConsoleInputManager consoleInputManager){
        this.consoleOutputManager = consoleOutputManager;
        this.messageMessenger = messageMessenger;
        commandMap = new HashMap<>();
        invokerInterface = new Invoker(messageMessenger);
        commandMap.put("help", new Help(commandMap.keySet(),messageMessenger,consoleOutputManager));
        commandMap.put("info", new Info(spaceMarineManager, messageMessenger, consoleOutputManager));
        commandMap.put("show", new Show(spaceMarineManager, messageMessenger, consoleOutputManager));
        commandMap.put("add", new Add(spaceMarineManager, consoleInputManager));
        commandMap.put("update_id", new UpdateId(spaceMarineManager, messageMessenger, consoleOutputManager, consoleInputManager));
        commandMap.put("remove_by_id", new RemoveById(spaceMarineManager, messageMessenger, consoleOutputManager));
        commandMap.put("clear", new Clear(spaceMarineManager));
        commandMap.put("save", new Save(spaceMarineManager));
        commandMap.put("execute_script", new ExecuteScript(messageMessenger, consoleOutputManager, spaceMarineManager, application));
        commandMap.put("exit", new Exit(application));
        commandMap.put("add_if_max", new AddIfMax(spaceMarineManager, consoleInputManager));
        commandMap.put("remove_greater", new RemoveGreater(spaceMarineManager,  messageMessenger, consoleOutputManager, consoleInputManager));
        commandMap.put("history", new History(history, consoleOutputManager));
        commandMap.put("group_counting_by_id", new GroupCountingById(spaceMarineManager,consoleOutputManager));
        commandMap.put("count_by_weapon_type", new CountByWeaponType(spaceMarineManager, messageMessenger, consoleOutputManager));
        commandMap.put("print_field_descending_weapon_type", new PrintFieldDescendingWeaponType(spaceMarineManager, consoleOutputManager));
    }

    @Override
    public void scriptRemove(String scriptName) {
        script.remove(scriptName);
    }

    @Override
    public void executeCommand(String command, String arg) throws NoSuchCommandException{
        if (command.equals("")){
            return;
        }
        if (commandMap.containsKey(command)){
            invokerInterface.setArg(arg);
            try {
                commandMap.get(command).acceptInvoker(invokerInterface);
            }catch (NoArgumentException | InvalidArgumentTypeException | NoSuchFieldException e){
                consoleOutputManager.printError(e.getMessage() + "\n");
                return;
            }
            history.push(command);
            if (history.size() > 8){
                history.remove(0);
            }
        }else {
            throw new NoSuchCommandException(messageMessenger.getExceptionMess("noSuchCommand"));
        }
    }

    @Override
    public void scriptAddHistory(String scriptName){
        script.add(scriptName);
    }

    @Override
    public boolean scriptIsUsed (String scriptName){
        return script.contains(scriptName);
    }

    @Override
    public void clearScript(){
        script.clear();
    }
}
