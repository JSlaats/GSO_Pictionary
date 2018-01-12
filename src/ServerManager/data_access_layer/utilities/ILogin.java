package ServerManager.data_access_layer.utilities;

import Interfaces.IPlayer;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface ILogin extends Remote {
    IPlayer login(String username, String password) throws SQLException, RemoteException;
    boolean register(String username, String password) throws SQLException, RemoteException;
}
