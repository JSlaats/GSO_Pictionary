package domain;

import Interfaces.IPlayer;

import java.io.Serializable;

public class Player implements IPlayer, Serializable{
    private String name;
    private int score;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore(int score) {
        this.score += score;
    }

    @Override
    public String toString() {
        return "Name: "+this.name + "  Score: "+this.getScore();
    }
}
