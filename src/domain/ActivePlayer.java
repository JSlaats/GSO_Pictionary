package domain;

import Interfaces.IActivePlayer;

import java.io.Serializable;
import java.rmi.RemoteException;

public class ActivePlayer implements Serializable, IActivePlayer {
    private Player activePlayer;
    private BrushProperties brush;
    public String word;

    public ActivePlayer(Player player) {
        this.activePlayer = player;
        this.brush = new BrushProperties();
        this.word = this.generateWord();
    }
    private String generateWord(){
        return "Gnome Childd";
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public void setActivePlayer(Player activePlayer) {
        this.activePlayer = activePlayer;
    }

    public BrushProperties getBrush() {
        return brush;
    }

    public void setBrush(BrushProperties brush) {
        this.brush = brush;
    }
}
