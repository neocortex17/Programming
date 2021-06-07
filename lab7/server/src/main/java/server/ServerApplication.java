package server;

import application.Application;
import collectionManager.CollectionManager;
import collectionManager.SpaceMarineManager;
import connection.ServerConnectionManager;
import fileManager.*;
import ident.IdentManager;
import ident.IdentManagerImpl;
import log.Log;
import messages.Message;
import messages.MessageImpl;
import requests.RequestReceiver;
import requests.ServerRequestReceiver;
import responses.ResponseSender;
import responses.ServerResponseSender;
import serverCommands.*;

import java.io.IOException;
import java.nio.channels.DatagramChannel;
import java.sql.SQLException;

public class ServerApplication implements Application {
    private final int port;
    private CommandAnalyzer serverCommandAnalyzer;
    private ServerConnectionManager connectionManager;
    private final String dbName;
    private final String dbPassword;
    private Server server;

    public ServerApplication(int port, String dbName, String dbPassword){
        this.port = port;
        this.dbName = dbName;
        this.dbPassword = dbPassword;
    }

    @Override
    public void start() {
        Message message = new MessageImpl();
        String dbURL = "jdbc:postgresql://localhost:5432/postgres";
        DataManager dataManager;
        try {
            dataManager = new DataManagerImpl(dbURL, dbName, dbPassword);
        }catch (SQLException e){
            Log.getLogger().error("Can not connect to data base, app finished");
            return;
        }
        IdentManager identManager = new IdentManagerImpl();
        identManager.setUsers(dataManager.readUsers());
        CollectionManager collectionManager = new SpaceMarineManager();
        connectionManager = new ServerConnectionManager();
        ServerCommandManager serverCommandManager = new ServerCommandManagerImpl(collectionManager,dataManager,this,message,identManager);
        serverCommandAnalyzer = new ServerCommandAnalyzer(serverCommandManager, message);
        RequestReceiver requestReceiver;
        ResponseSender responseSender;
        try {
            DatagramChannel channel = connectionManager.openConnection(port);
            requestReceiver = new ServerRequestReceiver(channel);
            responseSender = new ServerResponseSender(channel);
        }catch (IOException e){
            Log.getLogger().error("Opening connection error");
            return;
        }
        RequestAnalyzer requestAnalyzer = new RequestAnalyzer(serverCommandManager);
        server = new Server(requestReceiver,requestAnalyzer,responseSender);
        collectionManager.setSpaceMarines(dataManager.readElements());
        collectionManager.getSpaceMarineStream().peek(System.out::println);
        Log.getLogger().info("Server has started");
        server.start();
    }

    @Override
    public void exit() {
        try {
            connectionManager.closeConnection();
        }catch (IOException e){
            Log.getLogger().error("Connection closing error");
        }
        server.stopExecutorServices();
        serverCommandAnalyzer.stopReading();
    }
}
