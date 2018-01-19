package domain;

import GameServer.GameServer;
import Interfaces.IBrushProperties;
import Interfaces.IDrawing;
import Interfaces.IStroke;
import fontyspublisher.IRemotePropertyListener;
import fontyspublisher.IRemotePublisherForListener;
import fontyspublisher.RemotePublisher;

import java.awt.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Drawing extends UnicastRemoteObject implements IDrawing, IRemotePublisherForListener{
    private ArrayList<IStroke> strokes;
    private final static Logger LOGGER = Logger.getLogger(Drawing.class.getName());
    private RemotePublisher publisher;

    public Drawing() throws RemoteException {
        this.strokes = new ArrayList<>();
        try {
            publisher = new RemotePublisher();
        } catch (RemoteException e) {
            System.out.println("Publisher failed to instantiate.");
            System.out.println("Remote exception: " + e.getMessage());
            return;
        }
        publisher.registerProperty("stroke");
        publisher.registerProperty("clear");
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

            publisher.inform("stroke", null, getLastStroke());

        } catch (RemoteException e) {
            LOGGER.log(Level.WARNING,e.toString(),e);
        }
    }

    public void clear(){
        this.strokes = new ArrayList<>();
        try {
            publisher.inform("clear", null,true);
        } catch (RemoteException e) {
            LOGGER.log(Level.WARNING,e.toString(),e);
        }
    }

    @Override
    public void subscribeRemoteListener(IRemotePropertyListener listener, String property) throws RemoteException {
        publisher.subscribeRemoteListener(listener, property);
    }

    @Override
    public void unsubscribeRemoteListener(IRemotePropertyListener listener, String property) throws RemoteException {
        publisher.unsubscribeRemoteListener(listener, property);
    }
}
