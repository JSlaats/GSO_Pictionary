package domain;

import java.awt.*;

public class Stroke {
    private Point begin;
    private Point end;
    private BrushProperties brush;

    public Stroke(Point begin, Point end, BrushProperties brush) {
        this.begin = begin;
        this.end = end;
        this.brush = brush;
    }

    public Point getBegin() {
        return begin;
    }

    public void setBegin(Point begin) {
        this.begin = begin;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    public BrushProperties getBrush() {
        return brush;
    }

    public void setBrush(BrushProperties brush) {
        this.brush = brush;
    }
}
