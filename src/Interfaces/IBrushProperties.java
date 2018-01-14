package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IBrushProperties extends Remote {
    /**
     * Krijg de width van de brush
     * @return int tussen 1 en 25 van de width van de brush
     * @throws RemoteException
     */
    int getWidth() throws RemoteException;

    /**
     * Set de width van de brush
     * @param width int tussen de 1 en 25 van de width van de brush
     * @throws RemoteException
     */
    void setWidth(int width)throws RemoteException;

    /**
     * Krijg een int van de kleur rood
     * @return int tussen de 0 en 255
     * @throws RemoteException
     */
    int getR() throws RemoteException;

    /**
     * Krijg een int van de kleur groen
     * @return int tussen de 0 en 255
     * @throws RemoteException
     */
    int getG()throws RemoteException;

    /**
     * Krijg een int van de kleur blauw
     * @return int tussen de 0 en 255
     * @throws RemoteException
     */
    int getB()throws RemoteException;

    /**
     * Set de kleur van de brush aan de hand van RGB waarden
     * @param red int tussen de 0 en 255 voor de kleur rood
     * @param green int tussen de 0 en 255 voor de kleur groen
     * @param blue int tussen de 0 en 255 voor de kleur blauw
     * @throws RemoteException
     */
    void setColor(int red, int green, int blue)throws RemoteException;

}
