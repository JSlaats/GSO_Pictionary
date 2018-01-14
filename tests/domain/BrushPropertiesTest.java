package domain;

import Interfaces.IBrushProperties;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;

import static org.junit.Assert.*;

public class BrushPropertiesTest {

    IBrushProperties bp;
    @Before
    public void setUp() {
        bp = new BrushProperties();
    }

    @Test
    public void getWidth() throws RemoteException {
        //default value should be 10
        int expectedResult = 10;
        int result = bp.getWidth();
        assertEquals(expectedResult,result);
    }

    @Test
    public void setWidth() throws RemoteException {
        //set brush to 15
        bp.setWidth(15);
        int expectedResult = 15;
        int result = bp.getWidth();
        assertEquals(expectedResult,result);

        //set brush to 0, should stay 15
        bp.setWidth(0);
        expectedResult = 15;
        result = bp.getWidth();
        assertEquals(expectedResult,result);

        //set brush to 26, should stay 15
        bp.setWidth(26);
        expectedResult = 15;
        result = bp.getWidth();
        assertEquals(expectedResult,result);
    }

    @Test
    public void setColor() throws RemoteException {
        //set all colors to 15, should work
        bp.setColor(15,15,15);

        int expectedResultRed = 15;
        int expectedResultGreen = 15;
        int expectedResultBlue = 15;

        int resultRed = bp.getR();
        int resultGreen = bp.getG();
        int resultBlue = bp.getB();

        assertEquals(expectedResultRed,resultRed);
        assertEquals(expectedResultGreen,resultGreen);
        assertEquals(expectedResultBlue,resultBlue);


        //set colors to -1, should stay 15
        bp.setColor(-1,-1,-1);

        expectedResultRed = 15;
        expectedResultGreen = 15;
        expectedResultBlue = 15;

        resultRed = bp.getR();
        resultGreen = bp.getG();
        resultBlue = bp.getB();

        assertEquals(expectedResultRed,resultRed);
        assertEquals(expectedResultGreen,resultGreen);
        assertEquals(expectedResultBlue,resultBlue);


        //set colors to 256, should stay 15
        bp.setColor(256,256,256);

        expectedResultRed = 15;
        expectedResultGreen = 15;
        expectedResultBlue = 15;

        resultRed = bp.getR();
        resultGreen = bp.getG();
        resultBlue = bp.getB();

        assertEquals(expectedResultRed,resultRed);
        assertEquals(expectedResultGreen,resultGreen);
        assertEquals(expectedResultBlue,resultBlue);
    }
}