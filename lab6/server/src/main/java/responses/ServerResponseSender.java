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
    private final SocketAddress address;
    private final ByteArrayOutputStream byteArrayOutputStream;

    public ServerResponseSender(DatagramChannel channel, SocketAddress address){
        this.channel = channel;
        this.address = address;
        byteArrayOutputStream = new ByteArrayOutputStream(4096);
    }

    public void sendResponse(Response response){
        try{
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(response);
            channel.send(ByteBuffer.wrap(byteArrayOutputStream.toByteArray()),address);
        }catch (IOException e){
            Log.getLogger().error("Response sending error", e);
        }
    }

}
