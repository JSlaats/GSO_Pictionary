package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IActivePlayer extends Remote {
    /**
     * Krijg een instantie van IBrushProperties
     * @return IBrushProperties
     * @throws RemoteException
     */
    IBrushProperties getBrush() throws RemoteException;

    /**
     * Krijg het woord wat getekend/geraden moet worden
     * @return String van het woord wat getekend/geraden moet worden
     * @throws RemoteException
     */
    String getWord() throws RemoteException;

    /**
     * Zet de width van de brush
     * @param width int van de width van de brush
     * @throws RemoteException
     */
    void setBrushWidth(int width) throws RemoteException;


    /**
     * Set de kleur van de brush
     * @param red int tussen 0 en 255 van de kleur rood
     * @param green int tussen 0 en 255 van de kleur groen
     * @param blue int tussen 0 en 255 van de kleur blauw
     * @throws RemoteException
     */
    void setBrushColor(int red, int green, int blue) throws RemoteException;

    /**
     * Krijg IPlayer van de actieve speler
     * @return IPlayer van de actieve speler
     * @throws RemoteException
     */
    IPlayer getPlayer() throws RemoteException;
}
