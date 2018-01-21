package domain;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChatMessage {
    private String message;
    private LocalDateTime time;
    private String sender;

    public ChatMessage(String message, LocalDateTime time, String sender) {
        this.message = message;
        this.time = time;
        this.sender = sender;
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        return "[" + time.format(dtf) + "] " + sender + ": " + message;
    }
}
