package domain;

import java.util.ArrayList;

public class Drawing {
    private ArrayList<Stroke> strokes;

    public Drawing() {
        this.strokes = new ArrayList<>();
    }

    public Drawing(ArrayList<Stroke> strokes) {
        this.strokes = strokes;
    }

    public ArrayList<Stroke> getStrokes() {
        return strokes;
    }

    public void setStrokes(ArrayList<Stroke> strokes) {
        this.strokes = strokes;
    }

    public void setStroke(Stroke stroke){
        this.strokes.add(stroke);
    }
}
