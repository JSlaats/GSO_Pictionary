package domain;

import Interfaces.IActivePlayer;
import Interfaces.IBrushProperties;
import Interfaces.IPlayer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ActivePlayer extends UnicastRemoteObject implements IActivePlayer {
    private IPlayer player;
    private BrushProperties brush;
    private String word;

    public ActivePlayer(IPlayer player) throws RemoteException {
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

    public IPlayer getPlayer() {
        return player;
    }

    public IBrushProperties getBrush() {
        return brush;
    }

    public void setBrushWidth(int width){
        this.brush.setWidth(width);
    }

    public void setBrushColor(int red, int green, int blue) {
        this.brush.setColor(red,green,blue);
    }
}
