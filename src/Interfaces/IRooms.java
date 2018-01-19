package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRooms extends Remote {
     /**
      * Geeft het IP adress van de room
      * @return String van het IP adress waarin de room wordt gehost
      * @throws RemoteException
      */
     String getIpAdress() throws RemoteException;

     /**
      * Geeft de port van de room
      * @return int van de port waarin de room wordt gehost
      * @throws RemoteException
      */
     int getPort() throws RemoteException;

     /**
      * Geeft de naam van de room
      * @return String van de room naam
      * @throws RemoteException
      */
     String getName()throws RemoteException;

     /**
      * Geeft de naam van de host
      * @return String van de room host
      * @throws RemoteException
      */
     String getHost()throws RemoteException;
}