package domain;

import java.awt.*;

public class Stroke {
    private Point position;
    private BrushProperties brush;

    public Stroke(Point position, BrushProperties brush) {
        this.position = position;
        this.brush = brush;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public BrushProperties getBrush() {
        return brush;
    }

    public void setBrush(BrushProperties brush) {
        this.brush = brush;
    }
}
