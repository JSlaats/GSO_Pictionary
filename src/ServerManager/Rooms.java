package ServerManager;

import Interfaces.IRooms;

import java.io.Serializable;

public class Rooms implements IRooms,Serializable{
    private String host;
    private String name;
    private String ipAdress;
    private int port;

    public Rooms(String host, String name, String ipAdress, int port) {
        super();
        this.host = host;
        this.name = name;
        this.ipAdress = ipAdress;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
