package domain;

import GameServer.GameServer;
import Interfaces.IPlayer;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Player implements IPlayer, Serializable{
    private final static Logger LOGGER = Logger.getLogger(Player.class.getName());

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
        String returnString =  " Name: "+this.name + "  Score: "+this.getScore();
        try {
            if(GameServer.getInstance().getRoom().getActivePlayer().getPlayer().equals(this))
                returnString = "[Active]"+returnString;
            if(GameServer.getInstance().getRoom().getHost().equals(this)){
                returnString = "[Host]"+returnString;
            }
        } catch (RemoteException e) {
            LOGGER.log(Level.WARNING,e.toString(),e);
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
