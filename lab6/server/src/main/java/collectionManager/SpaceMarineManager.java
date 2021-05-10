package collectionManager;

import exceptions.*;
import fileManager.FileManager;
import spaceMarine.SpaceMarine;
import spaceMarine.SpaceMarinesGetter;
import spaceMarine.Weapon;

import java.time.ZonedDateTime;
import java.util.LinkedHashSet;
import java.util.stream.Stream;

/**
 * A class that implements the ISpaceMarineManager interface
 */
public class SpaceMarineManager implements CollectionManager {
    private LinkedHashSet<SpaceMarine> spaceMarines;
    private FileManager fileManager;
    private final ZonedDateTime zonedDateTime;

    /**
     * Constructor for SpaceMarineManager
     * @param fileManager file manager
     */
    public SpaceMarineManager (FileManager fileManager){
        spaceMarines = new LinkedHashSet<>();
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
                SpaceMarineIdManager.INSTANCE.removeId(id);
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
    public void addSpaceMarine(SpaceMarinesGetter spaceMarinesGetter) {
        Integer id = SpaceMarineIdManager.INSTANCE.getFreeId();
        SpaceMarine spaceMarine = new SpaceMarine(id, spaceMarinesGetter,ZonedDateTime.now());
        SpaceMarineIdManager.INSTANCE.addId(id);
        spaceMarines.add(spaceMarine);
    }

    @Override
    public void clear() {
        SpaceMarineIdManager.INSTANCE.clearAllOccupiedId();
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
        spaceMarines.addAll(fileManager.read());
        for (SpaceMarine spaceMarine: spaceMarines){
            Integer id = spaceMarine.getId();
            if (SpaceMarineIdManager.INSTANCE.idIsFree(id)){
                SpaceMarineIdManager.INSTANCE.addId(id);
            } else {
                spaceMarines.clear();
                SpaceMarineIdManager.INSTANCE.clearAllOccupiedId();
                return;
            }
        }
    }

    @Override
    public Stream<SpaceMarine> getSpaceMarineStream() {
        return spaceMarines.stream();
    }

    public void setSpaceMarines(LinkedHashSet<SpaceMarine> hashSet) {
        spaceMarines = hashSet;
    }


}









