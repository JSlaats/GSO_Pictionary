package domain;

import Interfaces.IChat;
import Interfaces.IRoom;
import ServerManager.IRooms;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;

import static org.junit.Assert.*;

public class RoomTest {

    private IRoom room;

    @Before
    public void setUp() throws Exception {
        room = new Room(new Player("Jelle"));
    }
    @Test
    public void getChat() throws RemoteException {

    }

    @Test
    public void getHost() {
    }

    @Test
    public void getPlayers() {
    }

    @Test
    public void addPlayer() {
    }

    @Test
    public void getDrawing() {
    }

    @Test
    public void getActivePlayer() {
    }

    @Test
    public void guessWord() {
    }
}