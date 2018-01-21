package GameServer;

import domain.Player;
import domain.Room;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class GameServer {
    private static int portNumber = 1099;
    private static String host = null;
    private static final String bindingName = "room";

    private static GameServer SERVER_INSTANCE = null;
    private Room room;

    public Room getRoom(){
        return room;
    }

    public static GameServer getInstance(){
        if(SERVER_INSTANCE == null){
            SERVER_INSTANCE = new GameServer();
        }
        return SERVER_INSTANCE;
    }

    private GameServer() {
        System.out.println("Server: Port number "+portNumber);

        room = null;
        try {
            room = new Room(new Player(host));
            System.out.println("Host: "+room.getHost().getName());
            System.out.println("Server: room created");
        } catch (RemoteException var4) {
            System.out.println("Server: Cannot create Room");
            System.out.println("Server: RemoteException: " + var4.getMessage());
            room = null;
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
            if (registry != null) {
                registry.rebind(bindingName, room);
            }
        } catch (RemoteException var2) {
            System.out.println("Server: Cannot bind "+bindingName);
            System.out.println("Server: RemoteException: " + var2.getMessage());
        }

    }

    public static void main(String[] args) {
        System.out.println("STARTING GAMESERVER");

        for(String str : args){
            System.out.println(str);
        }

        portNumber = Integer.parseInt(args[0]);
        host = args[1];
        SERVER_INSTANCE = new GameServer();
        System.out.println("GAMESERVER RUNNING");
    }
}
