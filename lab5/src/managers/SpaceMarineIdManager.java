package managers;

import outputConsole.IConsoleOutputManager;

import java.util.Set;
import java.util.TreeSet;

/**
 * A class that implements the ISpaceMarineIdManager interface
 */
public class SpaceMarineIdManager implements ISpaceMarineIdManager {
    private Set<Integer> usedId;
    private static SpaceMarineIdManager instance;

    /**
     * @return object of SpaceMarineIdManager
     */
    public synchronized static SpaceMarineIdManager getInstance() {
        if (instance == null) {
            instance = new SpaceMarineIdManager();
        }
        return instance;
    }

    private SpaceMarineIdManager() {
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

    public void groupByIdModulo3(IConsoleOutputManager consoleOutputManager) {
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
        consoleOutputManager.printMess("Number of id with remainder of division by 3 equal to 0: " + remainder0 + "\n" +
                "Number of id with remainder of division by 3 equal to 1: " + remainder1 + "\n" +
                "Number of id with remainder of division by 3 equal to 2: " + remainder2 + "\n");
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
