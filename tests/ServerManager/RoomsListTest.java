package ServerManager;

import Interfaces.IRoom;
import Interfaces.IRoomsList;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;

import static org.junit.Assert.*;

public class RoomsListTest {

    IRoomsList roomsList;
    @Before
    public void setUp() throws Exception {
        roomsList = new RoomsList();
    }

    @Test
    public void getRoomsList() throws RemoteException {
        roomsList.add("Jelle","Coole gameroom","127.0.0.1",1999);
        int expResult = 1;
        int result = roomsList.getRoomsList().size();
        assertEquals(expResult,result);
    }

}