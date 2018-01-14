package domain;

import Interfaces.IPlayer;

import java.io.Serializable;
import java.util.Objects;

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
        if(score > 0)this.score += score;
    }

    @Override
    public String toString() {
        return "Name: "+this.name + "  Score: "+this.getScore();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return score == player.score &&
                Objects.equals(name, player.name);
    }

}
