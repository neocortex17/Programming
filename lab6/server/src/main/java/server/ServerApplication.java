package server;

import application.Application;
import collectionManager.CollectionManager;
import collectionManager.SpaceMarineManager;
import connection.ServerConnectionManager;
import exceptions.*;
import fileManager.*;
import log.Log;
import messages.Message;
import messages.MessageImpl;
import serverCommands.*;

import java.io.IOException;
import java.nio.channels.DatagramChannel;

public class ServerApplication implements Application {
    private final int port;
    private CommandAnalyzer clientCommandAnalyzer;
    private CommandAnalyzer serverCommandAnalyzer;
    private ServerConnectionManager connectionManager;

    public ServerApplication(int port){
        this.port = port;
    }

    @Override
    public void start() {
        String fileName = System.getenv("LAB6");
        Message message = new MessageImpl();
        Reader reader = new ReaderFromCSV(fileName);
        Writer writer = new WriterToCSV(fileName);
        FileManager fileManager = new FileManagerImpl(reader, writer);
        CollectionManager collectionManager = new SpaceMarineManager(fileManager);
        connectionManager = new ServerConnectionManager();
        ServerCommandManager commandManager = new ServerCommandManagerImpl(collectionManager, this, message);
        serverCommandAnalyzer = new ServerCommandAnalyzer(commandManager, message);
        try {
            DatagramChannel channel = connectionManager.openConnection(port);
            clientCommandAnalyzer = new ClientCommandAnalyzer(commandManager, channel);
        }catch (IOException e){
            Log.getLogger().error("Opening connection error", e);
            return;
        }
        try {
            collectionManager.addSpaceMarines();
        }catch (InvalidFieldException | InvalidArgumentTypeException | NoDataException | BrokenDataException e){
            Log.getLogger().error("File is broken", e);
        }catch (NoEvnVarException e){
            Log.getLogger().error("environmental variable LAB6 doesn't exist, application stopped", e);
        }
        Log.getLogger().info("Server has started");
        clientCommandAnalyzer.startReading();
        serverCommandAnalyzer.startReading();
    }

    @Override
    public void exit() {
        try {
            connectionManager.closeConnection();
        }catch (IOException e){
            Log.getLogger().error("Connection closing error", e);
        }
        clientCommandAnalyzer.stopReading();
        serverCommandAnalyzer.stopReading();
    }
}
