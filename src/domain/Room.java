package domain;

import Interfaces.*;
import fontyspublisher.IRemotePropertyListener;
import fontyspublisher.IRemotePublisherForListener;
import fontyspublisher.RemotePublisher;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Room extends UnicastRemoteObject implements IRoom, IRemotePublisherForListener {
    private final static Logger LOGGER = Logger.getLogger(Room.class.getName());

    private Chat chat;
    private Player host;
    private ActivePlayer activePlayer;
    private ArrayList<IPlayer> players;
    private Drawing drawing;
    private RemotePublisher publisher;

    public Room(Player host) throws RemoteException {
        try {
            publisher = new RemotePublisher();
        } catch (RemoteException e) {
            System.out.println("Publisher failed to instantiate.");
            System.out.println("Remote exception: " + e.getMessage());
            return;
        }
        publisher.registerProperty("player");

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
        if(!players.contains(player)) {
            this.players.add(player);
        }
        try {
            publisher.inform("player", null, getPlayers());
        } catch (RemoteException e) {
            LOGGER.log(Level.WARNING,e.toString(),e);
        }

    }
    public void removePlayer(IPlayer player){
        if(players.contains(player)) {
            this.players.remove(player);
        }
        try {
            publisher.inform("player", null, getPlayers());
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

    private void setActivePlayer(IPlayer activePlayer) {
        try {
            this.activePlayer = new ActivePlayer(activePlayer);
            publisher.inform("player", null, getPlayers());

        } catch (RemoteException e) {
            LOGGER.log(Level.WARNING,e.toString(),e);
        }
    }

    private IPlayer nextActivePlayer() throws RemoteException {
        int index = players.indexOf(getActivePlayer().getPlayer());
        int nextIndex;
        if(index + 1 >= players.size()){
            nextIndex = 0;
        }else{
            nextIndex = index + 1;
        }
        return players.get(nextIndex);
    }

    private void win(IPlayer player,int score) throws RemoteException {
        //increase winning players score
        player.increaseScore(score);
        //set a new active player
        setActivePlayer(nextActivePlayer());
        getDrawing().clear();
    }

    public boolean guessWord(String guess, IPlayer player) throws RemoteException {
        guess = guess.toLowerCase();
        String guessWord = getActivePlayer().getWord().toLowerCase();
        if(Objects.equals(guess, guessWord)) {
            this.getChat().setMessage("Guessed the word '"+guess+"', HE WAS RIGHT!!!!", LocalDateTime.now(),player.getName() );
            IPlayer pl = players.get(players.indexOf(player));
            win(pl,10);
            return true;
        }
        this.getChat().setMessage("Guessed the word '"+guess+"', IT WAS WRONG!!!!", LocalDateTime.now(),player.getName() );
        return false;
    }
    @Override
    public void subscribeRemoteListener(IRemotePropertyListener listener, String property) throws RemoteException {
        publisher.subscribeRemoteListener(listener, property);
    }

    @Override
    public void unsubscribeRemoteListener(IRemotePropertyListener listener, String property) throws RemoteException {
        publisher.unsubscribeRemoteListener(listener, property);
    }
}
