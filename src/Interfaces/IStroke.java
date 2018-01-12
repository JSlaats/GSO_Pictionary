package Interfaces;

import java.awt.*;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IStroke extends Remote {
    Point getPosition() throws RemoteException;

    IBrushProperties getBrush() throws RemoteException;
}
