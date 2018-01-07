package domain;

import javafx.scene.paint.Color;

public class BrushProperties {
    private int width;
    private Color color;

    public BrushProperties() {
        this.width = 2;
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
