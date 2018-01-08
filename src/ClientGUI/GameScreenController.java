package ClientGUI;

import domain.*;
import domain.Stroke;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.awt.*;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class GameScreenController implements Initializable {
    public TextField chatInput;
    public TextArea chatBox;
    public Button sendBtn;
    public Button leaveBtn;
    public Canvas drawingCanvas;
    public Slider sizeSlider;
    public ComboBox<String> colorInput;
    public Label wordLabel;
    public TextField guessInput;
    private GraphicsContext gc;

    private String guessWord = "";
    Room room = new Room(new Player("Jelle"));

    public void leaveRoom(ActionEvent actionEvent) {
    }

    private void sendChatMessage(){
        ChatMessage message = new ChatMessage(chatInput.getText(), null,room.getHost().toString());
        chatBox.appendText(message.toString()+"\n\r");
        chatInput.setText("");
    }
    private void guessWord(String guess){
        guess = guess.toLowerCase();
        String guessWordL = guessWord.toLowerCase();
        if(Objects.equals(guess, guessWordL)){
            chatBox.appendText("You guessed the word: \""+guessWord+"\"! Congratz bro!!!!\n\r");
        }else{
            chatBox.appendText("Your guess was: \""+guess+"\", and it was wrong. YOU SUCK!\n\r");
        }
    }
    public void sendChatMessageEvent(ActionEvent actionEvent) {
        sendChatMessage();
    }
    public void sendChatMessageEventKey(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER))
        {
            sendChatMessage();
        }
    }
    public void setStroke(MouseEvent mouseEvent) {
        int width = room.getActivePlayer().getBrush().getWidth();
        gc.setStroke(room.getActivePlayer().getBrush().getColor());
        gc.setFill(room.getActivePlayer().getBrush().getColor());
        gc.fillOval((int)mouseEvent.getX(),(int)mouseEvent.getY(),width,width);

        room.getDrawing().setStroke(new Stroke(
                new Point((int)mouseEvent.getX(),(int)mouseEvent.getY()),
                room.getActivePlayer().getBrush()
        ));

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.gc = drawingCanvas.getGraphicsContext2D();

        sizeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            setBrushSize();
        });
        colorInput.getItems().addAll("Black","Red","Green","Blue","Yellow");
        colorInput.setValue("Black");
        this.setWord("Gnome Child");
    }
    public void setWord(String word){
        guessWord = word;
        wordLabel.setText(word);
    }
    public void clearScreen(ActionEvent actionEvent) {
        gc.clearRect(0, 0, drawingCanvas.getWidth(), drawingCanvas.getHeight());
        room.getDrawing().clear();
    }

    public void setBrushSize() {
        double val = sizeSlider.getValue();
        System.out.println(val);
        room.getActivePlayer().getBrush().setWidth((int)val);
    }

    public void setColor(ActionEvent actionEvent) {
        Paint color;
        switch(colorInput.getValue()){
            case "Red":
                color = Color.RED;
                break;
            case "Green":
                color = Color.GREEN;
                break;
            case "Blue":
                color = Color.BLUE;
                break;
            case "Yellow":
                color = Color.YELLOW;
                break;
            case "Black":
            default:
                color = Color.BLACK;
                break;
        }
        room.getActivePlayer().getBrush().setColor(color);
    }

    public void guessEvent(ActionEvent actionEvent) {
        if(!guessInput.getText().isEmpty()){
            this.guessWord(guessInput.getText());
            guessInput.setText("");
        }
    }

    public void guessWordKey(KeyEvent keyEvent) {

        if (keyEvent.getCode().equals(KeyCode.ENTER) && !guessInput.getText().isEmpty())
        {
            this.guessWord(guessInput.getText());
            guessInput.setText("");
        }
    }


}
