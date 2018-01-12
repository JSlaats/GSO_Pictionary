package domain;

import Interfaces.IBrushProperties;

import java.io.Serializable;
import java.rmi.Remote;

public class BrushProperties implements Serializable, IBrushProperties {
    private int width;
    private int r,g,b;

    public BrushProperties() {
        this.width = 10;
        this.r = 0;
        this.g = 0;
        this.b = 0;
    }

    public BrushProperties(int width,int r, int g, int b){
        this.width = width;
        this.r = r;
        this.g = g;
        this.b = b;
    }
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getR() {
        return r;
    }

    public int getG() {
        return g;
    }

    public int getB() {
        return b;
    }

    public void setColor(int r, int g, int b){
        this.r = r;
        this.g = g;
        this.b = b;
    }

}
