package model;

import model.interfaces.IShape;

import java.awt.*;

final class Rect implements IShape {


    private Point startPoint;
    private Point endPoint;
    int width;
    int height;
    Graphics2D g;

    public void Rect(Point startPoint, Point endPoint, int width, int height) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.width = width;
        this.height = height;

    }

    public void draw() {
        g.setColor(Color.GREEN);

        int startX = Math.min(startPoint.getX(), endPoint.getX());
        int endX = Math.max(startPoint.getX(), endPoint.getX());
        int startY = Math.min(startPoint.getY(), endPoint.getY());
        int endY = Math.max(startPoint.getY(), endPoint.getY());

        int width = endX - startX;
        int height = endY - startY;

        g.fillRect(startX,startY,width, height);
    }

    @Override
    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }
    @Override
    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    @Override
    public void setG(Graphics2D g) {
        this.g = g;
    }

    @Override
    public void setColor(Color color) { }

//    int startX = Math.min(startPoint.getX(), endPoint.getX());
//    int endX = Math.max(startPoint.getX(), endPoint.getX());
//    int startY = Math.min(startPoint.getY(), endPoint.getY());
//    int endY = Math.max(startPoint.getY(), endPoint.getY());

//    int newWidth = endX - startX;
//    int newHeight = endY - startY;
//
//    Point newStartPoint = new Point(startX,startY);
//    Point newEndPoint = new Point(endX,endY);


//    public Rect toRect(){
//        return new Rect(newStartPoint,newEndPoint,newWidth,newHeight);
//    }

}