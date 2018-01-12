package domain;


import Interfaces.IChat;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Chat extends UnicastRemoteObject implements IChat{
    private ArrayList<ChatMessage> messages;

    public Chat() throws RemoteException {
        super();
        this.messages = new ArrayList<>();
    }

    public ArrayList<String> getMessages() {
        ArrayList<String> msgList = new ArrayList<>();
        messages.forEach(msg -> msgList.add(msg.toString()));
        return msgList;
    }

    public void setMessage(String message, LocalDateTime time, String sender){
        this.messages.add(new ChatMessage(message, time, sender));
    }
    public String getLastMessage(){
        return this.messages.get(this.messages.size()-1).toString();
    }

}
