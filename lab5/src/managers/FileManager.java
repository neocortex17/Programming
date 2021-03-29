package managers;

import exceptions.*;
import messageManager.IMessageMessenger;
import spaceMarine.SpaceMarine;

import java.util.Collection;

/**
 * Implementation of the IFileManager interface
 */
public class FileManager implements IFileManager {

    private Reader reader;
    private Writer writer;
    private IMessageMessenger messageMessenger;

    /**
     * Constructor for FileManager
     * @param reader object of Reader
     * @param writer object of Writer
     * @param messageMessenger message messenger
     */
    public FileManager (Reader reader, Writer writer, IMessageMessenger messageMessenger){
        this.reader = reader;
        this.writer = writer;
        this.messageMessenger = messageMessenger;
    }


    @Override
    public Collection<? extends SpaceMarine> read() throws NoEvnVarException, InvalidFieldException, NoDataException, BrokenDataException, InvalidArgumentTypeException {
          return reader.read(messageMessenger);
    }

    @Override
    public void write(Collection<? extends SpaceMarine> spaceMarines) {
        writer.write(spaceMarines);
    }
}
