package fileManager;

import exceptions.*;
import spaceMarine.SpaceMarine;

import java.util.Collection;

/**
 * Implementation of the IFileManager interface
 */
public class FileManagerImpl implements FileManager {
    private Reader reader;
    private Writer writer;

    /**
     * Constructor for FileManager
     * @param reader object of Reader
     * @param writer object of Writer
     */
    public FileManagerImpl(Reader reader, Writer writer){
        this.reader = reader;
        this.writer = writer;
    }


    @Override
    public Collection<? extends SpaceMarine> read() throws NoEvnVarException, InvalidFieldException, NoDataException, BrokenDataException, InvalidArgumentTypeException {
          return reader.read();
    }

    @Override
    public void write(Collection<? extends SpaceMarine> spaceMarines) {
        writer.write(spaceMarines);
    }
}
