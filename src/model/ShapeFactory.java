package model;

import model.interfaces.IShape;

import java.awt.*;

public class ShapeFactory {
    public IShape makeShape(Shape shape) {

        IShape iShape;

        if (shape.shapeType==ShapeType.RECTANGLE){
            System.out.println("RECTANGLE!");
            iShape = new RectStrategy(shape);

//            iShape = new RectStrategy(shape.startPoint,shape.endPoint,shape.appState,shape.pColor,shape.sColor,shape.shadingType);
//            RectStrategy rect = new RectStrategy(shape.startPoint,shape.endPoint,shape.appState,shape.pColor,shape.sColor,shape.shadingType);
        }
        else if (shape.shapeType==ShapeType.ELLIPSE){
            System.out.println("ELLIPSE!");
            iShape = new EllipStrategy(shape);
//             iShape = new EllipStrategy(shape.startPoint,shape.endPoint,shape.appState,shape.pColor,shape.sColor,shape.shadingType);
        }
        else{
            System.out.println("Triangle!");
            iShape = new TriStrategy(shape);

//            iShape = new TriStrategy(shape.startPoint,shape.endPoint,shape.appState,shape.pColor,shape.sColor,shape.shadingType);
        }
        System.out.println("Gonna return!");
        return iShape;
    }
}
