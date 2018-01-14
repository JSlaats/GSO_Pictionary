package domain;

import Interfaces.IChat;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

public class ChatTest {

    IChat chat;
    @Before
    public void setUp() throws Exception {
        chat = new Chat();
    }

    @Test
    public void setMessage() throws RemoteException {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");

        chat.setMessage("hey",now,"Jelle");

        String expResult =  "["+now.format(dtf)+"] Jelle: hey";
        String result = chat.getLastMessage();
        assertEquals(expResult,result);
    }

}