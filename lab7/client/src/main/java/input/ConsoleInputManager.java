package input;

import identification.Identification;
import inputManager.CommandReader;
import inputManager.InputManager;
import inputManager.SpaceMarineReader;
import messages.Message;
import output.OutputManager;
import spaceMarine.SpaceMarinesGetter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInputManager implements InputManager {
    private BufferedReader reader;
    private CommandReader commandReader;
    private SpaceMarineReader spaceMarineReader;
    private final IdentReader identReader;

    public ConsoleInputManager(Message message, OutputManager outputManager) {
        reader = new BufferedReader(new InputStreamReader(System.in));
        commandReader = new ConsoleCommandReader(reader);
        spaceMarineReader = new ConsoleSpaceMarineReader(reader, message, outputManager);
        identReader = new ConsoleIdentReader(reader, message, outputManager, "salt");
    }

    @Override
    public boolean ready() throws IOException {
        return reader.ready();
    }

    @Override
    public String readCommand() throws IOException {
        return commandReader.readCommand();
    }

    @Override
    public SpaceMarinesGetter readSpaceMarine() throws IOException {
        return spaceMarineReader.readSpaceMarine();
    }

    @Override
    public Identification readIdent() throws IOException {
        return identReader.readIdent();
    }
}
