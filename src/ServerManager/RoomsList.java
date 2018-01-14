package ServerManager;

import Interfaces.IRooms;
import Interfaces.IRoomsList;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class RoomsList extends UnicastRemoteObject implements IRoomsList {
    private ArrayList<IRooms> roomsList;

    public RoomsList() throws RemoteException {
        super();
        roomsList = new ArrayList<>();
    }

    public ArrayList<IRooms> getRoomsList() {
        return roomsList;
    }
    public void add(String host, String name, String ipAdress, int port){
        roomsList.add((IRooms)new Rooms(host,name,ipAdress,port));
    }
}
