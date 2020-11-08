package model.interfaces;

import model.Point;
import java.awt.*;
import model.Shape;

public interface IShape {

    void draw(Graphics2D g);
    Point getStartPoint();
    Point getEndPoint();
    Shape getShape();
    int getSize();
}
