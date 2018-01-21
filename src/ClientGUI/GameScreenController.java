package ClientGUI;

import Interfaces.IPlayer;
import Interfaces.IRoom;
import Interfaces.IStroke;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.awt.*;
import java.net.URL;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameScreenController implements Initializable{
    private final static Logger LOGGER = Logger.getLogger(GameScreenController.class.getName());

    @FXML public TextField chatInput;
    @FXML public TextArea chatBox;
    @FXML public Button sendBtn;
    @FXML public Button leaveBtn;
    @FXML public Canvas drawingCanvas;
    @FXML public Slider sizeSlider;
    @FXML public ComboBox<String> colorInput;
    @FXML public Label wordLabel;
    @FXML public TextField guessInput;
    @FXML public AnchorPane mainPane;
    @FXML public ListView userList;
    @FXML public Button clearBtn;
    @FXML public Button guessBtn;
    @FXML public Label sizeLbl;
    @FXML public Label colorLbl;
    @FXML public AnchorPane menuPane;
    @FXML public Label lblTime;
    @FXML private GraphicsContext gc;

    private final ObservableList<IPlayer> playerList =
            FXCollections.observableArrayList();

    private IRoom room = GameClient.getInstance().getRoom();
    private RemoteView rvDrawing;
    private RemoteView rvChat;
    private RemoteView rvRoom;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ArrayList<String> propertiesDrawing = new ArrayList<>();
            propertiesDrawing.add("stroke");
            propertiesDrawing.add("clear");
            ArrayList<String> propertiesChat = new ArrayList<>();
            propertiesChat.add("chat");
            ArrayList<String> propertiesRoom = new ArrayList<>();
            propertiesRoom.add("player");
            propertiesRoom.add("timer");
            propertiesRoom.add("newRound");

            rvDrawing = new RemoteView(this, GameClient.getInstance().getRoom().getDrawing(),propertiesDrawing);
            rvChat = new RemoteView(this, GameClient.getInstance().getRoom().getChat(),propertiesChat);
            rvRoom = new RemoteView(this, GameClient.getInstance().getRoom(),propertiesRoom);

            GameClient.setLocalPlayer(ManagerClient.getInstance().getLocalPlayer());
            if(!GameClient.isHost()) GameClient.getInstance().getRoom().addPlayer(GameClient.getLocalPlayer());
            this.playerList.setAll(room.getPlayers());

        } catch (RemoteException e) {
            LOGGER.log(Level.WARNING,e.toString(),e);
        }

        this.gc = drawingCanvas.getGraphicsContext2D();
        sizeSlider.valueProperty().addListener((observable, oldValue, newValue) -> setBrushSize());
        colorInput.getItems().addAll("Black","Red","Green","Blue","Yellow");
        colorInput.setValue("Black");
        updateWordLabel();
        Runtime.getRuntime().addShutdownHook(new Thread(this::leaveRoom));
        drawAll();
        userList.setItems(this.playerList);
        setGameScreenState();
    }

    public void setGameScreenState() {
        try {
            System.out.println(room.getActivePlayer().getPlayer());
            if(GameClient.getLocalPlayer().equals(room.getActivePlayer().getPlayer())){
                //player is activeplayer, activate drawing field
                menuPane.setVisible(true);

                drawingCanvas.setDisable(false);
                chatInput.setDisable(true);
                guessInput.setDisable(true);
                sendBtn.setDisable(true);
                guessBtn.setDisable(true);
            }else{
                //player is not activeplayer, disable drawing field
                menuPane.setVisible(false);

                drawingCanvas.setDisable(true);
                chatInput.setDisable(false);
                guessInput.setDisable(false);
                sendBtn.setDisable(false);
                guessBtn.setDisable(false);
            }
        } catch (RemoteException e) {
            LOGGER.log(Level.WARNING,e.toString(),e);
        }
    }

    public void leaveRoom() {
        Platform.runLater(()->{
            try {
                GameClient.getInstance().getRoom().removePlayer(GameClient.getLocalPlayer());
                rvRoom.close();
                rvDrawing.close();
                rvChat.close();
                System.exit(0);
            } catch (RemoteException e) {
                LOGGER.log(Level.WARNING,e.toString(),e);
            }
        });
    }


    public void sendChatMessageEventKey(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER))
        {
            try {
                sendChatMessage();
            } catch (RemoteException e) {
                LOGGER.log(Level.WARNING,e.toString(),e);
            }
        }
    }
    public void sendChatMessageEvent(ActionEvent actionEvent) {
        try {
            sendChatMessage();
        } catch (RemoteException e) {
            LOGGER.log(Level.WARNING,e.toString(),e);
        }
    }
    private void sendChatMessage() throws RemoteException {
        if(!chatInput.getText().isEmpty()) {
            LocalDateTime now = LocalDateTime.now();
            room.getChat().setMessage(chatInput.getText(), now, GameClient.getLocalPlayer().getName());
            chatInput.setText("");
        }
    }

    public void addChatMessage(String message){
        if(message != null){
            chatBox.appendText(message + "\n\r");
        }
    }

    private void guessWord(String guess) throws RemoteException {
        room.guessWord(guess,GameClient.getLocalPlayer());
        guessInput.clear();
    }


    public void setStroke(MouseEvent mouseEvent) throws RemoteException {
        //send stroke to server
        room.getDrawing().setStroke(new Point((int)mouseEvent.getX(),(int)mouseEvent.getY()));
    }

    public void drawStroke(IStroke stroke) throws RemoteException{
        if(stroke != null) {
            int r = stroke.getBrush().getR();
            int g = stroke.getBrush().getG();
            int b = stroke.getBrush().getB();
            Paint color = Color.rgb(r, g, b);
            int width = stroke.getBrush().getWidth();
            gc.setStroke(color);
            gc.setFill(color);
            gc.fillOval(stroke.getPosition().x, stroke.getPosition().y, width, width);
        }
    }

    public void drawAll()  {
        try {
            for (IStroke s:room.getDrawing().getStrokes()) {
                this.drawStroke(s);
            }
        } catch (RemoteException e) {
            LOGGER.log(Level.WARNING,e.toString(),e);
        }
    }
    public void updateUserList(ArrayList<IPlayer> players){
        Platform.runLater(()->{
            playerList.setAll(players);
        });
    }

    public void updateWordLabel(){
        Platform.runLater(()->{
            try {
                System.out.println("Word: "+room.getActivePlayer().getWord());
                wordLabel.setText(room.getActivePlayer().getWord());
            } catch (RemoteException e) {
                LOGGER.log(Level.WARNING,e.toString(),e);
            }
        });
    }
    public void clearScreen()  {
        try {
            room.getDrawing().clear();
        } catch (RemoteException e) {
            LOGGER.log(Level.WARNING,e.toString(),e);
        }
    }

    public void clearLocalScreen(){
        gc.clearRect(0, 0, drawingCanvas.getWidth(), drawingCanvas.getHeight());
    }

    public void setBrushSize()  {
        double val = sizeSlider.getValue();
        try {
            room.getActivePlayer().setBrushWidth((int)val);
        } catch (RemoteException e) {
            LOGGER.log(Level.WARNING,e.toString(),e);
        }
    }

    public void setColor(ActionEvent actionEvent) throws RemoteException {
        int r = 0;
        int g = 0;
        int b = 0;
        switch(colorInput.getValue()){
            case "Red":
                r = 255;
                break;
            case "Green":
                g = 255;
                break;
            case "Blue":
                b = 255;
                break;
            case "Yellow":
                r = 255;
                g = 255;
                break;
            case "Black":
            default:
                break;
        }

        room.getActivePlayer().setBrushColor(r,g,b);
    }

    public void guessEvent(ActionEvent actionEvent) {
        if(!guessInput.getText().isEmpty()){
            try {
                this.guessWord(guessInput.getText());
            } catch (RemoteException e) {
                LOGGER.log(Level.WARNING,e.toString(),e);
            }
        }
    }

    public void guessWordKey(KeyEvent keyEvent) {

        if (keyEvent.getCode().equals(KeyCode.ENTER) && !guessInput.getText().isEmpty())
        {
            try {
                this.guessWord(guessInput.getText());
            } catch (RemoteException e) {
                LOGGER.log(Level.WARNING,e.toString(),e);
            }
        }
    }


    public void updateTimer(int newValue) {
        Platform.runLater(()->{lblTime.setText(newValue+"");});

    }
}
