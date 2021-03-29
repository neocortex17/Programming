package managers;

import exceptions.*;
import logging.Log;
import spaceMarine.*;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.logging.Level;
import java.util.stream.Stream;

/**
 * A class that implements the ISpaceMarineManager interface
 */
public class SpaceMarineManager implements ISpaceMarineManager{
    private LinkedHashSet<SpaceMarine> spaceMarines;
    private IFileManager fileManager;
    private ZonedDateTime zonedDateTime;

    /**
     * Constructor for SpaceMarineManager
     * @param fileManager file manager
     */
    public SpaceMarineManager (IFileManager fileManager){
        Log.logger.log(Level.INFO,"SpaceMarineManager init");
        spaceMarines = new LinkedHashSet<SpaceMarine>();
        zonedDateTime = ZonedDateTime.now();
        this.fileManager = fileManager;
    }

    @Override
    public Class getType(){
        return spaceMarines.getClass();
    }

    @Override
    public int getLength(){
       return spaceMarines.size();
    }

    @Override
    public boolean removeById(Integer id) {
       for (SpaceMarine spaceMarine: spaceMarines){
            if (spaceMarine.getId().equals(id)){
                SpaceMarineIdManager.getInstance().removeId(id);
                spaceMarines.remove(spaceMarine);
                return true;
            }
        }
        return false;
    }

    @Override
    public void addSpaceMarine(SpaceMarine spaceMarine) {
        spaceMarines.add(spaceMarine);
        }

    @Override
    public void clear() {
        SpaceMarineIdManager.getInstance().clearAllOccupiedId();
        spaceMarines.clear();
    }

    @Override
    public void saveSpaceMarines() {
        fileManager.write(spaceMarines);
    }

    @Override
    public ZonedDateTime getInitDate() {
        return zonedDateTime;
    }

    @Override
    public void addSpaceMarines() throws NoEvnVarException, NoDataException, BrokenDataException, InvalidFieldException, InvalidArgumentTypeException {
        Log.logger.log(Level.INFO,"Adding space marines into LinkedHashSet");
        spaceMarines.addAll(fileManager.read());
    }

    @Override
    public Stream<SpaceMarine> getSpaceMarineStream() {
        return spaceMarines.stream();
    }

    @Override
    public int countByWeaponType (Weapon weapon){
            int count = 0;
            for (SpaceMarine spaceMarine: spaceMarines){
                if (spaceMarine.getWeaponType() == weapon){
                    count++;
                }
            }
            return count;

    }
}









