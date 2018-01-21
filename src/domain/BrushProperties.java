package domain;


import Interfaces.IBrushProperties;

import java.io.Serializable;
import java.util.Objects;

public class BrushProperties implements Serializable, IBrushProperties {
    private int width;
    private int red;
    private int green;
    private int blue;

    public BrushProperties() {
        this.width = 10;
        this.red = 0;
        this.green = 0;
        this.blue = 0;
    }

    public BrushProperties(int width, int red, int green, int blue) {
        this.width = width;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        if (!(width < 1) && !(width > 25)) this.width = width;
    }

    public int getR() {
        return red;
    }

    public int getG() {
        return green;
    }

    public int getB() {
        return blue;
    }

    public void setColor(int red, int green, int blue) {
        if (red >= 0 && red <= 255 && green >= 0 && green <= 255 && blue >= 0 && blue <= 255) {
            this.red = red;
            this.green = green;
            this.blue = blue;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BrushProperties that = (BrushProperties) o;
        return width == that.width &&
                red == that.red &&
                green == that.green &&
                blue == that.blue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, red, green, blue);
    }
}
