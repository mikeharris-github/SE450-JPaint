package model;

import model.interfaces.IShape;
import model.persistence.ApplicationState;

import java.awt.*;
//import java.awt.Point;
import model.Point;


final class RectStrategy implements IShape {

    Shape shape;
    ApplicationState appState;
    RectStrategy(Shape shape){
        this.shape = shape;
    }

    @Override
    public void draw(Graphics2D g) {

        int startX = Math.min(shape.startPoint.getX(), shape.endPoint.getX());
        int endX = Math.max(shape.startPoint.getX(), shape.endPoint.getX());
        int startY = Math.min(shape.startPoint.getY(), shape.endPoint.getY());
        int endY = Math.max(shape.startPoint.getY(), shape.endPoint.getY());

        int width = endX - startX;
        int height = endY - startY;

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

    @Override
    public boolean isGroup() {
        return false;
    }


}
