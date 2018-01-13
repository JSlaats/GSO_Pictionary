package ClientGUI;

import Interfaces.IPlayer;
import ServerManager.IRoomsList;
import ServerManager.data_access_layer.utilities.ILogin;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ManagerClient {
    private static String ipAddress = "127.0.0.1";
    private static int portNumber = 1098;
    private static final String bindingName = "roomlist";

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

/*        if(this.registry != null) {
            this.printContentsRegistry();
        }*/

        if(this.registry != null) {
            try {
                login = (ILogin) this.registry.lookup("login");
                roomsList = (IRoomsList) this.registry.lookup(bindingName);
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

/*        if(roomsList != null) {

            try {
                this.testGetRoom();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }*/

    }

   /* private void testGetRoom() throws RemoteException {
        roomsList.getRoomsList().forEach(rooms -> {
            try {
                System.out.println(rooms.getHost());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }

    private void printContentsRegistry() {
        try {
            String[] ex = this.registry.list();
            System.out.println("Client: list of names bound in registry:");
            if(ex.length != 0) {
                String[] var2 = this.registry.list();
                int var3 = var2.length;

                for(int var4 = 0; var4 < var3; ++var4) {
                    String s = var2[var4];
                    System.out.println(s);
                }
            } else {
                System.out.println("Client: list of names bound in registry is empty");
            }
        } catch (RemoteException var6) {
            System.out.println("Client: Cannot show list of names bound in registry");
            System.out.println("Client: RemoteException: " + var6.getMessage());
        }

    }*/
}
