package ClientGUI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientCallbackInterface extends Remote {

    void ping() throws RemoteException;

    void notifyChanges() throws RemoteException;

}

