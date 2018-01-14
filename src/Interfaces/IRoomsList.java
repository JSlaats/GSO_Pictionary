package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IRoomsList extends Remote {
    /**
     * De lijst met de rooms die op dit moment draaien
     * @return Lijst van IRooms
     * @throws RemoteException
     */
    ArrayList<IRooms> getRoomsList() throws RemoteException;

    /**
     * Voeg een nieuwe room toe
     * @param host String van de host naam
     * @param name String van de room naam
     * @param ipAdress String van het IP adress
     * @param port int van de port waarop de room gehost wordt
     * @throws RemoteException
     */
    void add(String host, String name, String ipAdress, int port) throws RemoteException;
}
