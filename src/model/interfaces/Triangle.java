package model.interfaces;

import model.Point;

import java.awt.*;

public class Triangle implements IShape {

    private final java.awt.Point startPoint;
    private final java.awt.Point endPoint;

    public Triangle(java.awt.Point startPoint, java.awt.Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    @Override
    public void draw(Graphics2D g) {

    }

    @Override
    public Point getStartPoint() {
        return null;
    }

    @Override
    public Point getEndPoint() {
        return null;
    }
}
