package serverCommands;

import input.ConsoleCommandReader;
import inputManager.CommandReader;
import log.Log;
import messages.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ServerCommandAnalyzer extends Thread implements CommandAnalyzer{
    private boolean exit;
    private final CommandReader commandReader;
    private final Message message;
    private final ServerCommandManager commandManager;

    public ServerCommandAnalyzer(ServerCommandManager commandManager, Message message){
        commandReader = new ConsoleCommandReader(new BufferedReader(new InputStreamReader(System.in)));
        this.commandManager = commandManager;
        this.message = message;
    }

    @Override
    public void run() {
        while (!exit) {
            try {
                String inputString = commandReader.readCommand();
                if (inputString.equals("")) continue;
                commandManager.executeServerCommand(inputString);
            } catch (IOException e) {
                Log.getLogger().error(message.getMessage("endOfInput"), e);
                stopReading();
            }
        }
    }

    @Override
    public void startReading() {
        start();
    }

    @Override
    public void stopReading() {
        exit = true;
    }
}
