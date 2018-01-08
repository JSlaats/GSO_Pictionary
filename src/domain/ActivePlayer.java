package domain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ActivePlayer {
    private Player activePlayer;
    private BrushProperties brush;
    public String word;
    public ActivePlayer(Player player){
        this.activePlayer = player;
        this.brush = new BrushProperties();
        this.word = this.generateWord();
    }
    private String generateWord(){


        return "";
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
