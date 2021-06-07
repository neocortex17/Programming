package fileManager;

import exceptions.DBException;
import exceptions.NoSuchIdException;
import identification.Identification;
import log.Log;
import spaceMarine.SpaceMarine;
import spaceMarine.SpaceMarinesGetter;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Locale;

/**
 * A class that implements the Writer interface
 */
public class DataWriter implements Writer {
    private final Connection connection;

    public DataWriter(Connection connection){
        this.connection = connection;
    }

    @Override
    public void addElement(SpaceMarinesGetter spaceMarine, Identification identification) {
        try (PreparedStatement prepareStatement = connection.prepareStatement(
                "insert into spaceMarines (id, name, coordinates_x, coordinates_y, health, category, weapontype, " +
                        "meleeweapon, chapterName, chapterWorld, creationdate, owner) values " +
                        "(nextval('id_seq'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")){
            setSpaceMarinesToStatement(spaceMarine, prepareStatement);
            prepareStatement.setTimestamp(10, new Timestamp(ZonedDateTime.now().toInstant().getEpochSecond() * 1000L));
            prepareStatement.setString(11, identification.getLogin());
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException(e);
        }
    }

    @Override
    public void updateElement(SpaceMarinesGetter spaceMarine, Integer id, Identification identification) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("update spacemarines SET " +
                "name = ?,"+
                "coordinates_x = ?," +
                "coordinates_y = ?," +
                "health = ?," +
                "category = ?," +
                "weapontype = ?," +
                "meleeweapon = ?," +
                "chapterName = ?," +
                "chapterWorld = ?" +
                "where id = ? and owner = ?")){
            setSpaceMarinesToStatement(spaceMarine, preparedStatement);
            preparedStatement.setInt(10, id);
            preparedStatement.setString(11, identification.getLogin());
            if (preparedStatement.executeUpdate() < 1){
                throw new NoSuchIdException();
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new DBException(e);
        }
    }

    @Override
    public void clearElements(Identification identification) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("delete from spaceMarines where owner = ?")){
            preparedStatement.setString(1,identification.getLogin());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new DBException(e);
        }
    }

    @Override
    public void removeElement(Integer id, Identification identification) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("delete from spaceMarines where id = ? and owner = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, identification.getLogin());
            if (preparedStatement.executeUpdate() < 1) {
                throw new NoSuchIdException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException(e);
        }
    }

    @Override
    public void addUser(Identification identification) {
        try(PreparedStatement preparedStatement = connection.prepareStatement("insert into users (username, password) values (?, ?)")){
            preparedStatement.setString(1,identification.getLogin());
            preparedStatement.setString(2,identification.getPassword());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new DBException();
        }
    }

    private void setSpaceMarinesToStatement (SpaceMarinesGetter spaceMarine, PreparedStatement preparedStatement) throws SQLException{
        preparedStatement.setString(1,spaceMarine.getName());
        preparedStatement.setFloat(2, spaceMarine.getCoordinates().getX());
        preparedStatement.setDouble(3, spaceMarine.getCoordinates().getY());
        preparedStatement.setInt(4, spaceMarine.getHealth());
        preparedStatement.setString(5, spaceMarine.getCategory().toString());
        preparedStatement.setString(6, spaceMarine.getWeaponType().toString());
        preparedStatement.setString(7, spaceMarine.getMeleeWeapon().toString());
        preparedStatement.setString(8, spaceMarine.getChapter().getName());
        if (spaceMarine.getChapter().getWorld() == null){
            preparedStatement.setNull(9, Types.VARCHAR);
        }else{
            preparedStatement.setString(9, spaceMarine.getChapter().getWorld());
        }
    }
}
