package ident;

import identification.Identification;

import java.util.Collection;

public interface IdentManager {
    boolean checkIdent(Identification identification);
    boolean isOnline(Identification identification);
    void setUsers(Collection<Identification> users);
    void addOnlineUser(Identification identification);
    void addUser(Identification identification);
    void removeOnlineUser(Identification identification);
}
