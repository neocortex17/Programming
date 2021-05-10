package responses;

import messages.Response;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.ByteBuffer;

public class ClientResponseReceiver implements ResponseReceiver{
    private final DatagramSocket socket;

    public ClientResponseReceiver(DatagramSocket socket) {
        this.socket = socket;
        try {
            socket.setSoTimeout(1000000);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Response receiveResponse() throws IOException {
        ByteBuffer bytes = ByteBuffer.allocate(4096);
        DatagramPacket receivePacket = new DatagramPacket(bytes.array(), bytes.array().length);
        socket.receive(receivePacket);
        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bytes.array()));
        try {
            return (Response) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException();
        }
    }
}
