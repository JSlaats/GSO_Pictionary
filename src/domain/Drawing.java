package domain;

import GameServer.GameServer;
import Interfaces.IBrushProperties;
import Interfaces.IDrawing;
import Interfaces.IStroke;

import java.awt.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Drawing extends UnicastRemoteObject implements IDrawing {
    private ArrayList<IStroke> strokes;
    private final static Logger LOGGER = Logger.getLogger(Drawing.class.getName());

    public Drawing() throws RemoteException {
        this.strokes = new ArrayList<>();
    }

    public ArrayList<IStroke> getStrokes() {
        return new ArrayList<>(strokes);
    }


    public IStroke getLastStroke(){
        if(this.strokes.size()>0) {
            return this.strokes.get(this.strokes.size() - 1);
        }else{
            return null;
        }
    }

    public void setStroke(Point position) {
        try {
            IBrushProperties b = GameServer.getInstance().getRoom().getActivePlayer().getBrush();
            BrushProperties brush = new BrushProperties(b.getWidth(),b.getR(),b.getG(),b.getB());
            this.strokes.add(new Stroke(position,brush));
        } catch (RemoteException e) {
            LOGGER.log(Level.WARNING,e.toString(),e);
        }
    }

    public void clear(){
        this.strokes = new ArrayList<>();
    }

}
