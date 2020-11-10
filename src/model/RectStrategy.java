package model;

import model.interfaces.IShape;
import model.persistence.ApplicationState;

import java.awt.*;
//import java.awt.Point;
import model.Point;


final class RectStrategy implements IShape {

//    private final Point startPoint;
//    private final Point endPoint;
//    private final Color pColor;
//    private final Color sColor;
//    private final ShapeShadingType shadingType;
    Shape shape;
    ApplicationState appState;

    RectStrategy(Shape shape){
        this.shape = shape;
    }
//    RectStrategy(Point startPoint, Point endPoint, ApplicationState appState, Color pColor, Color sColor, ShapeShadingType shadingType) {
//        this.startPoint = startPoint;
//        this.endPoint = endPoint;
//        this.appState = appState;
//        this.pColor = pColor;
//        this.sColor = sColor;
//        this.shadingType = shadingType;
//    }

    @Override
    public void draw(Graphics2D g) {

        int startX = (int)Math.min(shape.startPoint.getX(), shape.endPoint.getX());
        int endX = (int)Math.max(shape.startPoint.getX(), shape.endPoint.getX());
        int startY = (int)Math.min(shape.startPoint.getY(), shape.endPoint.getY());
        int endY = (int)Math.max(shape.startPoint.getY(), shape.endPoint.getY());

        int width = (int)endX - (int)startX;
        int height = (int)endY - (int)startY;
//
//
        g.setColor(shape.pColor);


        if(shape.shadingType == ShapeShadingType.FILLED_IN){
//            System.out.println("ShapeShadingType is FILLED_IN");
            g.fillRect(startX,startY,width, height);
        }
        else if (shape.shadingType == ShapeShadingType.OUTLINE){
//            System.out.println("ShapeShadingType is OUTLINE");
            g.drawRect(startX,startY,width, height);
        }
        else {
//            System.out.println("ShapeShadingType is OUTLINE AND FILLED IN");
            g.setColor(shape.sColor);
            g.drawRect(startX, startY, width, height);
            g.setColor(shape.pColor);
            g.fillRect(startX, startY, width, height);
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
    public ShapeGroup getGroup() {
        return null;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public void drawChildren(Graphics2D g) {

    }


}
