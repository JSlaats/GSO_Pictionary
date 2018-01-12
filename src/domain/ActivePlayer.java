package domain;

import Interfaces.IActivePlayer;
import Interfaces.IBrushProperties;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ActivePlayer extends UnicastRemoteObject implements IActivePlayer {
    private Player player;
    private BrushProperties brush;
    private String word;

    public ActivePlayer(Player player) throws RemoteException {
        super();
        this.player = player;
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

    public Player getPlayer() {
        return player;
    }

/*    public void setPlayer(Player player) {
        this.player = player;
    }*/

    public IBrushProperties getBrush() {
        return brush;
    }

    public void setBrush(IBrushProperties brush) {
        this.brush = (BrushProperties)brush;
    }
    public void setBrushWidth(int width){
        this.brush.setWidth(width);
    }

    public void setBrushColor(int r, int g, int b) {
        this.brush.setColor(r,g,b);
    }
}
