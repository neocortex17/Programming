package fileManager;

import exceptions.*;
import identification.Identification;
import spaceMarine.SpaceMarine;

import java.util.Collection;
import java.util.Set;

/**
 * Data reader class interface
 */
public interface Reader {

    Collection<SpaceMarine> readElements();

    SpaceMarine getElement(Integer id);

    Set<Identification> readUsers();

    SpaceMarine getLastElement();
}
