package Interfaces;

import domain.BrushProperties;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IActivePlayer extends Remote {
    BrushProperties getBrush() throws RemoteException;
    String getWord() throws RemoteException;
}
