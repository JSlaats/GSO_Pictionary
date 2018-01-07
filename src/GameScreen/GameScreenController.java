package GameScreen;

import domain.*;
import domain.Stroke;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.awt.*;

public class GameScreenController {
    public TextField chatInput;
    public TextArea chatBox;
    public Button sendBtn;
    public Button leaveBtn;

    Room room = new Room(new Player("Jelle"));

    public void leaveRoom(ActionEvent actionEvent) {
    }

    public void sendChatMessage(ActionEvent actionEvent) {
        ChatMessage message = new ChatMessage(chatInput.getText(), null,room.getHost().toString());
        chatBox.appendText(message.toString()+"\n\r");
        chatInput.setText("");
    }

    public void setStroke(MouseEvent mouseEvent) {
        room.getDrawing().setStroke(new Stroke(
                new Point((int)mouseEvent.getX(),(int)mouseEvent.getY()),
                new Point((int)mouseEvent.getX(),(int)mouseEvent.getY()),
                new BrushProperties()
        ));

    }
}
