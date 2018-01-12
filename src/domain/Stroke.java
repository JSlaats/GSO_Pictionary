package domain;

import Interfaces.IBrushProperties;
import Interfaces.IStroke;

import java.awt.*;
import java.io.Serializable;

public class Stroke implements Serializable, IStroke{
    private Point position;
    private BrushProperties brush;

    public Stroke(Point position, BrushProperties brush) {
        this.position = position;
        this.brush = brush;
    }

    public Point getPosition() {
        return position;
    }

    public IBrushProperties getBrush() {
        return brush;
    }
}
