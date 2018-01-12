package Interfaces;

import java.awt.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IDrawing extends Remote {
    ArrayList<IStroke> getStrokes() throws  RemoteException;
    void setStroke(Point position) throws RemoteException;
    IStroke getLastStroke() throws RemoteException;

    void clear() throws RemoteException;
}
