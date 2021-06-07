package input;

import exceptions.ScriptException;
import identification.Identification;
import inputManager.CommandReader;
import inputManager.InputManager;
import inputManager.SpaceMarineReader;
import messages.Message;
import spaceMarine.SpaceMarinesGetter;

import java.io.BufferedReader;
import java.io.IOException;

public class ScriptInputManager implements InputManager {
    private BufferedReader bufferedReader;
    private CommandReader commandReader;
    private SpaceMarineReader spaceMarineReader;

    public ScriptInputManager(BufferedReader bufferedReader, Message message){
        this.bufferedReader = bufferedReader;
        commandReader = new ScriptCommandReader(bufferedReader);
        spaceMarineReader = new ScriptSpaceMarineReader(bufferedReader);
    }

    @Override
    public boolean ready() {
        try {
            return bufferedReader.ready();
        } catch (IOException e) {
            return false;
        }
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
    public Identification readIdent() {
        throw new ScriptException();
    }
}
