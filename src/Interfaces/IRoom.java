package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IRoom extends Remote {
    /**
     * Geeft de IPlayer van de speler die de room host
     * @return IPlayer van de host
     * @throws RemoteException
     */
    IPlayer getHost() throws RemoteException;

    /**
     * Geeft referentie van (remote) instantie Drawing
     * @return IDrawing
     * @throws RemoteException
     */
    IDrawing getDrawing() throws RemoteException;

    /**
     * Geeft referentie van (remote) instantie Chat
     * @return IChat met alle chatberichten
     * @throws RemoteException
     */
    IChat getChat() throws RemoteException;

    /**
     * Geeft de lijst van IPlayers die in de room zitten
     * @return ArrayList van IPlayers
     * @throws RemoteException
     */
    ArrayList<IPlayer> getPlayers() throws RemoteException;

    /**
     * Voeg een speler toe aan de room
     * @param player IPlayer met de data van de speler
     * @throws RemoteException
     */
    void addPlayer(IPlayer player) throws RemoteException;

    /**
     * Verwijder een speler van de server
     * @param player IPlayer met de data van de speler
     * @throws RemoteException
     */
    void removePlayer(IPlayer player) throws RemoteException;

    /**
     * Raad het woord wat uitgebeeld is
     * @param guess String met het gegokte woord
     * @param player IPlayer die het woord gokt
     * @return True als het woord geraden is, anders false
     * @throws RemoteException
     */
    boolean guessWord(String guess, IPlayer player) throws RemoteException;

    /**
     * Geeft referentie van (remote) instantie ActivePlayer
     * @return IActivePlayer de speler die aan het tekenen is
     * @throws RemoteException
     */
    IActivePlayer getActivePlayer() throws RemoteException;
}

