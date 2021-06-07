package serverCommands;

import application.Application;
import collectionManager.CollectionManager;
import exceptions.IdentException;
import exceptions.InvalidArgumentTypeException;
import exceptions.NeedObjectException;
import exceptions.NoArgumentException;
import fileManager.DataManager;
import ident.IdentManager;
import identification.Identification;
import log.Log;
import messages.Message;
import messages.RequestType;
import messages.Response;
import messages.ResponseType;
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
    private final IdentManager identManager;

    public ServerCommandManagerImpl(CollectionManager collectionManager, DataManager dataManager, Application application, Message message, IdentManager identManager){
        this.identManager = identManager;
        clientCommandMap = new HashMap<>();
        serverCommandMap = new HashMap<>();
        history = new Stack<>();
        commandInvoker = new ServerCommandInvokerImpl();
        commandInvokerForServerCommands = new ServerCommandInvokerImpl();
        clientCommandMap.put("help", new Help(clientCommandMap.keySet(), message));
        clientCommandMap.put("info", new Info(collectionManager, message));
        clientCommandMap.put("show", new Show(collectionManager, message));
        clientCommandMap.put("add", new Add(collectionManager, dataManager, message));
        clientCommandMap.put("update", new UpdateId(collectionManager, dataManager, message));
        clientCommandMap.put("remove_by_id", new RemoveById(collectionManager, dataManager, message));
        clientCommandMap.put("clear", new Clear(collectionManager, dataManager, message));
        clientCommandMap.put("add_if_max", new AddIfMax(collectionManager, dataManager, message));
        clientCommandMap.put("count_by_weapontype", new CountByWeaponType(collectionManager, message));
        clientCommandMap.put("history", new History(history, message));
        clientCommandMap.put("group_counting_by_id", new GroupCountingById(collectionManager, message));
        clientCommandMap.put("print_field_descending_weapontype", new PrintFieldDescendingWeaponType(collectionManager, message));
        clientCommandMap.put("remove_greater", new RemoveGreater(collectionManager, message));
        clientCommandMap.put("identification", new Ident(message, identManager));
        clientCommandMap.put("registration", new Reg(message, identManager, dataManager));
        serverCommandMap.put("exit", new Exit(collectionManager, application, message));
    }

    @Override
    public Response executeClientCommand(RequestType type, String command, String arg, SpaceMarinesGetter spaceMarine, Identification identification) {
        ResponseFactory responseFactory = new ServerResponseFactory();
        if (!identManager.checkIdent(identification) && type != RequestType.IDENT_REG_COMMAND_REQUEST){
            return responseFactory.createIdentErrorResponse("identError");
        }
        if (clientCommandMap.containsKey(command)) {
            commandInvoker.setType(type);
            commandInvoker.setArg(arg);
            commandInvoker.setObject(spaceMarine);
            commandInvoker.setIdent(identification);
            try {
                clientCommandMap.get(command).acceptInvoker(commandInvoker);
                history.push(command);
                if (history.size() > 9) {
                    history.remove(0);
                }
                return responseFactory.createDefaultResponse(commandInvoker.getCommandOutput());
            } catch (NoArgumentException e) {
                return responseFactory.createErrorResponse("noArg");
            } catch (InvalidArgumentTypeException e) {
               return responseFactory.createErrorResponse("invalidArgumentType");
            } catch (NeedObjectException e) {
                return responseFactory.createNeedObjectResponse();
            }catch (IdentException e){
                return responseFactory.createIdentErrorResponse("identError");
            }

        } else {
            return responseFactory.createErrorResponse("noSuchCommand");
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

