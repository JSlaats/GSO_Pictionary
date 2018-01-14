package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IPlayer extends Remote {
    /**
     * Krijg de naam van de speler
     * @return String met de naam van de speler
     * @throws RemoteException
     */
    String getName() throws RemoteException;

    /**
     * Krijg de score van de speler
     * @return int met de score van de speler
     * @throws RemoteException
     */
    int getScore() throws RemoteException;

    /**
     * Verhoog de score van de speler
     * @param score int waarmee de score verhoogd moet worden
     * @throws RemoteException
     */
    void increaseScore(int score) throws RemoteException;
}
