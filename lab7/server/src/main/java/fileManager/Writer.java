package fileManager;

import identification.Identification;
import spaceMarine.SpaceMarine;
import spaceMarine.SpaceMarinesGetter;

import java.util.Collection;

/**
 * Data Writer Class Interface
 */
public interface Writer {
    void addElement(SpaceMarinesGetter spaceMarine, Identification identification);
    void updateElement(SpaceMarinesGetter spaceMarine, Integer id, Identification identification);
    void clearElements(Identification identification);
    void removeElement(Integer id, Identification identification);
    void addUser(Identification identification);
}
