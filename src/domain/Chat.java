package domain;


import Interfaces.IChat;
import fontyspublisher.IRemotePropertyListener;
import fontyspublisher.IRemotePublisherForListener;
import fontyspublisher.RemotePublisher;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Chat extends UnicastRemoteObject implements IChat, IRemotePublisherForListener {
    private ArrayList<ChatMessage> messages;
    private RemotePublisher publisher;
    private final static Logger LOGGER = Logger.getLogger(Chat.class.getName());

    public Chat() throws RemoteException {
        super();
        try {
            publisher = new RemotePublisher();
        } catch (RemoteException e) {
            System.out.println("Publisher failed to instantiate.");
            System.out.println("Remote exception: " + e.getMessage());
            return;
        }
        this.messages = new ArrayList<>();
        publisher.registerProperty("chat");
    }

/*    public ArrayList<String> getMessages() {
        ArrayList<String> msgList = new ArrayList<>();
        messages.forEach(msg -> msgList.add(msg.toString()));
        return msgList;
    }*/

    public void setMessage(String message, LocalDateTime time, String sender){
        this.messages.add(new ChatMessage(message, time, sender));
        try {
            publisher.inform("chat", null, getLastMessage());
        } catch (RemoteException e) {
            LOGGER.log(Level.WARNING,e.toString(),e);
        }
    }

    public String getLastMessage(){
        if(this.messages.size()>0) {
            return this.messages.get(this.messages.size() - 1).toString();
        }
        return null;
    }

    @Override
    public void subscribeRemoteListener(IRemotePropertyListener listener, String property) throws RemoteException {
        publisher.subscribeRemoteListener(listener, property);
    }

    @Override
    public void unsubscribeRemoteListener(IRemotePropertyListener listener, String property) throws RemoteException {
        publisher.unsubscribeRemoteListener(listener, property);
    }
}
