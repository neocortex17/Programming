package collectionManager;

import exceptions.*;
import spaceMarine.SpaceMarine;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Stream;

/**
 * A class that implements the ISpaceMarineManager interface
 */
public class SpaceMarineManager implements CollectionManager {
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();
    private LinkedHashSet<SpaceMarine> spaceMarines;
    private final ZonedDateTime zonedDateTime;

    /**
     * Constructor for SpaceMarineManager
     */
    public SpaceMarineManager (){
        spaceMarines = new LinkedHashSet<>();
        zonedDateTime = ZonedDateTime.now();
    }

    @Override
    public Class getType(){
        readLock.lock();
        try {
            return spaceMarines.getClass();
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public int getLength(){
        readLock.lock();
        try {
            return spaceMarines.size();
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public void removeById(Integer id) {
        writeLock.lock();
        try {
            for (SpaceMarine spaceMarine : spaceMarines) {
                if (spaceMarine.getId().equals(id)) {
                    spaceMarines.remove(spaceMarine);
                    return;
                }
            }
        }finally {
            writeLock.unlock();
        }
    }

    @Override
    public void addSpaceMarine(SpaceMarine spaceMarine) {
        readLock.lock();
        try {
            spaceMarines.add(spaceMarine);
        } finally {
            readLock.unlock();
        }
        }

    /*@Override
    public void addSpaceMarine(SpaceMarinesGetter spaceMarinesGetter) {
        Integer id = SpaceMarineIdManager.INSTANCE.getFreeId();
        SpaceMarine spaceMarine = new SpaceMarine(id, spaceMarinesGetter,ZonedDateTime.now());
        SpaceMarineIdManager.INSTANCE.addId(id);
        spaceMarines.add(spaceMarine);
    }*/

    @Override
    public void clear() {
        readLock.lock();
        try {
            spaceMarines.clear();
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public ZonedDateTime getInitDate() {
        readLock.lock();
        try {
            return zonedDateTime;
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public Stream<SpaceMarine> getSpaceMarineStream() {
        readLock.lock();
        try {
            return spaceMarines.stream();
        } finally {
            readLock.unlock();
        }
    }

    public void setSpaceMarines(Collection<SpaceMarine> spaceMarines) {
        writeLock.lock();
        try {
            this.spaceMarines.addAll(spaceMarines);
        } finally {
            writeLock.unlock();
        }
    }

    public void setNewCollection(Collection<SpaceMarine> spaceMarines) {
        writeLock.lock();
        try {
            this.spaceMarines = (LinkedHashSet<SpaceMarine>) spaceMarines;
        } finally {
            writeLock.unlock();
        }
    }


}









