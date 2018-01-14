package domain;

import Interfaces.IDrawing;
import Interfaces.IStroke;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.rmi.RemoteException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class DrawingTest {

    IDrawing drawing;

    @Before
    public void setUp() throws Exception {
        drawing = new Drawing();
    }

    @Test
    public void getStrokes() throws RemoteException {
        //no strokes
        ArrayList<IStroke> result = drawing.getStrokes();
        ArrayList<IStroke> expResult = new ArrayList<>();
        assertEquals(result,expResult);

        //one stroke
        drawing.setStroke(new Point(0,0));
        int result1 = drawing.getStrokes().size();
        int expRes1 = 1;
        assertEquals(result1,expRes1);
    }

    @Test
    public void getLastStroke() throws RemoteException {
        //drawing has no strokes, should be null
        IStroke result = drawing.getLastStroke();
        assertNull(result);

        //drawing has a stroke, should equal this
        drawing.setStroke(new Point(55,4));
        IStroke expResult = new Stroke(new Point(55,4),new BrushProperties());
        result = drawing.getLastStroke();
        assertEquals(expResult,result);
    }

    @Test
    public void clear() throws RemoteException {
        //add a stroke to the list so it contains something
        drawing.setStroke(new Point(55,5));
        drawing.clear();
        //result should be 0
        int result = drawing.getStrokes().size();
        int expResult = 0;
        assertEquals(expResult,result);
    }
}