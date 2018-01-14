package Interfaces;

import java.awt.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IDrawing extends Remote {
    /**
     * Geeft een lijst van alle IStrokes in een Drawing
     * @return Lijst van alle IStrokes in de drawing
     * @throws RemoteException
     */
    ArrayList<IStroke> getStrokes() throws  RemoteException;

    /**
     * Voeg een nieuwe Stroke toe aan de Drawing aan de hand van de positie
     * @param position Point met de positie waar de stroke moet staan op het canvas
     * @throws RemoteException
     */
    void setStroke(Point position) throws RemoteException;

    /**
     * Geeft de laatste IStroke in de drawing
     * @return Laaste IStroke die aan de drawing is toegevoegd
     * @throws RemoteException
     */
    IStroke getLastStroke() throws RemoteException;

    /**
     * Maak de drawing leeg door alle strokes te verwijderen
     * @throws RemoteException
     */
    void clear() throws RemoteException;
}
