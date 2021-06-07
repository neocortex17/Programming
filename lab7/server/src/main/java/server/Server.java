package server;

import log.Log;
import messages.Request;
import messages.Response;
import requests.RequestReceiver;
import responses.ResponseSender;
import serverCommands.RequestAnalyzer;

import java.io.IOException;
import java.net.SocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

public class Server {
    private final ExecutorService receiveRequests = Executors.newFixedThreadPool(10);
    private final ExecutorService analyzeRequests = new ForkJoinPool(10);
    //private final ExecutorService analyzeRequests = Executors.newFixedThreadPool(10);
    private final ExecutorService sendResponses = new ForkJoinPool(10);
    private final RequestReceiver requestReceiver;
    private final RequestAnalyzer requestAnalyzer;
    private final ResponseSender responseSender;

    public Server (RequestReceiver requestReceiver, RequestAnalyzer requestAnalyzer, ResponseSender responseSender){
        this.requestReceiver = requestReceiver;
        this.requestAnalyzer = requestAnalyzer;
        this.responseSender = responseSender;
    }

    public void start(){
        Runnable receivingRequestRunnable = () -> {
            try {
                while (true){
                    Request request = requestReceiver.receiveRequest();
                    analyzeRequest(request,requestReceiver.getAddress());
                }
            }catch (IOException | ClassNotFoundException e){
                Log.getLogger().error("Receive request error");
            }
        };
        receiveRequests.submit(receivingRequestRunnable);
    }

    private void analyzeRequest(Request request, SocketAddress address){
        Runnable analyzingRequestRunnable = () -> {
            Response response = requestAnalyzer.analyzeRequest(request);
            sendResponse(response, address);
        };
        analyzeRequests.submit(analyzingRequestRunnable);
    }

    private void sendResponse(Response response,SocketAddress address){
        Runnable sendingResponseRunnable = () -> responseSender.sendResponse(response,address);
        sendResponses.submit(sendingResponseRunnable);
    }

    public void stopExecutorServices(){
        receiveRequests.shutdown();
        analyzeRequests.shutdown();
        sendResponses.shutdown();
    }
}
