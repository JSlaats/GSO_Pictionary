package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IBrushProperties extends Remote {
    int getWidth() throws RemoteException;

    void setWidth(int width)throws RemoteException;

    int getR() throws RemoteException;

    int getG()throws RemoteException;

    int getB()throws RemoteException;

    void setColor(int r, int g, int b)throws RemoteException;

}
