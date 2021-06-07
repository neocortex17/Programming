package serverCommands;

import identification.Identification;
import messages.RequestType;
import messages.Response;
import spaceMarine.SpaceMarinesGetter;

import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

public interface ServerCommandManager {
    Response executeClientCommand(RequestType type, String command, String arg, SpaceMarinesGetter spaceMarine, Identification identification);
    void executeServerCommand(String command);
}
