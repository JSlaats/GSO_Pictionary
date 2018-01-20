package domain;

import Interfaces.IPlayer;
import java.io.Serializable;
import java.util.Objects;

public class Player implements IPlayer, Serializable{

    private String name;
    private int score;
    private boolean host;
    private boolean active;

    public Player(String name) {
        this.name = name;
    }

    public void setHost(boolean host) {
        this.host = host;
    }

    public void setActive(boolean active) {
        this.active = active;
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
        String returnString =  " Name: "+this.name + "  Score: "+this.getScore();
        if(host)
            returnString = "[Active]"+returnString;
        if(active){
            returnString = "[Host]"+returnString;
        }
        return returnString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
