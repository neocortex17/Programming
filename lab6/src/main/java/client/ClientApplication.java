package client;

import application.Application;
import clientCommands.ClientCommandManager;
import clientCommands.ClientCommandManagerImpl;
import connection.ClientConnectionManager;
import connection.ClientConnectionManagerImpl;
import exceptions.ScriptRecursionException;
import input.ConsoleInputManager;
import inputManager.InputManager;
import messages.Message;
import messages.MessageImpl;
import output.ConsoleOutputManager;
import output.OutputManager;

import java.io.IOException;
import java.net.DatagramSocket;

public class ClientApplication implements Application {
    private boolean exit;
    private Message message;
    private OutputManager outputManager;
    private InputManager inputManager;
    private ClientConnectionManager connectionManager;
    private ClientCommandManager commandManager;
    private final String address;
    private final int port;

    public ClientApplication(String address, int port){
        exit = false;
        this.address = address;
        this.port = port;
    }

    @Override
    public void start(){
        try {
            outputManager = new ConsoleOutputManager();
            message = new MessageImpl();
            connectionManager = new ClientConnectionManagerImpl();
            DatagramSocket socket = connectionManager.openConnection(address, port);
            inputManager = new ConsoleInputManager(message, outputManager);
            commandManager = new ClientCommandManagerImpl(socket, connectionManager.getSocketAddress(),
                    inputManager, outputManager, message, this);
            outputManager.printMsg(message.getMessage("start") + "\n");
            run();
        } catch (IOException e){
            outputManager.printErrorMsg(message.getMessage("noConnection") + "\n");
        }
    }

    private void run() throws IOException {
         while (!exit) {
            String inputString;
            try {
                inputString = inputManager.readCommand();
                if (inputString.equals("")) continue;
                String[] input = inputString.split("\\s+", 2);
                if (input.length == 1) {
                    commandManager.executeCommand(input[0], null);
                } else {
                    commandManager.executeCommand(input[0], input[1]);
                }
            } catch (IOException e) {
                outputManager.printErrorMsg(message.getMessage("endOfInput") + "\n");
                exit();
            } catch (ScriptRecursionException e){
                outputManager.printErrorMsg(message.getMessage("scriptRecursion") + "\n");
            }
        }
    }

    @Override
    public void exit(){
        exit = true;
        connectionManager.closeConnection();
    }
}
