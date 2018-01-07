package domain;

import java.util.Date;

public class ChatMessage {
    private String message;
    private Date time;
    private String sender;

    public ChatMessage(String message, Date time, String sender) {
        this.message = message;
        this.time = time;
        this.sender = sender;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "message='" + message + '\'' +
                ", time=" + time +
                ", sender='" + sender + '\'' +
                '}';
    }
}
