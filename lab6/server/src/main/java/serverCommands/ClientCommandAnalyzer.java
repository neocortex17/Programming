package serverCommands;

import log.Log;
import messages.Request;
import requests.RequestReceiver;
import requests.ServerRequestReceiver;

import java.io.IOException;
import java.nio.channels.DatagramChannel;

public class ClientCommandAnalyzer extends Thread implements CommandAnalyzer{
    private boolean exit;
    private final ServerCommandManager commandManager;
    private final DatagramChannel channel;

    public ClientCommandAnalyzer(ServerCommandManager commandManager, DatagramChannel channel){
        this.commandManager = commandManager;
        this.channel = channel;
    }

    @Override
    public void startReading(){
        start();
    }

    @Override
    public void run() {
        while (!exit){
            try {
                RequestReceiver requestReceiver = new ServerRequestReceiver(channel);
                Request request = requestReceiver.receiveRequest();
                commandManager.executeClientCommand(request.getType(), request.getCommand(), request.getArg(),
                        request.getObject(), requestReceiver.getAddress(), channel);
            } catch (IOException e) {
                stopReading();
            } catch (ClassNotFoundException e) {
                Log.getLogger().error("Serializing error", e);
            }
        }
        Log.getLogger().error("Server has stopped");
    }

    @Override
    public void stopReading() {
        exit = true;
    }
}
