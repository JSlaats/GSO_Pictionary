package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IPlayer extends Remote {
    String getName() throws RemoteException;
    int getScore() throws RemoteException;
    void setScore(int score) throws RemoteException;
}
