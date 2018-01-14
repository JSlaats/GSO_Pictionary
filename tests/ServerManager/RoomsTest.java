package ServerManager;

import Interfaces.IRooms;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;

import static org.junit.Assert.*;

public class RoomsTest {

    IRooms rooms;
    @Before
    public void setUp() throws Exception {
        rooms = new Rooms("Jelle","Coole gameroom","127.0.0.1",1999);
    }

    @Test
    public void getName() throws RemoteException {
        String expResult = "Coole gameroom";
        String result = rooms.getName();
        assertEquals(expResult,result);
    }

    @Test
    public void getIpAdress() throws RemoteException {
        String expResult = "127.0.0.1";
        String result = rooms.getIpAdress();
        assertEquals(expResult,result);
    }

    @Test
    public void getPort() throws RemoteException {
        int expResult = 1999;
        int result = rooms.getPort();
        assertEquals(expResult,result);
    }
}