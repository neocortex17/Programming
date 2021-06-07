package clientCommands;

import client.ClientApplication;
import exceptions.NoArgumentException;
import identification.Identification;
import inputManager.InputManager;
import messages.Message;
import messages.Request;
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
    private final ClientApplication application;
    private final Map<String, ClientCommand> clientCommandMap;
    private final ClientCommandInvoker commandInvoker;
    private static final Set<String> usedScripts = new HashSet<>();

    public ClientCommandManagerImpl(DatagramSocket socket, SocketAddress address, InputManager inputManager,
                                    OutputManager outputManager, Message message, ClientApplication application){
        this.socket = socket;
        this.address = address;
        this.inputManager = inputManager;
        this.outputManager = outputManager;
        this.message = message;
        this.application = application;
        commandInvoker = new ClientCommandInvokerImpl();
        clientCommandMap = new HashMap<>();
        clientCommandMap.put("exit", new Exit(application, message));
        clientCommandMap.put("execute_script", new ExecuteScript(socket, address, outputManager, message, application));
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
        if (command.equals("identification") || command.equals("registration")){
            Identification newIdentification = inputManager.readIdent();
            Request request = requestFactory.createIdentRegRequest(command, newIdentification, application.getIdentification());
            application.ident(newIdentification);
            requestSender.sendRequest(request);
        }
        if (arg == null || arg.equals("")){
            requestSender.sendRequest(requestFactory.createSimpleRequest(command, application.getIdentification()));
        } else {
            requestSender.sendRequest(requestFactory.createArgRequest(command, arg, application.getIdentification()));
        }
        ResponseReceiver responseReceiver = new ClientResponseReceiver(socket);
        Response response = responseReceiver.receiveResponse();
        if (response.getType() == ResponseType.NEED_OBJECT_RESPONSE){
            if (arg == null || arg.equals("")){
                requestSender.sendRequest(requestFactory.createObjectRequest(command, inputManager.readSpaceMarine(), application.getIdentification()));
            } else {
                requestSender.sendRequest(requestFactory.createArgObjectRequest(command, arg, inputManager.readSpaceMarine(), application.getIdentification()));
            }
            response = responseReceiver.receiveResponse();
        }
        if (response.getType() == ResponseType.ERROR_RESPONSE){
            outputManager.printErrorMsg(message.getMessage(response.getContent()) + "\n");
        }
        if (response.getType() == ResponseType.DEFAULT_RESPONSE){
            outputManager.printMsg(response.getContent() + "\n");
        }
        if (response.getType() == ResponseType.IDENT_ERROR_RESPONSE) {
            outputManager.printErrorMsg("авторизируйся");
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
