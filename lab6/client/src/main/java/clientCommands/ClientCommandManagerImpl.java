package clientCommands;

import client.ClientApplication;
import exceptions.NoArgumentException;
import inputManager.InputManager;
import messages.Message;
import messages.Response;
import messages.ResponseType;
import output.OutputManager;
import requests.ClientRequestFactory;
import requests.ClientRequestSender;
import requests.RequestFactory;
import requests.RequestSender;
import responses.ClientResponseReceiver;
import responses.ResponseReceiver;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ClientCommandManagerImpl implements ClientCommandManager{
    private final DatagramSocket socket;
    private final SocketAddress address;
    private final InputManager inputManager;
    private final OutputManager outputManager;
    private final Message message;
    private final Map<String, ClientCommand> clientCommandMap;
    private final ClientCommandInvoker commandInvoker;
    private static final Set<String> usedScripts = new HashSet<>();

    public ClientCommandManagerImpl(DatagramSocket socket, SocketAddress address, InputManager inputManager,
                                    OutputManager outputManager, Message message, ClientApplication app){
        this.socket = socket;
        this.address = address;
        this.inputManager = inputManager;
        this.outputManager = outputManager;
        this.message = message;
        commandInvoker = new ClientCommandInvokerImpl();
        clientCommandMap = new HashMap<>();
        clientCommandMap.put("exit", new Exit(app, message));
        clientCommandMap.put("execute_script", new ExecuteScript(socket, address, outputManager, message, app));
    }

    @Override
    public void usedScriptAdd(String scriptName) {
        usedScripts.add(scriptName);
    }

    @Override
    public boolean scriptIsUsed(String scriptName) {
        return usedScripts.contains(scriptName);
    }

    @Override
    public void executeCommand(String command, String arg) throws IOException {
        if (clientCommandMap.containsKey(command)){
            executeClientCommand(command, arg);
        } else {
            try {
                executeServerCommand(command, arg);
            }catch (SocketTimeoutException e){
                outputManager.printErrorMsg(message.getMessage("noConnection") + "\n");
            }
        }
    }

    @Override
    public void clearUsedScripts() {
        usedScripts.clear();
    }

    @Override
    public void usedScriptRemove(String scriptName) {
        usedScripts.remove(scriptName);
    }

    private void executeServerCommand(String command, String arg) throws IOException {
        RequestSender requestSender = new ClientRequestSender(address, socket);
        RequestFactory requestFactory = new ClientRequestFactory();
        if (arg == null || arg.equals("")){
            requestSender.sendRequest(requestFactory.createSimpleRequest(command));
        } else {
            requestSender.sendRequest(requestFactory.createArgRequest(command, arg));
        }
        ResponseReceiver responseReceiver = new ClientResponseReceiver(socket);
        Response response = responseReceiver.receiveResponse();
        if (response.getType() == ResponseType.NEED_OBJECT_RESPONSE){
            if (arg == null || arg.equals("")){
                requestSender.sendRequest(requestFactory.createObjectRequest(command, inputManager.readSpaceMarine()));
            } else {
                requestSender.sendRequest(requestFactory.createArgObjectRequest(command, arg, inputManager.readSpaceMarine()));
            }
            response = responseReceiver.receiveResponse();
        }
        if (response.getType() == ResponseType.ERROR_RESPONSE){
            outputManager.printErrorMsg(message.getMessage(response.getContent()) + "\n");
        }
        if (response.getType() == ResponseType.DEFAULT_RESPONSE){
            outputManager.printMsg(response.getContent() + "\n");
        }
    }

    private void executeClientCommand(String command, String arg){
        commandInvoker.setArg(arg);
        try {
            clientCommandMap.get(command).acceptInvoker(commandInvoker);
            outputManager.printMsg(commandInvoker.getCommandOutput() + "\n");
        } catch (NoArgumentException e) {
            outputManager.printErrorMsg(message.getMessage("noArg") + "\n");
        }
    }
}
