package model;

import model.interfaces.IShape;
import model.persistence.ApplicationState;

import java.awt.*;
import java.awt.Point;

final class TriStrategy implements IShape {

    private final Point startPoint;
    private final Point endPoint;
    private final Color pColor;
    private final Color sColor;
    private final ShapeShadingType shadingType;
    ApplicationState appState;

    TriStrategy(Point startPoint, Point endPoint, ApplicationState appState, Color pColor, Color sColor, ShapeShadingType shadingType) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.appState = appState;
        this.pColor = pColor;
        this.sColor = sColor;
        this.shadingType = shadingType;
    }

    @Override
    public void draw(Graphics2D g) {

        Point newPoint = new Point(startPoint.x, endPoint.y);

        System.out.println("startPoint.x = " + startPoint.x);
        int startArray[] = new int[3];
        int endArray[] = new int[3];

        startArray[0] = (int)startPoint.getX();
        startArray[1] = (int)endPoint.getX();
        startArray[2] = (int)newPoint.getX();

        endArray[0] = (int)startPoint.getY();
        endArray[1] = (int)endPoint.getY();
        endArray[2] = (int)newPoint.getY();

        g.setColor(pColor);
        if(shadingType == ShapeShadingType.FILLED_IN) {
            g.fillPolygon(startArray,endArray,3);
        }
        else if (shadingType == ShapeShadingType.OUTLINE){
            g.drawPolygon(startArray,endArray,3);
        }
        else{
            g.setColor(sColor);
            g.drawPolygon(startArray,endArray,3);
            g.setColor(pColor);
            g.fillPolygon(startArray,endArray,3);
        }



//        g.fillPolygon(startArray,endArray,3);

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
