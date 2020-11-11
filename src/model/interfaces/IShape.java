package model.interfaces;

import model.Point;
import java.awt.*;
import model.Shape;
import model.ShapeGroup;

public interface IShape {

    void draw(Graphics2D g);
    Point getStartPoint();
    Point getEndPoint();
    Shape getShape();
    ShapeGroup getGroup();
    int getSize();
    void drawChildren(Graphics2D g);
    boolean isGroup();
}
