package serverCommands;

import application.Application;
import collectionManager.CollectionManager;
import exceptions.InvalidArgumentTypeException;
import exceptions.NeedObjectException;
import exceptions.NoArgumentException;
import log.Log;
import messages.Message;
import messages.RequestType;
import responses.ResponseFactory;
import responses.ResponseSender;
import responses.ServerResponseFactory;
import responses.ServerResponseSender;
import spaceMarine.SpaceMarinesGetter;

import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ServerCommandManagerImpl implements ServerCommandManager{
    private final Map<String, ServerCommand> clientCommandMap;
    private final Map<String, ServerCommand> serverCommandMap;
    private final Stack<String> history;
    private final ServerCommandInvoker commandInvoker;
    private final ServerCommandInvoker commandInvokerForServerCommands;

    public ServerCommandManagerImpl(CollectionManager collectionManager, Application application, Message message){
        clientCommandMap = new HashMap<>();
        serverCommandMap = new HashMap<>();
        history = new Stack<>();
        commandInvoker = new ServerCommandInvokerImpl();
        commandInvokerForServerCommands = new ServerCommandInvokerImpl();
        clientCommandMap.put("help", new Help(clientCommandMap.keySet(), message));
        clientCommandMap.put("info", new Info(collectionManager, message));
        clientCommandMap.put("show", new Show(collectionManager, message));
        clientCommandMap.put("add", new Add(collectionManager, message));
        clientCommandMap.put("update", new UpdateId(collectionManager, message));
        clientCommandMap.put("remove_by_id", new RemoveById(collectionManager, message));
        clientCommandMap.put("clear", new Clear(collectionManager, message));
        clientCommandMap.put("add_if_max", new AddIfMax(collectionManager, message));
        clientCommandMap.put("count_by_weapontype", new CountByWeaponType(collectionManager, message));
        clientCommandMap.put("history", new History(history, message));
        clientCommandMap.put("group_counting_by_id", new GroupCountingById(collectionManager, message));
        clientCommandMap.put("print_field_descending_weapontype", new PrintFieldDescendingWeaponType(collectionManager, message));
        clientCommandMap.put("remove_greater", new RemoveGreater(collectionManager, message));
        serverCommandMap.put("exit", new Exit(collectionManager, application, message));
        serverCommandMap.put("save", new Save(collectionManager, message));
    }

    @Override
    public void executeClientCommand(RequestType type, String command, String arg, SpaceMarinesGetter spaceMarine, SocketAddress address, DatagramChannel channel) {
        ResponseFactory responseFactory = new ServerResponseFactory();
        ResponseSender responseSender = new ServerResponseSender(channel, address);
        if (clientCommandMap.containsKey(command)) {
            commandInvoker.setType(type);
            commandInvoker.setArg(arg);
            commandInvoker.setObject(spaceMarine);
            try {
                clientCommandMap.get(command).acceptInvoker(commandInvoker);
                history.push(command);
                if (history.size() > 9) {
                    history.remove(0);
                }
                responseSender.sendResponse(responseFactory.createDefaultResponse(commandInvoker.getCommandOutput()));
            } catch (NoArgumentException e) {
                responseSender.sendResponse(responseFactory.createErrorResponse("noArg"));
            } catch (InvalidArgumentTypeException e) {
                responseSender.sendResponse((responseFactory.createErrorResponse("invalidArgumentType")));
            } catch (NeedObjectException e) {
                responseSender.sendResponse(responseFactory.createNeedObjectResponse());
            }

        } else {
            responseSender.sendResponse(responseFactory.createErrorResponse("noSuchCommand"));
        }
    }

    @Override
    public void executeServerCommand(String command) {
        if (serverCommandMap.containsKey(command)){
            try {
                serverCommandMap.get(command).acceptInvoker(commandInvokerForServerCommands);
                Log.getLogger().info(commandInvokerForServerCommands.getCommandOutput());
            } catch (NoArgumentException | InvalidArgumentTypeException | NeedObjectException ignored) {

            }
        } else {
            Log.getLogger().error("No such command: " + command);
        }
    }
}

