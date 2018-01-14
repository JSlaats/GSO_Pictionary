package domain;

import Interfaces.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Room extends UnicastRemoteObject implements IRoom{
    private final static Logger LOGGER = Logger.getLogger(Room.class.getName());

    private Chat chat;
    private Player host;
    private ActivePlayer activePlayer;
    private ArrayList<IPlayer> players;
    private Drawing drawing;

    public Room(Player host) throws RemoteException {
        this.host = host;
        this.chat = new Chat();
        this.drawing = new Drawing();
        this.activePlayer = new ActivePlayer(host);
        this.players = new ArrayList<>();
        this.addPlayer(host);
    }

    public IChat getChat() {
        return chat;
    }

    public Player getHost(){
        return host;
    }

    public ArrayList<IPlayer> getPlayers() {
        return players;
    }

    public void addPlayer(IPlayer player){
        this.players.add(player);
        try {
            System.out.println("added player: "+player.getName());
        } catch (RemoteException e) {
            LOGGER.log(Level.WARNING,e.toString(),e);
        }
    }

    public IDrawing getDrawing() {
        return drawing;
    }

    public IActivePlayer getActivePlayer() {
        return activePlayer;
    }

/*    public void setActivePlayer(Player activePlayer) {
        try {
            this.activePlayer = new ActivePlayer(activePlayer);
        } catch (RemoteException e) {
            LOGGER.log(Level.WARNING,e.toString(),e);
        }
    }*/

    public boolean guessWord(String guess) throws RemoteException {
        guess = guess.toLowerCase();
        String guessWord = getActivePlayer().getWord().toLowerCase();
        if(Objects.equals(guess, guessWord)) {
            return true;
        }
        return false;
    }

}
