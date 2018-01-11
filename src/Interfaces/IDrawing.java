package Interfaces;

import domain.Stroke;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IDrawing extends Remote {
    ArrayList<Stroke> getStrokes() throws  RemoteException;
    void setStrokes(ArrayList<Stroke> strokes) throws RemoteException;
    void setStroke(Stroke stroke) throws RemoteException;
    void clear() throws RemoteException;
}
