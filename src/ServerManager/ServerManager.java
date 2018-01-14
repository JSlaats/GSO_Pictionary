package ServerManager;

import GameServer.IGameServer;
import javafx.collections.FXCollections;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class ServerManager{
    private static final int portNumber = 1098;
    private static final String bindingName = "roomlist";

    private RoomsList roomsList;
    private Login login;
    private ServerManager() {
        System.out.println("Server: Port number "+portNumber);

        roomsList = null;
        try {
            roomsList = new RoomsList();
            login = new Login();

            System.out.println("Server: roomList created");
            System.out.println("Server: login created");

        } catch (RemoteException var4) {
            System.out.println("Server: Cannot create RoomList");
            System.out.println("Server: RemoteException: " + var4.getMessage());
            roomsList = null;
        }

        Registry registry = null;
        try {
            registry = LocateRegistry.createRegistry(portNumber);
            System.out.println("Server: Registry created on port number "+portNumber);
        } catch (RemoteException var3) {
            System.out.println("Server: Cannot create registry");
            System.out.println("Server: RemoteException: " + var3.getMessage());
            registry = null;
        }

        try {
            registry.rebind(bindingName, (Remote) roomsList);
            registry.rebind("login",(Remote)login);
        } catch (RemoteException var2) {
            System.out.println("Server: Cannot bind "+bindingName);
            System.out.println("Server: RemoteException: " + var2.getMessage());
        }
        try {
            registry.rebind("login",(Remote)login);
        } catch (RemoteException var2) {
            System.out.println("Server: Cannot bind "+login);
            System.out.println("Server: RemoteException: " + var2.getMessage());
        }

    }

    public static void main(String[] args) {
        System.out.println("ServerManager USING REGISTRY");
        new ServerManager();
    }
}
