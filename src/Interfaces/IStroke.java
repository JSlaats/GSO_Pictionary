package Interfaces;

import java.awt.*;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IStroke extends Remote {
    /**
     * Geeft de positie van de Stroke
     * @return Point met de positie van de stroke
     * @throws RemoteException
     */
    Point getPosition() throws RemoteException;

    /**
     * Geeft een instantie van IBrushProperties van de Stroke
     * @return IBrushProperties met de Width en Color van de stroke
     * @throws RemoteException
     */
    IBrushProperties getBrush() throws RemoteException;
}
