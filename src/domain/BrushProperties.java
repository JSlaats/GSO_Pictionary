package domain;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class BrushProperties {
    private int width;
    //private Color color;
    private Paint color;
    public BrushProperties() {
        this.width = 10;
        this.color = Color.BLACK;
    }

    public BrushProperties(int width, Color color) {
        this.width = width;
        this.color = color;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Paint getColor() {
        return color;
    }

    public void setColor(Paint color) {
        this.color = color;
    }
}
