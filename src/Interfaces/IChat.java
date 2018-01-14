package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public interface IChat extends Remote {
    /**
     * Voeg een chatbericht toe
     * @param message het bericht wat toegevoegd wordt
     * @param time tijd dat het bericht is verstuurd
     * @param sender String van de playernaam
     * @throws RemoteException
     */
    void setMessage(String message, LocalDateTime time, String sender) throws RemoteException;

    /**
     * Krijg het laatst toegevoegde chatbericht
     * @return String van het laatst verstuurde chatbericht
     * @throws RemoteException
     */
    String getLastMessage() throws RemoteException;
}
