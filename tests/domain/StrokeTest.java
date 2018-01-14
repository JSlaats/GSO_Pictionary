package domain;

import Interfaces.IBrushProperties;
import Interfaces.IStroke;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.rmi.RemoteException;

import static org.junit.Assert.*;

public class StrokeTest {
    IStroke stroke;
    @Before
    public void setUp() throws Exception {
        stroke = new Stroke(new Point(10,10),new BrushProperties());
    }

    @Test
    public void getPosition() throws RemoteException {
        Point expResult = new Point(10,10);
        Point result = stroke.getPosition();
        assertEquals(expResult,result);
    }

    @Test
    public void getBrush() throws RemoteException {
        //same new brush
        IBrushProperties expResult = new BrushProperties();
        IBrushProperties result = stroke.getBrush();
        assertEquals(expResult,result);

        //different brush
        expResult = new BrushProperties(12,12,12,12);
        assertNotEquals(expResult,result);
    }
}