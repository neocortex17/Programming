package responses;

import log.Log;
import messages.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ServerResponseSender implements ResponseSender{
    private final DatagramChannel channel;

    public ServerResponseSender(DatagramChannel channel){
        this.channel = channel;
    }

    public void sendResponse(Response response, SocketAddress address){
        try{
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(4096);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(response);
            channel.send(ByteBuffer.wrap(byteArrayOutputStream.toByteArray()),address);
        }catch (IOException e){
            Log.getLogger().error("Response sending error", e);
        }
    }

}
