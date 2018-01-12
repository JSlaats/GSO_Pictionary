package ClientGUI;

import Interfaces.IPlayer;
import Interfaces.IRoom;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class GameClient {
    private static String ipAddress = "127.0.0.1";
    private static int portNumber = 1099;
    private static GameClient CLIENT_INSTANCE = new GameClient(ipAddress,portNumber);
    private Registry registry;
    private IRoom room;
    private GameScreenController controller;

    public IRoom getRoom(){
        return room;
    }

    public static GameClient getInstance(){
        if(CLIENT_INSTANCE == null){
            CLIENT_INSTANCE = new GameClient(ipAddress,portNumber);
        }
        return CLIENT_INSTANCE;
    }

    public void setGameScreenController(GameScreenController controller){
        this.controller = controller;
    }
    
    private GameClient(String ipAddress, int portNumber) {
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
            this.printContentsRegistry();
        }

        if(this.registry != null) {
            try {
                room = (IRoom)this.registry.lookup("room");
            } catch (RemoteException var4) {
                System.out.println("Client: Cannot bind room");
                System.out.println("Client: RemoteException: " + var4.getMessage());
                room = null;
            } catch (NotBoundException var5) {
                System.out.println("Client: Cannot bind room");
                System.out.println("Client: NotBoundException: " + var5.getMessage());
                room = null;
            }
        }

        if(room != null) {
            System.out.println("Client: room bound");
        } else {
            System.out.println("Client: room is null pointer");
        }

        if(room != null) {

            try {
                this.testGetRoom();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

    }

    private void testGetRoom() throws RemoteException {
        System.out.println("Client: Host is " + room.getHost().getName()+" score:"+room.getHost().getScore());
        /*System.out.println(room.getDrawing());
        for (IPlayer p:room.getPlayers()) {
            System.out.println(p.getName());
        }*/
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

    }
}
