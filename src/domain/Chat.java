package domain;

import java.util.ArrayList;

public class Chat {
    private ArrayList<ChatMessage> messages;

    public Chat(ArrayList<ChatMessage> messages) {
        this.messages = new ArrayList<>(messages);
    }

    public Chat() {
        this.messages = new ArrayList<>();
    }

    public ArrayList<ChatMessage> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<ChatMessage> messages) {
        this.messages = messages;
    }

    public void setMessage(ChatMessage message){
        this.messages.add(message);
    }
}
