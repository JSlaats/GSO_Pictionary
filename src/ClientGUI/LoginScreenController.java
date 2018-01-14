package ClientGUI;

import Interfaces.IPlayer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginScreenController implements Initializable {
    private final static Logger LOGGER = Logger.getLogger(LoginScreenController.class.getName());
    @FXML public TextField inputUsername;
    @FXML public TextField inputPassword;
    @FXML public AnchorPane mainPane;
    @FXML public Label msgLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public void Login(ActionEvent actionEvent) {
        if(!inputUsername.getText().isEmpty() && !inputPassword.getText().isEmpty()){
            System.out.println("You may log in");
            try {
                IPlayer player = ManagerClient.getInstance().getLogin().login(inputUsername.getText(),inputPassword.getText());
                if(player != null){
                    ManagerClient.getInstance().setLocalPlayer(player);
                    msgLabel.setTextFill(Color.GREEN);
                    msgLabel.setText("Login was successfull");
                    System.out.println("Login was successfull");
                    toRoomScreen();
                }else{
                    msgLabel.setTextFill(Color.RED);
                    msgLabel.setText("Login failed");
                    System.out.println("Login failed");
                }
            } catch (SQLException | RemoteException | NullPointerException ex) {
                msgLabel.setTextFill(Color.RED);
                msgLabel.setText("Connection failed");
            }
        }else{
            msgLabel.setTextFill(Color.RED);
            msgLabel.setText("All fields need to be filled");
        }
    }

    public void Register(ActionEvent actionEvent) {
        if(!inputUsername.getText().isEmpty() && !inputPassword.getText().isEmpty()) {
            try {
                boolean success = ManagerClient.getInstance().getLogin().register(inputUsername.getText(),inputPassword.getText());
                if(success){
                    msgLabel.setTextFill(Color.GREEN);
                    msgLabel.setText("You are registered, you may now log in");
                    System.out.println("You are registered, you may now log in");
                }
            } catch (SQLException e) {
                msgLabel.setTextFill(Color.RED);
                msgLabel.setText("Register failed");
                System.out.println("Something went wrong with registering");
                LOGGER.log(Level.WARNING,e.toString(),e);
            } catch (RemoteException | NullPointerException ex) {
                msgLabel.setTextFill(Color.RED);
                msgLabel.setText("Connection failed");
            }
        }else{
            msgLabel.setTextFill(Color.RED);
            msgLabel.setText("All fields need to be filled");
        }
    }

    private void toRoomScreen(){
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("RoomScreen.fxml"));
            mainPane.getChildren().setAll(pane);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING,e.toString(),e);
        }
    }
}
