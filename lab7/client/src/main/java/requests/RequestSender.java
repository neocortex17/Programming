package requests;

import messages.Request;

import java.io.IOException;

public interface RequestSender {
    void sendRequest(Request request) throws IOException;
}
