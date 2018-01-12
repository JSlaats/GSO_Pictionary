package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public interface IChat extends Remote {
    ArrayList<String> getMessages() throws RemoteException;
    void setMessage(String message, LocalDateTime time, String sender) throws RemoteException;
    String getLastMessage() throws RemoteException;
}
