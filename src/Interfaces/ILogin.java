package Interfaces;

import Interfaces.IPlayer;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface ILogin extends Remote {
    /**
     * Inloggen met username en password.
     * @param username username om mee in te loggen
     * @param password password om mee in te loggen
     * @return  Referentie naar IPlayer
     * @throws SQLException
     * @throws RemoteException
     */
    IPlayer login(String username, String password) throws SQLException, RemoteException;

    /**
     * Een account aanmaken.
     * @param username username om mee te registreren
     * @param password password om mee te registreren
     * @return True als het account bestaat, anders false
     * @throws SQLException
     * @throws RemoteException
     */
    boolean register(String username, String password) throws SQLException, RemoteException;
}
