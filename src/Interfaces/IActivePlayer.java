package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IActivePlayer extends Remote {
    IBrushProperties getBrush() throws RemoteException;
    void setBrush(IBrushProperties brush) throws RemoteException;
    String getWord() throws RemoteException;
    void setWord(String word) throws RemoteException;
    void setBrushWidth(int width) throws RemoteException;
    void setBrushColor(int r, int g, int b) throws RemoteException;
}
