package collectionManager;

import java.util.Set;
import java.util.TreeSet;

/**
 * A class that implements the ISpaceMarineIdManager interface
 */
public enum SpaceMarineIdManager implements IdManager {
    INSTANCE;
    private Set<Integer> usedId;

    private SpaceMarineIdManager(){
        usedId = new TreeSet<>();
    }

    @Override
    public boolean idIsFree(Integer id) {
        return !usedId.contains(id);
    }

    @Override
    public void addId(Integer id) {
        usedId.add(id);
    }

    @Override
    public void removeId(Integer id) {
        usedId.remove(id);
    }

    @Override
    public void clearAllOccupiedId() {
        usedId.clear();
    }

    public void groupByIdModulo3() {
        int remainder0 = 0;
        int remainder1 = 0;
        int remainder2 = 0;
            for (Integer id : this.usedId) {
                if (id % 3 == 0) {
                    remainder0++;
                }
                if (id % 3 == 1) {
                    remainder1++;
                }
                if (id % 3 == 2) {
                    remainder2++;
                }
            }
        }
    public Integer getFreeId() {
        Integer newId = 0;
        for (Integer id : usedId) {
            if (id - newId != 1) {
                break;
            }
            newId = id;
        }
        return newId + 1;
    }

}
