package fileManager;

import identification.Identification;
import spaceMarine.SpaceMarine;
import spaceMarine.SpaceMarinesGetter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Set;

/**
 * Implementation of the IFileManager interface
 */
public class DataManagerImpl implements DataManager {
    private Reader reader;
    private Writer writer;

    public DataManagerImpl(String url, String user, String password) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        this.reader = new DataReader(connection);
        this.writer = new DataWriter(connection);
    }

    @Override
    public Collection<SpaceMarine> readElements() {
        return reader.readElements();
    }

    @Override
    public SpaceMarine addElement(SpaceMarinesGetter spaceMarinesGetter, Identification identification) {
        writer.addElement(spaceMarinesGetter, identification);
        return reader.getLastElement();
    }

    @Override
    public SpaceMarine updateElement(SpaceMarinesGetter spaceMarinesGetter, Integer id, Identification identification) {
        writer.updateElement(spaceMarinesGetter, id, identification);
        return reader.getLastElement();
    }

    @Override
    public void clearElements(Identification identification) {
        writer.clearElements(identification);
    }

    @Override
    public void removeElement(Integer id, Identification identification) {
        writer.removeElement(id, identification);
    }

    @Override
    public void addUser(Identification identification) {
        writer.addUser(identification);
    }

    @Override
    public Set<Identification> readUsers() {
        return reader.readUsers();
    }
}

