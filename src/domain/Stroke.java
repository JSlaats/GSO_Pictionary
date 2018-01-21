package domain;

import Interfaces.IBrushProperties;
import Interfaces.IStroke;

import java.awt.*;
import java.io.Serializable;
import java.util.Objects;

public class Stroke implements Serializable, IStroke {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stroke stroke = (Stroke) o;
        return Objects.equals(position, stroke.position) &&
                Objects.equals(brush, stroke.brush);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, brush);
    }
}
