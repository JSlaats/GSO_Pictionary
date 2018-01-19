package ServerManager;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerManager{
    private static final int portNumber = 1098;

    private Login login;

    private ServerManager() {
        System.out.println("Server: Port number "+portNumber);

        RoomsList roomsList;
        try {
            roomsList = new RoomsList();
            login = new Login();

            System.out.println("Server: roomsList created");
            System.out.println("Server: login created");

        } catch (RemoteException var4) {
            System.out.println("Server: Cannot create RoomsList or Login");
            System.out.println("Server: RemoteException: " + var4.getMessage());
            roomsList = null;
        }

        Registry registry;
        try {
            registry = LocateRegistry.createRegistry(portNumber);
            System.out.println("Server: Registry created on port number "+portNumber);
        } catch (RemoteException var3) {
            System.out.println("Server: Cannot create registry");
            System.out.println("Server: RemoteException: " + var3.getMessage());
            registry = null;
        }

        try {
            assert registry != null;
            registry.rebind("roomslist", (Remote) roomsList);
        } catch (RemoteException var2) {
            System.out.println("Server: Cannot bind 'roomslist'");
            System.out.println("Server: RemoteException: " + var2.getMessage());
        }
        try {
            registry.rebind("login",(Remote)login);
        } catch (RemoteException var2) {
            System.out.println("Server: Cannot bind 'login'");
            System.out.println("Server: RemoteException: " + var2.getMessage());
        }

    }

    public static void main(String[] args) {
        System.out.println("ServerManager USING REGISTRY");
        new ServerManager();
    }
}
