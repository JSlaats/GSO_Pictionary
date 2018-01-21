package domain;

import Interfaces.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class RoomTest {

    private IRoom room;

    @Before
    public void setUp() throws Exception {
        room = new Room(new Player("Jelle"));
    }

    @Test
    public void getHost() throws RemoteException {
        //right host
        IPlayer expResult = new Player("Jelle");
        IPlayer result = room.getHost();
        assertEquals(expResult,result);

        //wrong host
        expResult = new Player("NotJelle");
        result = room.getHost();
        assertNotEquals(expResult,result);
    }

    @Test
    public void getPlayers() throws RemoteException {
        //get players should be the same
        ArrayList<IPlayer> expResult = new ArrayList<>();
        expResult.add(new Player("Jelle"));
        ArrayList<IPlayer> result = room.getPlayers();
        assertEquals(expResult,result);
    }

    @Test
    public void addPlayer() throws RemoteException {
        //size default check
        int expResult = 1;
        int result = room.getPlayers().size();
        assertEquals(expResult,result);

        //size addPlayer
        room.addPlayer(new Player("Pieter Post"));
        expResult = 2;
        result = room.getPlayers().size();
        assertEquals(expResult,result);
    }

    @Test
    public void getDrawing() throws RemoteException {
        IDrawing expResult = new Drawing();
        IDrawing result = room.getDrawing();
        assertEquals(expResult.getStrokes(),result.getStrokes());
    }

    @Test
    public void getActivePlayer() throws RemoteException {
        //default active player
        IActivePlayer expResult = new ActivePlayer(new Player("Jelle"),"Gnome Child");
        IActivePlayer result = room.getActivePlayer();
        assertEquals(expResult.getPlayer(),result.getPlayer());
    }

    @Test
    public void guessWord() throws RemoteException {
        //right word
        boolean result = room.guessWord("Gnome Child", new Player("Jelle"));
        assertTrue(result);
        //wrong word
        result = room.guessWord("Not The word!", new Player("Jelle"));
        assertFalse(result);
    }
}