package domain;

import Interfaces.IPlayer;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;
import static org.junit.Assert.*;

public class PlayerTest {

    IPlayer player;
    @Before
    public void setUp() throws Exception {
        player = new Player("Jelle");
    }

    @Test
    public void getName() throws RemoteException {
        //name is correct
        String expResult = "Jelle";
        String result = player.getName();
        assertEquals(expResult,result);

        //name is wrong
        expResult = "NietJelle";
        assertNotEquals(expResult,result);
    }

    @Test
    public void getScore() throws RemoteException {
        //score is default 0
        int expResult = 0;
        int result = player.getScore();
        assertEquals(expResult,result);
    }

    @Test
    public void increaseScore() throws RemoteException {
        //increase score by 10
        player.increaseScore(10);
        int expResult = 10;
        int result = player.getScore();

        //increase score by -1, should stay 10
        player.increaseScore(-1);
        result = player.getScore();
        assertEquals(expResult,result);
    }

    @Test
    public void ToString() {
        String expResult = "Name: Jelle  Score: 0";
        String result = player.toString();
        assertEquals(expResult,result);
    }
}