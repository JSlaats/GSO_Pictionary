package ClientGUI;

import GameServer.GameServer;
import Interfaces.IRooms;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.URL;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RoomScreenController implements Initializable {
    @FXML public TableView<IRooms> roomTable;
    @FXML public TableColumn<IRooms, String> ipCol;
    @FXML public TableColumn<IRooms, String> roomCol;
    @FXML public TableColumn<IRooms, Integer> portCol;
    @FXML public TableColumn<IRooms, String> hostCol;


    @FXML public Button createRoomBtn;
    @FXML public TextField inputRoomName;
    @FXML public Button joinRoomBtn;
    @FXML public AnchorPane mainPane;
    private Timer timer;
    private final static Logger LOGGER = Logger.getLogger(RoomScreenController.class.getName());
    private final ObservableList<IRooms> data =
            FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            data.addAll(ManagerClient.getInstance().getRoomsList().getRoomsList());
        } catch (RemoteException e) {
            LOGGER.log(Level.WARNING,e.toString(),e);
        }

        hostCol.setCellValueFactory(new PropertyValueFactory<>("host"));
        roomCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        ipCol.setCellValueFactory(new PropertyValueFactory<>("ipAdress"));
        portCol.setCellValueFactory(new PropertyValueFactory<>("port"));

        roomTable.setItems(data);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                reloadTable();
            }
        }, 1000, 1000);

    }

    public void reloadTable(){
        try {
            if(data.size() != ManagerClient.getInstance().getRoomsList().getRoomsList().size()) {
                data.setAll(ManagerClient.getInstance().getRoomsList().getRoomsList());
            }

        } catch (RemoteException e) {
            LOGGER.log(Level.WARNING,e.toString(),e);
        }

    }
    public void CreateRoom(ActionEvent actionEvent) throws RemoteException {
        String roomName = inputRoomName.getText();
        if(!roomName.isEmpty()){
            String ip = getIpAdress();
            int port = getPort();
            String host = ManagerClient.getInstance().getLocalPlayer().getName();

            ManagerClient.getInstance().getRoomsList().add(host,roomName,ip,port);
            reloadTable();
            try {
                hostRoom(ip,port,host);
            } catch (IOException e) {
                LOGGER.log(Level.WARNING,e.toString(),e);
            }
        }
    }
    private void hostRoom(String ip, int port, String host) throws IOException {
        String[] params = new String[] {""+port, host};
        //start new GameServer
        GameServer.main(params);
        GameClient.setInstance(ip,port);
        GameClient.setIsHost(true);
        toGameScreen();
    }
    private int getPort(){
        try (ServerSocket socket = new ServerSocket(0)) {
            socket.setReuseAddress(true);
            return socket.getLocalPort();
        } catch (IOException e) {
            LOGGER.log(Level.WARNING,e.toString(),e);
        }
        return 1099;
    }

    public void JoinRoom(ActionEvent actionEvent) throws RemoteException {
        IRooms room = roomTable.getSelectionModel().getSelectedItem();
        if(room != null) {
            GameClient.setInstance(room.getIpAdress(),room.getPort());
            GameClient.setIsHost(false);
            toGameScreen();
        }
    }



    private static String getIpAdress() {
        try{
            InetAddress ex = InetAddress.getLocalHost();
            return ex.getHostAddress();
        } catch (UnknownHostException e) {
            LOGGER.log(Level.WARNING,e.toString(),e);
        }
        return null;
    }

    private void toGameScreen(){
        try{
            timer.cancel();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("GameScreen.fxml"));
            mainPane.getChildren().setAll(pane);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING,e.toString(),e);
        }
    }
}
