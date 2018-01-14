package domain;


import Interfaces.IBrushProperties;

import java.io.Serializable;

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

    public BrushProperties(int width,int red, int green, int blue){
        this.width = width;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
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

    public void setColor(int red, int green, int blue){
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

}
