package domain;

import Interfaces.IRoom;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Objects;

public class Room extends UnicastRemoteObject implements IRoom{
    private Chat chat;
    private Player host;
    private ActivePlayer activePlayer;
    private ArrayList<Player> players;
    private Drawing drawing;

    public Room(Player host) throws RemoteException {
        this.host = host;
        this.chat = new Chat();
        this.drawing = new Drawing();
        this.activePlayer = new ActivePlayer(host);
        this.players = new ArrayList<>();
        this.addPlayer(host);
    }

    /*public Room (Player host, ArrayList<Player> players,Drawing drawing,Chat chat) throws RemoteException{
        this.host = host;
        this.players = players;
        this.drawing = drawing;
        this.chat = chat;
        this.activePlayer = new ActivePlayer(host);
    }*/

    public Player getHost(){
        return host;
    }

    public void setHost(Player host) {
        this.host = host;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
/*
    public void setPlayers(ArrayList<Player> players) {
        this.players = new ArrayList<>(players);
    }*/

    public void addPlayer(Player player){
        this.players.add(player);
    }

    public Drawing getDrawing() {
        return drawing;
    }
/*
    public void setDrawing(Drawing drawing) {
        this.drawing = drawing;
    }*/

    public ActivePlayer getActivePlayer() {
        return activePlayer;
    }

    private void setActivePlayer(Player activePlayer) {
        this.activePlayer = new ActivePlayer(activePlayer);
    }

    public boolean guessWord(String guess){
        guess = guess.toLowerCase();
        String guessWord = getActivePlayer().getWord().toLowerCase();
        if(Objects.equals(guess, guessWord)) {
            return true;
        }
        return false;
    }

}
