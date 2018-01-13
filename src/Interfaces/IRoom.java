package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IRoom extends Remote {
    IPlayer getHost() throws RemoteException;
    IDrawing getDrawing() throws RemoteException;
    IChat getChat() throws RemoteException;
    ArrayList<IPlayer> getPlayers() throws RemoteException;
    void addPlayer(IPlayer player) throws RemoteException;
    boolean guessWord(String guess) throws RemoteException;
    IActivePlayer getActivePlayer() throws RemoteException;
}

