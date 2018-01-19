package ClientGUI;

import Interfaces.IPlayer;
import Interfaces.IRoomsList;
import Interfaces.ILogin;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ManagerClient {
    private static String ipAddress = "127.0.0.1";
    private static int portNumber = 1098;
    private static final String bindingName = "roomslist";

    private static ManagerClient CLIENT_INSTANCE = new ManagerClient(ipAddress,portNumber);
    private Registry registry;
    private IRoomsList roomsList;
    private ILogin login;
    private IPlayer localPlayer;

    public ILogin getLogin(){return login;}

    public IPlayer getLocalPlayer() {
        return localPlayer;
    }

    public void setLocalPlayer(IPlayer localPlayer) {
        this.localPlayer = localPlayer;
    }

    public IRoomsList getRoomsList(){
        return roomsList;
    }

    public static ManagerClient getInstance(){
        if(CLIENT_INSTANCE == null){
            CLIENT_INSTANCE = new ManagerClient(ipAddress,portNumber);
        }
        return CLIENT_INSTANCE;
    }



    private ManagerClient(String ipAddress, int portNumber) {
        System.out.println("Client: IP Address: " + ipAddress);
        System.out.println("Client: Port number " + portNumber);

        try {
            this.registry = LocateRegistry.getRegistry(ipAddress, portNumber);
        } catch (RemoteException var6) {
            System.out.println("Client: Cannot locate registry");
            System.out.println("Client: RemoteException: " + var6.getMessage());
            this.registry = null;
        }

        if(this.registry != null) {
            System.out.println("Client: Registry located");
        } else {
            System.out.println("Client: Cannot locate registry");
            System.out.println("Client: Registry is null pointer");
        }

        if(this.registry != null) {
            try {
                login = (ILogin) this.registry.lookup("login");
                roomsList = (IRoomsList)this.registry.lookup("roomslist");
            } catch (RemoteException var4) {
                System.out.println("Client: Cannot bind "+bindingName);
                System.out.println("Client: RemoteException: " + var4.getMessage());
                roomsList = null;
            } catch (NotBoundException var5) {
                System.out.println("Client: Cannot bind "+bindingName);
                System.out.println("Client: NotBoundException: " + var5.getMessage());
                roomsList = null;
            }
        }
        if(roomsList != null) {
            System.out.println("Client: "+bindingName+" bound");
        } else {
            System.out.println("Client: "+bindingName+" is null pointer");
        }
        printContentsRegistry();
    }
    private void printContentsRegistry() {
        try {
            String[] names = registry.list();
            System.out.println("list of names bound in registry:");
            if (names.length != 0) {
                for (int i = 0; i < names.length; ++i) {
                    System.out.println(names[i]);
                }
            } else {
                System.out.println("list of names bound in registry is empty.");
            }
        } catch (RemoteException e) {
            System.out.println("Cannot show list of names bound in registry.");
            System.out.println("RemoteException: " + e.getMessage());
        }

    }

}
