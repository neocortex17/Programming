package responses;

import messages.Response;

import java.net.SocketAddress;

public interface ResponseSender {
    void sendResponse(Response response, SocketAddress address);
}
