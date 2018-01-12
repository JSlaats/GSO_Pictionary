package domain;

import GameServer.GameServer;
import Interfaces.IBrushProperties;
import Interfaces.IDrawing;
import Interfaces.IStroke;

import java.awt.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Drawing extends UnicastRemoteObject implements IDrawing {
    private ArrayList<IStroke> strokes;

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
            e.printStackTrace();
        }
    }

    public void clear(){
        this.strokes.clear();
    }
}
