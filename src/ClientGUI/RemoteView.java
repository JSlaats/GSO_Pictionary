package ClientGUI;

import Interfaces.IPlayer;
import Interfaces.IRooms;
import Interfaces.IStroke;
import fontyspublisher.IRemotePropertyListener;
import fontyspublisher.IRemotePublisherForListener;

import java.beans.PropertyChangeEvent;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class RemoteView extends UnicastRemoteObject implements IRemotePropertyListener {

    private ArrayList<String> properties;
    private Object controller;
    private IRemotePublisherForListener publisher;

    public RemoteView(Object controller, Remote remote, ArrayList<String> properties) throws RemoteException {
        this.publisher = (IRemotePublisherForListener) remote;
        this.properties = properties;
        this.controller = controller;
        System.out.println("Connection with remote publisher established");
        try {
            for (String property:properties) {
                publisher.subscribeRemoteListener(this, property);
            }
        } catch (RemoteException e) {
            System.out.println("Failed to subscribe.");
            System.out.println("Remote exception: " + e.getMessage());
        }
    }

    public void close() {
        try {
            for (String property:properties) {
                publisher.unsubscribeRemoteListener(this, property);
            }
        } catch (RemoteException e) {
            System.out.println("Failed to unsubscribe.");
            System.out.println("Remote exception: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void propertyChange(PropertyChangeEvent evt) throws RemoteException {
        switch(evt.getPropertyName()){
            case "list":
                ((RoomScreenController)controller).reloadTable((ArrayList<IRooms>)evt.getNewValue());
                break;
            case "stroke":
                ((GameScreenController)controller).drawStroke((IStroke)evt.getNewValue());
                break;
            case "clear":
                ((GameScreenController)controller).clearLocalScreen();
                break;
            case "chat":
                ((GameScreenController)controller).addChatMessage((String)evt.getNewValue());
                break;
            case "player":
                ((GameScreenController)controller).updateUserList((ArrayList<IPlayer>)evt.getNewValue());
                break;
            case "newRound":
                ((GameScreenController)controller).updateUserList((ArrayList<IPlayer>)evt.getNewValue());
                ((GameScreenController)controller).setGameScreenState();
                ((GameScreenController)controller).updateWordLabel();
                break;
            case "timer":
                ((GameScreenController)controller).updateTimer((int)evt.getNewValue());
                break;
        }
    }
}
