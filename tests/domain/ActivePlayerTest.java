package domain;

import Interfaces.IActivePlayer;
import Interfaces.IBrushProperties;
import Interfaces.IPlayer;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;

import static org.junit.Assert.*;

public class ActivePlayerTest {

    IActivePlayer activePlayer;

    @Before
    public void setUp() throws RemoteException {
        IPlayer player = new Player("Jelle");
        activePlayer = new ActivePlayer(player);
    }

    @Test
    public void getWord() throws RemoteException {
        //Correct word
        String expectedResult = "Gnome Childd";
        String result = activePlayer.getWord();
        assertEquals(expectedResult,result);
        //Wrong word
        expectedResult = "Not the right word";
        assertNotEquals(expectedResult,result);
    }

    @Test
    public void getPlayer() throws RemoteException {
        //same player
        String expectedPlayer = new Player("Jelle").toString();
        String result = activePlayer.getPlayer().toString();
        assertEquals(expectedPlayer,result);

        //not the same player
        expectedPlayer = new Player("NietJelle").toString();
        assertNotEquals(expectedPlayer,result);

        //empty playername
        expectedPlayer = new Player("").toString();
        assertNotEquals(expectedPlayer,result);
    }


    @Test
    public void getBrush() throws RemoteException {
        //same brush
        IBrushProperties expectedResult = new BrushProperties();
        IBrushProperties result = activePlayer.getBrush();
        assertEquals(expectedResult,result);

        //differend brush
        expectedResult = new BrushProperties(10,255,255,255);
        assertNotEquals(expectedResult,result);

        //Should be same brush
        expectedResult = new BrushProperties(10,0,0,0);
        assertEquals(expectedResult,result);
    }

    @Test
    public void setBrushWidth() throws RemoteException {
        //default value should be 10
        int expectedResult = 10;
        int result = activePlayer.getBrush().getWidth();
        assertEquals(expectedResult,result);

        //set brush to 15
        activePlayer.setBrushWidth(15);
        expectedResult = 15;
        result = activePlayer.getBrush().getWidth();
        assertEquals(expectedResult,result);

        //set brush to 0, should stay 15
        activePlayer.setBrushWidth(0);
        expectedResult = 15;
        result = activePlayer.getBrush().getWidth();
        assertEquals(expectedResult,result);

        //set brush to 26, should stay 15
        activePlayer.setBrushWidth(26);
        expectedResult = 15;
        result = activePlayer.getBrush().getWidth();
        assertEquals(expectedResult,result);
    }

    @Test
    public void setBrushColor() throws RemoteException {
        //set all colors to 15, should work
        activePlayer.setBrushColor(15,15,15);

        int expectedResultRed = 15;
        int expectedResultGreen = 15;
        int expectedResultBlue = 15;

        int resultRed = activePlayer.getBrush().getR();
        int resultGreen = activePlayer.getBrush().getG();
        int resultBlue = activePlayer.getBrush().getB();

        assertEquals(expectedResultRed,resultRed);
        assertEquals(expectedResultGreen,resultGreen);
        assertEquals(expectedResultBlue,resultBlue);


        //set colors to -1, should stay 15
        activePlayer.setBrushColor(-1,-1,-1);

        expectedResultRed = 15;
        expectedResultGreen = 15;
        expectedResultBlue = 15;

        resultRed = activePlayer.getBrush().getR();
        resultGreen = activePlayer.getBrush().getG();
        resultBlue = activePlayer.getBrush().getB();

        assertEquals(expectedResultRed,resultRed);
        assertEquals(expectedResultGreen,resultGreen);
        assertEquals(expectedResultBlue,resultBlue);


        //set colors to 256, should stay 15
        activePlayer.setBrushColor(256,256,256);

        expectedResultRed = 15;
        expectedResultGreen = 15;
        expectedResultBlue = 15;

        resultRed = activePlayer.getBrush().getR();
        resultGreen = activePlayer.getBrush().getG();
        resultBlue = activePlayer.getBrush().getB();

        assertEquals(expectedResultRed,resultRed);
        assertEquals(expectedResultGreen,resultGreen);
        assertEquals(expectedResultBlue,resultBlue);
    }
}