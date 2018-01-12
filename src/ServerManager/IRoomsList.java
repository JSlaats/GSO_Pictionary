package ServerManager;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IRoomsList extends Remote {
    ArrayList<IRooms> getRoomsList() throws RemoteException;

    void add(String host, String name, String ipAdress, int port) throws RemoteException;
}
