package ClientGUI;

import Interfaces.IPlayer;
import javafx.event.ActionEvent;
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

public class LoginScreen implements Initializable {

    public TextField inputUsername;
    public TextField inputPassword;
    public AnchorPane mainPane;
    public Label msgLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void Login(ActionEvent actionEvent) {
        if(!inputUsername.getText().isEmpty() && !inputPassword.getText().isEmpty()){
            System.out.println("You may log in");
            try {
                IPlayer player = ManagerClient.getInstance().getLogin().login(inputUsername.getText(),inputPassword.getText());
                if(player != null){
                    msgLabel.setTextFill(Color.GREEN);
                    msgLabel.setText("Login was successfull");
                    System.out.println("Login was successfull");
                    toRoomScreen();
                }else{
                    msgLabel.setTextFill(Color.RED);
                    msgLabel.setText("Login failed");
                    System.out.println("Login failed");
                }
            } catch (SQLException | RemoteException e) {
                msgLabel.setTextFill(Color.RED);
                msgLabel.setText("Connection failed");
               // e.printStackTrace();
            }
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
                e.printStackTrace();
            } catch (RemoteException e) {
                msgLabel.setTextFill(Color.RED);
                msgLabel.setText("Connection failed");
               // e.printStackTrace();
            }
        }
    }

    private void toRoomScreen(){
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("RoomScreen.fxml"));
            mainPane.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
