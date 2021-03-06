package ServerManager;

import Interfaces.IRooms;
import Interfaces.IRoomsList;
import fontyspublisher.IRemotePropertyListener;
import fontyspublisher.IRemotePublisherForListener;
import fontyspublisher.RemotePublisher;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class RoomsList extends UnicastRemoteObject implements IRoomsList, IRemotePublisherForListener {
    private ArrayList<IRooms> roomsList;
    private RemotePublisher publisher;

    public RoomsList() throws RemoteException {
        super();
        roomsList = new ArrayList<>();
        try {
            publisher = new RemotePublisher();
        } catch (RemoteException e) {
            System.out.println("Publisher failed to instantiate.");
            System.out.println("Remote exception: " + e.getMessage());
            return;
        }

        publisher.registerProperty("list");
    }

    public ArrayList<IRooms> getRoomsList() {
        publisher.inform("list", null, roomsList);
        return roomsList;
    }

    public void add(String host, String name, String ipAdress, int port){
        roomsList.add(new Rooms(host,name,ipAdress,port));
        publisher.inform("list", null, roomsList);
    }

    @Override
    public void subscribeRemoteListener(IRemotePropertyListener listener, String property) {
        publisher.subscribeRemoteListener(listener, property);
    }

    @Override
    public void unsubscribeRemoteListener(IRemotePropertyListener listener, String property) {
        publisher.unsubscribeRemoteListener(listener, property);
    }
}
