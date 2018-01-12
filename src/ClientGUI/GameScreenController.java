package ClientGUI;

import Interfaces.*;
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
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class GameScreenController implements Initializable{
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


    //Room room = new Room(new Player("Jelle"));
    IRoom room = GameClient.getInstance().getRoom();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GameClient.getInstance().setGameScreenController(this);
        this.gc = drawingCanvas.getGraphicsContext2D();
        sizeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            setBrushSize();
        });
        colorInput.getItems().addAll("Black","Red","Green","Blue","Yellow");
        colorInput.setValue("Black");
        updateWordLabel();
    }

    public void leaveRoom(ActionEvent actionEvent) {
        try {
            System.out.println(room.getActivePlayer().getWord());
            room.getActivePlayer().setWord("Test");
            System.out.println(room.getActivePlayer().getWord());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void sendChatMessage() throws RemoteException {
        LocalDateTime now = LocalDateTime.now();
        room.getChat().setMessage(chatInput.getText(),now,room.getHost().toString());
        chatBox.appendText(room.getChat().getLastMessage()+"\n\r");
        chatInput.setText("");
    }
    private void guessWord(String guess) throws RemoteException {
        if(room.guessWord(guess)){
            chatBox.appendText("You guessed the word: \""+guess+"\"! Congratz bro!!!!\n\r");
            this.clearScreen();

        }else{
            chatBox.appendText("Your guess was: \""+guess+"\", and it was wrong. YOU SUCK!\n\r");
        }
    }
    public void sendChatMessageEvent(ActionEvent actionEvent) {
        try {
            sendChatMessage();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public void sendChatMessageEventKey(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER))
        {
            try {
                sendChatMessage();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
    public void setStroke(MouseEvent mouseEvent) throws RemoteException {
        //send stroke to server
        room.getDrawing().setStroke(new Point((int)mouseEvent.getX(),(int)mouseEvent.getY()));
        //draw stroke from server
        drawStroke(room.getDrawing().getLastStroke());
        System.out.println(room.getDrawing().getStrokes().size());
    }
    public void drawStroke(IStroke stroke) throws RemoteException{
        int r = stroke.getBrush().getR();
        int g = stroke.getBrush().getG();
        int b = stroke.getBrush().getB();
        Paint color = Color.rgb(r,g,b);
        int width = stroke.getBrush().getWidth();
        gc.setStroke(color);
        gc.setFill(color);
        gc.fillOval(stroke.getPosition().x,stroke.getPosition().y,width,width);
    }

    public void drawAll() throws RemoteException {
        for (IStroke s:room.getDrawing().getStrokes()) {
            this.drawStroke(s);
        }
    }

    public void updateWordLabel(){
        try {
            System.out.println("Word: "+room.getActivePlayer().getWord());
            wordLabel.setText(room.getActivePlayer().getWord());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public void clearScreen()  {
        /*
        gc.clearRect(0, 0, drawingCanvas.getWidth(), drawingCanvas.getHeight());
        room.getDrawing().clear();*/
        try {
            drawAll();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    public void setBrushSize()  {
        double val = sizeSlider.getValue();
        System.out.println(val);
        try {
            room.getActivePlayer().setBrushWidth((int)val);
            //room.getActivePlayer().getBrush().setWidth((int)val);
            System.out.println(room.getActivePlayer().getBrush().getWidth());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setColor(ActionEvent actionEvent) throws RemoteException {
        int r = 0;
        int g = 0;
        int b = 0;
        //Paint color;
        switch(colorInput.getValue()){
            case "Red":
                r = 255;
             //   color = Color.RED;
                break;
            case "Green":
                g = 255;
              //  color = Color.GREEN;
                break;
            case "Blue":
                b = 255;
               // color = Color.BLUE;
                break;
            case "Yellow":
                r = 255;
                g = 255;
               // color = Color.YELLOW;
                break;
            case "Black":
            default:
                //color = Color.BLACK;
                break;
        }

        room.getActivePlayer().setBrushColor(r,g,b);
    }

    public void guessEvent(ActionEvent actionEvent) {
        if(!guessInput.getText().isEmpty()){
            try {
                this.guessWord(guessInput.getText());
            } catch (RemoteException e) {
                e.printStackTrace();
            }

            guessInput.clear();
        }
    }

    public void guessWordKey(KeyEvent keyEvent) {

        if (keyEvent.getCode().equals(KeyCode.ENTER) && !guessInput.getText().isEmpty())
        {
            try {
                this.guessWord(guessInput.getText());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            guessInput.clear();
        }
    }


}
