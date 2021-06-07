package connection;

import java.io.IOException;
import java.nio.channels.DatagramChannel;

public interface ServerConnection {
    DatagramChannel openConnection(int port) throws IOException;
    void closeConnection() throws IOException;
}
