package model;

import model.interfaces.IShape;
import model.persistence.ApplicationState;

import java.awt.*;
//import java.awt.Point;
import model.Point;

final class TriStrategy implements IShape {

//    private final Point startPoint;
//    private final Point endPoint;
//    private final Color pColor;
//    private final Color sColor;
//    private final ShapeShadingType shadingType;
    ApplicationState appState;
    Shape shape;

    TriStrategy(Shape shape) {
        this.shape = shape;
//        this.startPoint = startPoint;
//        this.endPoint = endPoint;
//        this.appState = appState;
//        this.pColor = pColor;
//        this.sColor = sColor;
//        this.shadingType = shadingType;
    }

    @Override
    public void draw(Graphics2D g) {

        Point newPoint = new Point(shape.startPoint.x, shape.endPoint.y);

//        System.out.println("startPoint.x = " + startPoint.x);
        int startArray[] = new int[3];
        int endArray[] = new int[3];

        startArray[0] = (int)shape.startPoint.getX();
        startArray[1] = (int)shape.endPoint.getX();
        startArray[2] = (int)newPoint.getX();

        endArray[0] = (int)shape.startPoint.getY();
        endArray[1] = (int)shape.endPoint.getY();
        endArray[2] = (int)newPoint.getY();

        g.setColor(shape.pColor);
        if(shape.shadingType == ShapeShadingType.FILLED_IN) {
            g.fillPolygon(startArray,endArray,3);
        }
        else if (shape.shadingType == ShapeShadingType.OUTLINE){
            g.drawPolygon(startArray,endArray,3);
        }
        else{
            g.setColor(shape.sColor);
            g.drawPolygon(startArray,endArray,3);
            g.setColor(shape.pColor);
            g.fillPolygon(startArray,endArray,3);
        }

    }

    @Override
    public model.Point getStartPoint() {
        return null;
    }

    @Override
    public model.Point getEndPoint() {
        return null;
    }

    @Override
    public Shape getShape() {
        return shape;
    }

    @Override
    public int getSize() {
        return 0;
    }


}
