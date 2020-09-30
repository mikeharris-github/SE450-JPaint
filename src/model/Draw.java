package model;

import model.interfaces.IShape;

import java.awt.*;


public class Draw {

    private final IShape shape;
    private final Graphics2D g;

    public Draw(Graphics2D g, IShape shape){
        this.shape = shape;
        this.g = g;
    }
}
