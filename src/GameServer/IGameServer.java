package GameServer;

import ClientGUI.GameClient;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IGameServer extends Remote {

    void register(GameClient client) throws RemoteException;

    void unregister(GameClient client) throws RemoteException;

    void doSomethingUseful() throws RemoteException;
    
}