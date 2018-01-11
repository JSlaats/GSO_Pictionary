package domain;

import Interfaces.IDrawing;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Drawing extends UnicastRemoteObject implements IDrawing {
    private ArrayList<Stroke> strokes;

    public Drawing() throws RemoteException {
        this.strokes = new ArrayList<>();
    }

    public Drawing(ArrayList<Stroke> strokes)throws RemoteException {
        this.strokes = strokes;
    }

    public ArrayList<Stroke> getStrokes() {
        return strokes;
    }

    public void setStrokes(ArrayList<Stroke> strokes) {
        this.strokes = new ArrayList<Stroke>(strokes);
    }

    public void setStroke(Stroke stroke){
        this.strokes.add(stroke);
    }
    public void clear(){
        this.strokes.clear();
    }
}
