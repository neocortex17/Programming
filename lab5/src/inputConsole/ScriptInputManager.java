package inputConsole;

import messageManager.IMessageMessenger;
import spaceMarine.SpaceMarine;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.ZonedDateTime;

/**
 * Implementation of the IConsoleInputManager interface for reading from the script
 */
public class ScriptInputManager implements IConsoleInputManager{
    private BufferedReader reader;
    private ICommandReader commandReader;
    private ISpaceMarineReader spaceMarineReader;

    /**
     * Constructor for ScriptInputManager
     * @param reader object of BufferedReader
     * @param messageMessenger message messenger
     */
    public ScriptInputManager (BufferedReader reader, IMessageMessenger messageMessenger){
        this.reader = reader;
        commandReader = new ScriptCommandReader(reader, messageMessenger);
        spaceMarineReader = new ScriptSpaceMarineReader(reader, messageMessenger);
    }

    @Override
    public boolean ready() {
        try {
            return reader.ready();
        }catch (IOException e ){
            return false;
        }
    }

    @Override
    public String readCommand() {
        return commandReader.readCommand();
    }

    @Override
    public SpaceMarine readSpaceMarine(Integer id, ZonedDateTime creationDate) throws NoSuchFieldException {
        return spaceMarineReader.readSpaceMarine(id, creationDate);
    }

    @Override
    public SpaceMarine readSpaceMarine() throws NoSuchFieldException {
        return spaceMarineReader.readSpaceMarine();
    }
}
