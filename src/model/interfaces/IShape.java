package model.interfaces;

import model.Point;
import java.awt.*;

public interface IShape {

    void setStartPoint(Point startPoint);
    void setEndPoint(Point endPoint);
    void setG(Graphics2D g);
    void setColor(Color color);

    void draw();
}
