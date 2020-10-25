package model;

import model.interfaces.IShape;
import model.persistence.ApplicationState;

import java.awt.*;
import java.awt.Point;

final class RectStrategy implements IShape {

    private final Point startPoint;
    private final Point endPoint;
    private final Color pColor;
    private final Color sColor;
    private final ShapeShadingType shadingType;
    ApplicationState appState;

    RectStrategy(Point startPoint, Point endPoint, ApplicationState appState, Color pColor, Color sColor, ShapeShadingType shadingType) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.appState = appState;
        this.pColor = pColor;
        this.sColor = sColor;
        this.shadingType = shadingType;
    }

    @Override
    public void draw(Graphics2D g) {

        int startX = (int)Math.min(this.startPoint.getX(), this.endPoint.getX());
        int endX = (int)Math.max(this.startPoint.getX(), this.endPoint.getX());
        int startY = (int)Math.min(this.startPoint.getY(), this.endPoint.getY());
        int endY = (int)Math.max(this.startPoint.getY(), this.endPoint.getY());

        int width = (int)endX - (int)startX;
        int height = (int)endY - (int)startY;
//
//
        g.setColor(pColor);


        if(shadingType == ShapeShadingType.FILLED_IN){
//            System.out.println("ShapeShadingType is FILLED_IN");
            g.fillRect(startX,startY,width, height);
        }
        else if (shadingType == ShapeShadingType.OUTLINE){
//            System.out.println("ShapeShadingType is OUTLINE");
            g.drawRect(startX,startY,width, height);
        }
        else {
//            System.out.println("ShapeShadingType is OUTLINE AND FILLED IN");
            g.setColor(sColor);
            g.drawRect(startX, startY, width, height);
            g.setColor(pColor);
            g.fillRect(startX, startY, width, height);
        }


//            g.setColor(Color.green);
//        g.fillRect(startX,startY,width, height);
//        System.out.println("final test: " + (int)startX + ", " + (int)startY);
//        System.out.println("final test: " + (int)endX + ", " + (int)endY);


    }

    @Override
    public model.Point getStartPoint() {
        return null;
    }

    @Override
    public model.Point getEndPoint() {
        return null;
    }

}
