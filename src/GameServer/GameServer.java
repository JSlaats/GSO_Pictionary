package GameServer;

import domain.Player;
import domain.Room;

import java.net.InetAddress;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class GameServer {
    private static final int portNumber = 1099;
    private static final String bindingName = "room";


    public GameServer() {
        System.out.println("Server: Port number 1099");

        Room room = null;
        try {
            room = new Room(new Player("JelleRMI"));
            room.getHost().setScore(1337);
            room.addPlayer(new Player("Henk"));
            room.addPlayer(new Player("Piet"));
            room.addPlayer(new Player("Frank"));

            System.out.println("Server: room created");
        } catch (RemoteException var4) {
            System.out.println("Server: Cannot create Room");
            System.out.println("Server: RemoteException: " + var4.getMessage());
            room = null;
        }

        Registry registry = null;
        try {
            registry = LocateRegistry.createRegistry(1099);
            System.out.println("Server: Registry created on port number 1099");
        } catch (RemoteException var3) {
            System.out.println("Server: Cannot create registry");
            System.out.println("Server: RemoteException: " + var3.getMessage());
            registry = null;
        }

        try {
            registry.rebind("room", (Remote) room);
        } catch (RemoteException var2) {
            System.out.println("Server: Cannot bind room");
            System.out.println("Server: RemoteException: " + var2.getMessage());
        }

        try {
            printIPAddresses();
        }
        catch (java.net.UnknownHostException uhe)
        {
            System.out.print(uhe.getMessage());
        }
    }

    private static void printIPAddresses() throws java.net.UnknownHostException {
        InetAddress ex = InetAddress.getLocalHost();
        System.out.println("Server: IP Address: " + ex.getHostAddress());
        InetAddress[] intf = InetAddress.getAllByName(ex.getCanonicalHostName());
        if(intf != null && intf.length > 1) {
            System.out.println("Server: Full list of IP addresses:");
            InetAddress[] enumIpAddr = intf;
            int var3 = intf.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                InetAddress allMyIp = enumIpAddr[var4];
                System.out.println("    " + allMyIp);
            }
        }
    }
    public static void main(String[] args) throws java.net.UnknownHostException {
        System.out.println("SERVER USING REGISTRY");
        printIPAddresses();
        new GameServer();
    }
}
