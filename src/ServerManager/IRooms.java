package ServerManager;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRooms extends Remote {
     String getIpAdress() throws RemoteException;
     int getPort() throws RemoteException;
     String getHost()throws RemoteException;
     String getName()throws RemoteException;
}