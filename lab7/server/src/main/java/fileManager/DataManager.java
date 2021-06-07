package fileManager;


import exceptions.*;
import identification.Identification;
import spaceMarine.SpaceMarine;
import spaceMarine.SpaceMarinesGetter;

import java.util.Collection;
import java.util.Set;

public interface DataManager {

    Collection<SpaceMarine> readElements();

    SpaceMarine addElement(SpaceMarinesGetter spaceMarinesGetter, Identification identification);

    SpaceMarine updateElement(SpaceMarinesGetter spaceMarinesGetter, Integer id, Identification identification);

    void clearElements(Identification identification);

    void removeElement(Integer id, Identification identification);

    void addUser(Identification identification);

    Set<Identification> readUsers();
}
