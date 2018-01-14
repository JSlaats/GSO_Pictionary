package ClientGUI;

import GameServer.GameServer;
import Interfaces.IRooms;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

public class RoomScreen implements Initializable {
    public TableView<IRooms> roomTable;
    public TableColumn<IRooms, String> ipCol;
    public TableColumn<IRooms, String> roomCol;
    public TableColumn<IRooms, Integer> portCol;
    public TableColumn<IRooms, String> hostCol;

    private final ObservableList<IRooms> data =
        FXCollections.observableArrayList();
    public Button createRoomBtn;
    public TextField inputRoomName;
    public Button joinRoomBtn;
    public AnchorPane mainPane;
    Timer timer;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            data.addAll(ManagerClient.getInstance().getRoomsList().getRoomsList());
        } catch (RemoteException e) {
            e.printStackTrace();
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
            }else{
               // System.out.println("Roomslist is same, not refreshing");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
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
                e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
        }
        return null;
    }

    private void toGameScreen(){
        try{
            timer.cancel();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("GameScreen.fxml"));
            mainPane.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
