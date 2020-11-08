package model;

import model.interfaces.IShape;
import model.persistence.ApplicationState;
import model.Point;

import java.awt.*;
//import java.awt.Point;

public class Shape {
//    private final Point startPoint;
//    private final Point endPoint;
    public Point startPoint;
    public Point endPoint;
//    private final Color pColor;
    public Color pColor;
    public Color sColor;
//    private final ShapeShadingType shadingType;
//    private final ShapeType shapeType;
    public ShapeShadingType shadingType;
    public ShapeType shapeType;
    ApplicationState appState;
    public boolean shapeSelected = false;
    public boolean undoPerformered = false;

    int deltaX;
    int deltaY;

//    Graphics2D g;



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
//            RectStrategy rect = new RectStrategy(startPoint,endPoint,appState,pColor,sColor,shadingType);
            rect.draw(g);
        }
        else if (shapeType==ShapeType.ELLIPSE){
            EllipStrategy ellip = new EllipStrategy(this);
//            EllipStrategy ellip = new EllipStrategy(startPoint,endPoint,appState,pColor,sColor,shadingType);
            ellip.draw(g);
        }
        else{
            TriStrategy tri = new TriStrategy(this);
//            TriStrategy tri = new TriStrategy(startPoint,endPoint,appState,pColor,sColor,shadingType);
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
        System.out.println("UndoMove Triggered!");
        this.setStartPoint(((int)this.getStartPoint().x)-deltaX, ((int)this.getStartPoint().y)-deltaY);
        this.setEndPoint(((int)this.getEndPoint().x)-deltaX, ((int)this.getEndPoint().y)-deltaY);
    }
    public void redoMove(){
        System.out.println("RedoMove Triggered!");
        this.setStartPoint(((int)this.getStartPoint().x)+deltaX, ((int)this.getStartPoint().y)+deltaY);
        this.setEndPoint(((int)this.getEndPoint().x)+deltaX, ((int)this.getEndPoint().y)+deltaY);
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
        int startX = (int)Math.min(this.startPoint.getX(), this.endPoint.getX());
        int endX = (int)Math.max(this.startPoint.getX(), this.endPoint.getX());
        int width = (int)endX - (int)startX;
        return width;
    }

    public int getHeight(){
        int startY = (int)Math.min(this.startPoint.getY(), this.endPoint.getY());
        int endY = (int)Math.max(this.startPoint.getY(), this.endPoint.getY());
        int height = (int)endY - (int)startY;
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

    public void setpColor(Color pColor){
        this.pColor = pColor;
    }

    public void setsColor(Color sColor){
        this.sColor = sColor;
    }

    public void shapeSelected(){
        shapeSelected = !shapeSelected;
        System.out.println("shapeSelected Boolean: " + shapeSelected);
    }
}
