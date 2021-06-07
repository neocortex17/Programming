package ident;

import identification.Identification;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class IdentManagerImpl implements IdentManager{
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();
    private final Set<Identification> onlineUsers = new HashSet<>();
    private final Set<Identification> users = new HashSet<>();

    @Override
    public boolean checkIdent(Identification identification) {
        readLock.lock();
        try {
            return users.contains(identification);
        }finally {
            readLock.unlock();
        }
    }

    @Override
    public boolean isOnline(Identification identification) {
        readLock.lock();
        try {
            return onlineUsers.contains(identification);
        }finally {
            readLock.unlock();
        }
    }

    @Override
    public void setUsers(Collection<Identification> users) {
        writeLock.lock();
        try {
            this.users.addAll(users);
        }finally {
            writeLock.unlock();
        }
    }

    @Override
    public void addOnlineUser(Identification identification) {
        writeLock.lock();
        try {
            onlineUsers.add(identification);
        }finally {
            writeLock.unlock();
        }
    }

    @Override
    public void addUser(Identification identification) {
        writeLock.lock();
        try {
            users.add(identification);
        }finally {
            writeLock.unlock();
        }
    }

    @Override
    public void removeOnlineUser(Identification identification) {
        writeLock.lock();
        try {
            onlineUsers.remove(identification);
        }finally {
            writeLock.unlock();
        }
    }
}
