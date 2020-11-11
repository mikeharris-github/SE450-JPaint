package model;

import model.interfaces.IShape;

import java.awt.*;

public class ShapeFactory {
    public IShape makeShape(Shape shape) {
        IShape iShape;
        if (shape.shapeType==ShapeType.RECTANGLE){
//            System.out.println("RECTANGLE!");
            iShape = new RectStrategy(shape);
        }
        else if (shape.shapeType==ShapeType.ELLIPSE){
//            System.out.println("ELLIPSE!");
            iShape = new EllipStrategy(shape);
        }
        else{
//            System.out.println("Triangle!");
            iShape = new TriStrategy(shape);
        }
        return iShape;
    }
}
