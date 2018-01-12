package ClientGUI;

import ServerManager.IRooms;
import ServerManager.IRoomsList;
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
import java.net.URL;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.ArrayList;
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
    private ArrayList<IRooms> rl;
    public Button createRoomBtn;
    public TextField inputRoomName;
    public Button joinRoomBtn;
    public AnchorPane mainPane;
    Timer timer;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            data.addAll(ManagerClient.getInstance().getRoomsList().getRoomsList());
            rl = ManagerClient.getInstance().getRoomsList().getRoomsList();
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
            if(rl != ManagerClient.getInstance().getRoomsList().getRoomsList()) {
                data.setAll(ManagerClient.getInstance().getRoomsList().getRoomsList());
            }else{
                System.out.println("Roomslist is same, not refreshing");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
    public void CreateRoom(ActionEvent actionEvent) throws RemoteException {
        String roomName = inputRoomName.getText();
        if(!roomName.isEmpty()){
            ManagerClient.getInstance().getRoomsList().add("randomhostName",roomName,getIpAdress(),1099);
            reloadTable();
            try {
                hostRoom();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void JoinRoom(ActionEvent actionEvent) throws RemoteException {
        IRooms room = roomTable.getSelectionModel().getSelectedItem();
        if(room != null) {
            System.out.print(room.getName());
            toGameScreen();
        }
    }

    private void hostRoom() throws IOException {
        try {

            //runProcess("javac -cp run src/GameServer/GameServer.java");
        } catch (Exception e) {
            e.printStackTrace();
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

    private static void runProcess(String command) throws Exception {
        Process proc = Runtime.getRuntime().exec(command);
        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(proc.getInputStream()));

        BufferedReader stdError = new BufferedReader(new
                InputStreamReader(proc.getErrorStream()));

        // read the output from the command
        System.out.println("Here is the standard output of the command:\n");
        String s = null;
        while ((s = stdInput.readLine()) != null) {
            System.out.println(s);
        }

        // read any errors from the attempted command
        System.out.println("Here is the standard error of the command (if any):\n");
        while ((s = stdError.readLine()) != null) {
            System.out.println(s);
        }

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
