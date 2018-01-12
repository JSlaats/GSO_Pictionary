package domain;


import Interfaces.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Objects;

public class Room extends UnicastRemoteObject implements IRoom{
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

    public void setHost(Player host) {
        this.host = host;
    }

    public ArrayList<IPlayer> getPlayers() {
        return players;
    }

    public void addPlayer(Player player){
        this.players.add(player);
    }

    public IDrawing getDrawing() {
        return drawing;
    }

    public IActivePlayer getActivePlayer() {
        return activePlayer;
    }

    private void setActivePlayer(Player activePlayer) {
        try {
            this.activePlayer = new ActivePlayer(activePlayer);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean guessWord(String guess) throws RemoteException {
        guess = guess.toLowerCase();
        String guessWord = getActivePlayer().getWord().toLowerCase();
        if(Objects.equals(guess, guessWord)) {
            return true;
        }
        return false;
    }

}
