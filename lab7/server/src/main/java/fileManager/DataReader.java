package fileManager;

import exceptions.*;
import identification.Identification;
import log.Log;
import spaceMarine.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.*;

/**
 *
 */
public class DataReader implements Reader {
    private final Connection connection;

    public DataReader(Connection connection){
        this.connection = connection;
    }

    @Override
    public Collection<SpaceMarine> readElements() {
         Collection<SpaceMarine> spaceMarines = new LinkedHashSet<>();
         try (Statement statement = connection.createStatement()){
             ResultSet resultSet = statement.executeQuery("select * from spacemarines");
             while (resultSet.next()){
                 spaceMarines.add(createElement(resultSet));
             }
         }catch (SQLException | InvalidFieldException e){
             Log.getLogger().error("Data Base problems");
             e.printStackTrace();
             throw new DBException(e);
         }
         return spaceMarines;
    }

    @Override
    public SpaceMarine getElement(Integer id) {
        try (PreparedStatement statement = connection.prepareStatement("select  * from spaceMarines where id = ?")){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                return createElement(resultSet);
            }else{
                throw new NoSuchIdException();
            }
        }catch (SQLException | InvalidFieldException e){
            e.printStackTrace();
            Log.getLogger().error("Data Base problems");
            throw new DBException(e);
        }
    }

    @Override
    public Set<Identification> readUsers() {
        Set<Identification> users = new HashSet<>();
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("select * from users");
            while (resultSet.next()){
                users.add(new Identification(resultSet.getString("username"), resultSet.getString("password")));
            }
            return users;
        }catch (SQLException e){
            e.printStackTrace();
            Log.getLogger().error("Data Base problems");
            throw new DBException(e);
        }
    }

    @Override
    public SpaceMarine getLastElement() {
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("select  * from spaceMarines where id = (select max(id) from spaceMarines)");
            if (resultSet.next()){
                return  createElement(resultSet);
            }else{
                return null;
            }
        }catch (SQLException | InvalidFieldException e){
            e.printStackTrace();
            Log.getLogger().error("Data Base problems");
            throw new DBException(e);
        }
    }

    private SpaceMarine createElement(ResultSet resultSet) throws SQLException, InvalidFieldException{
        SpaceMarineBuilder spaceMarineBuilder = new SpaceMarineBuilderImpl();
        spaceMarineBuilder.setName(resultSet.getString("name"));
        spaceMarineBuilder.setCoordinatesX(resultSet.getFloat("coordinates_x"));
        spaceMarineBuilder.setCoordinatesY(resultSet.getDouble("coordinates_y"));
        spaceMarineBuilder.setHealth(resultSet.getInt("health"));
        spaceMarineBuilder.setAstartesCategory(AstartesCategory.valueOf(resultSet.getString("category")));
        spaceMarineBuilder.setWeaponType(Weapon.valueOf(resultSet.getString("weapontype")));
        spaceMarineBuilder.setMeleeWeapon(MeleeWeapon.valueOf(resultSet.getString("meleeweapon")));
        spaceMarineBuilder.setChapterName(resultSet.getString("chapterName"));
        spaceMarineBuilder.setChapterWorld(resultSet.getString("chapterWorld"));
        return new SpaceMarine(resultSet.getInt("id"), spaceMarineBuilder.getSpaceMarineGetter(),resultSet.getDate("creationdate").toLocalDate().atStartOfDay(ZoneOffset.UTC));
    }
}
