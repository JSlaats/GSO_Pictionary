package domain;

import java.awt.*;
import java.io.Serializable;

public class Stroke implements Serializable{
    private Point position;
    private BrushProperties brush;

    public Stroke(Point position, BrushProperties brush) {
        this.position = position;
        this.brush = brush;
    }

    public Point getPosition() {
        return position;
    }

    public BrushProperties getBrush() {
        return brush;
    }
}
