package managers;

import spaceMarine.SpaceMarine;

import java.util.Collection;

/**
 * Data Writer Class Interface
 */
public interface Writer {
    /**
     * The method that writes the collection to a file
     * @param spaceMarines collection
     */
    void write(Collection<? extends SpaceMarine> spaceMarines);
}
