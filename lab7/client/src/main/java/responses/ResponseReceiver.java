package responses;

import messages.Response;

import java.io.IOException;

public interface ResponseReceiver {
    Response receiveResponse()throws IOException;
}
