package Interfaces;

import domain.Player;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IRoom extends Remote {
    IPlayer getHost() throws RemoteException;
    IDrawing getDrawing() throws RemoteException;

    ArrayList<Player> getPlayers() throws RemoteException;
    boolean guessWord(String guess) throws RemoteException;
    IActivePlayer getActivePlayer() throws RemoteException;
}

