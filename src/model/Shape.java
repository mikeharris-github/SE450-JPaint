package model;

import model.interfaces.IShape;
import model.persistence.ApplicationState;
import model.Point;
import java.awt.*;

public class Shape {
    public Point startPoint;
    public Point endPoint;
    public Color pColor;
    public Color sColor;
    public ShapeShadingType shadingType;
    public ShapeType shapeType;
    ApplicationState appState;
    public boolean shapeSelected = false;
    public boolean undoPerformered = false;
    public boolean isShape = true;
    public boolean shapePasted = false;

    int deltaX;
    int deltaY;

    Shape(Point startPoint, Point endPoint, ApplicationState appState, Color pColor, Color sColor, ShapeShadingType shadingType, ShapeType shapeType) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.appState = appState;
        this.pColor = pColor;
        this.sColor = sColor;
        this.shadingType = shadingType;
        this.shapeType = shapeType;
    }

    public void draw(Graphics2D g) {

        if (shapeType==ShapeType.RECTANGLE){
            RectStrategy rect = new RectStrategy(this);
            rect.draw(g);
        }
        else if (shapeType==ShapeType.ELLIPSE){
            EllipStrategy ellip = new EllipStrategy(this);
            ellip.draw(g);
        }
        else{
            TriStrategy tri = new TriStrategy(this);
            tri.draw(g);
        }
    }


    public void move(int deltaX, int deltaY){
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.setStartPoint((this.getStartPoint().x)+deltaX, ((int)this.getStartPoint().y)+deltaY);
        this.setEndPoint(((int)this.getEndPoint().x)+deltaX, ((int)this.getEndPoint().y)+deltaY);
    }

    public void undoMove(){
        this.setStartPoint(((int)this.getStartPoint().x)-deltaX, ((int)this.getStartPoint().y)-deltaY);
        this.setEndPoint(((int)this.getEndPoint().x)-deltaX, ((int)this.getEndPoint().y)-deltaY);
    }
    public void redoMove(){
        this.setStartPoint(((int)this.getStartPoint().x)+deltaX, ((int)this.getStartPoint().y)+deltaY);
        this.setEndPoint(((int)this.getEndPoint().x)+deltaX, ((int)this.getEndPoint().y)+deltaY);
    }

    public Point getMinXY(){
        int mouseStartX = Math.min(this.startPoint.getX(), this.endPoint.getX());
        int mouseStartY = Math.min(this.startPoint.getY(), this.endPoint.getY());

        Point point = new Point(mouseStartX,mouseStartY);
        return point;
    }

    public Point getMaxXY(){
        int mouseEndX = Math.max(this.startPoint.getX(), this.endPoint.getX());
        int mouseEndY = Math.max(this.startPoint.getY(), this.endPoint.getY());

        Point point = new Point(mouseEndX,mouseEndY);
        return point;
    }


    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public Color getpColor(){
        return pColor;
    }

    public Color getsColor(){
        return sColor;
    }

    public ShapeShadingType getShadingType() {
        return shadingType;
    }

    public ShapeType getShapeType() {
        return shapeType;
    }

    public int getWidth(){
        int startX = Math.min(this.startPoint.getX(), this.endPoint.getX());
        int endX = Math.max(this.startPoint.getX(), this.endPoint.getX());
        int width = endX - startX;
        return width;
    }

    public int getHeight(){
        int startY = Math.min(this.startPoint.getY(), this.endPoint.getY());
        int endY = Math.max(this.startPoint.getY(), this.endPoint.getY());
        int height = endY - startY;
        return height;
    }

    public void setStartPoint(int x, int y) {
        this.startPoint.x = x;
        this.startPoint.y = y;
    }

    public void setEndPoint(int x, int y) {
        this.endPoint.x = x;
        this.endPoint.y = y;
    }

    public void shapeSelected(){
        shapeSelected = !shapeSelected;
//        System.out.println("shapeSelected Boolean: " + shapeSelected);
    }
}
