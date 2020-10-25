package model;

import model.persistence.ApplicationState;

import java.awt.*;
import java.awt.Point;

public class Shape /*implements IShape*/ {
    private final java.awt.Point startPoint;
    private final java.awt.Point endPoint;
    private final Color pColor;
    private final Color sColor;
    private final ShapeShadingType shadingType;
    private final ShapeType shapeType;
    ApplicationState appState;
    boolean shapeSelected = false;
    boolean undoPerformered = false;

    int deltaX;
    int deltaY;

//    Graphics2D g;



    Shape(java.awt.Point startPoint, java.awt.Point endPoint, ApplicationState appState, Color pColor, Color sColor, ShapeShadingType shadingType, ShapeType shapeType) {
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
            RectStrategy rect = new RectStrategy(startPoint,endPoint,appState,pColor,sColor,shadingType);
            rect.draw(g);
        }
        else if (shapeType==ShapeType.ELLIPSE){
            EllipStrategy ellip = new EllipStrategy(startPoint,endPoint,appState,pColor,sColor,shadingType);
            ellip.draw(g);
        }
        else{
            TriStrategy tri = new TriStrategy(startPoint,endPoint,appState,pColor,sColor,shadingType);
            tri.draw(g);
        }
    }


    public void move(int deltaX, int deltaY){
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.setStartPoint(((int)this.getStartPoint().x)+deltaX, ((int)this.getStartPoint().y)+deltaY);
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

    public void shapeSelected(){
        shapeSelected = !shapeSelected;
        System.out.println("shapeSelected Boolean: " + shapeSelected);
    }
}


//    Shape(ApplicationState appState, Point startPoint, Point endPoint){
//        this.appState = appState;
//        selectedPrimaryColor = appState.getActivePrimaryColor();
//        this.startPoint = startPoint;
//        this.endPoint = endPoint;
//    }

//    @Override
//    public void setStartPoint(Point startPoint) {
//    }
//
//    @Override
//    public void setEndPoint(Point endPoint) {
//
//    }
//
//    @Override
//    public void setG(Graphics2D g) {
//
//    }
//
//    @Override
//    public void setColor(Color color) {
//
//    }
//
//    @Override
//    public void draw() {
//
//    }
//

//
//    @Override
//    public Graphics2D getG() {
//        return null;
//    }
//
//    @Override
//    public Color getColor() {
//        return null;
//    }
//
//    @Override
//    public void draw(Graphics2D g) {
//
//    }
//}

//        //option2
//        if(shadingType == ShapeShadingType.FILLED_IN){
//            if (shapeType==ShapeType.ELLIPSE){
//                g.fillOval(startX,startY,width, height);}
//            else if(shapeType==ShapeType.TRIANGLE) {
//                int startArray[] = new int[3];
//                int endArray[] = new int[3];
//                startArray[0] = (int)startPoint.getX();
//                startArray[1] = (int)endPoint.getX();
//                startArray[2] = (int)newPoint.getX();
//                endArray[0] = (int)startPoint.getY();
//                endArray[1] = (int)endPoint.getY();
//                endArray[2] = (int)newPoint.getY();
//                g.fillPolygon(startArray,endArray,3);}
//            else{g.fillRect(startX,startY,width, height);}
//        }
//        else if (shadingType == ShapeShadingType.OUTLINE){
//            if (shapeType==ShapeType.ELLIPSE){
//                g.drawOval(startX,startY,width, height);}
//            else if(shapeType==ShapeType.TRIANGLE) {
//                int startArray[] = new int[3];
//                int endArray[] = new int[3];
//                startArray[0] = (int)startPoint.getX();
//                startArray[1] = (int)endPoint.getX();
//                startArray[2] = (int)newPoint.getX();
//                endArray[0] = (int)startPoint.getY();
//                endArray[1] = (int)endPoint.getY();
//                endArray[2] = (int)newPoint.getY();
//                g.drawPolygon(startArray,endArray,3);}
//            else{g.drawRect(startX,startY,width, height);}
//        }
//        else {
//            System.out.println("ShapeShadingType is OUTLINE AND FILLED IN");
//            g.setColor(sColor);
////            g.drawOval(startX, startY, width, height);
////            g.setColor(pColor);
////            g.fillOval(startX, startY, width, height);
////
//            if (shapeType==ShapeType.ELLIPSE){
//                g.drawOval(startX, startY, width, height);
//                g.setColor(pColor);
//                g.fillOval(startX,startY,width, height);}
//            else if(shapeType==ShapeType.TRIANGLE) {
//                int startArray[] = new int[3];
//                int endArray[] = new int[3];
//                startArray[0] = (int)startPoint.getX();
//                startArray[1] = (int)endPoint.getX();
//                startArray[2] = (int)newPoint.getX();
//                endArray[0] = (int)startPoint.getY();
//                endArray[1] = (int)endPoint.getY();
//                endArray[2] = (int)newPoint.getY();
//                g.drawPolygon(startArray,endArray,3);
//                g.setColor(pColor);
//                g.fillPolygon(startArray,endArray,3);}
//            else{
//                g.drawRect(startX,startY,width, height);
//                g.setColor(pColor);
//                g.fillRect(startX,startY,width, height);
//            }

