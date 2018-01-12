package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDateTime;

public interface IChat extends Remote {
    void setMessage(String message, LocalDateTime time, String sender) throws RemoteException;
    String getLastMessage() throws RemoteException;
}
