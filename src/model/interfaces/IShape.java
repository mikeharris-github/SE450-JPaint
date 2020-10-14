package model.interfaces;

import model.Point;
import java.awt.*;

public interface IShape {

    void draw(Graphics2D g);

    Point getStartPoint();

    Point getEndPoint();
}
