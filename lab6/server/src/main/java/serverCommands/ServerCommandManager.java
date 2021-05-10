package serverCommands;

import messages.RequestType;
import spaceMarine.SpaceMarinesGetter;

import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

public interface ServerCommandManager {
    void executeClientCommand(RequestType type, String command, String arg, SpaceMarinesGetter spaceMarine, SocketAddress address, DatagramChannel channel);
    void executeServerCommand(String command);
}
