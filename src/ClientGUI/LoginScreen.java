package ClientGUI;

import Interfaces.IPlayer;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginScreen implements Initializable {

    public TextField inputUsername;
    public TextField inputPassword;
    public AnchorPane mainPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void Login(ActionEvent actionEvent) {
        if(!inputUsername.getText().isEmpty() && !inputPassword.getText().isEmpty()){
            System.out.println("You may log in");
            try {
                IPlayer player = ManagerClient.getInstance().getLogin().login(inputUsername.getText(),inputPassword.getText());
                if(player != null){
                    System.out.println("Login was successfull");
                    toRoomScreen();
                }else{
                    System.out.println("Login failed");
                }
            } catch (SQLException | RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void Register(ActionEvent actionEvent) {
        if(!inputUsername.getText().isEmpty() && !inputPassword.getText().isEmpty()) {
            try {
                boolean success = ManagerClient.getInstance().getLogin().register(inputUsername.getText(),inputPassword.getText());
                if(success){
                    System.out.println("You are registered, you may now log in");
                }
            } catch (SQLException e) {
                System.out.println("Something went wrong with registering");
                e.printStackTrace();
            } catch (RemoteException e) {
                e.printStackTrace();
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
