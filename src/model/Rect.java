package model;

import model.interfaces.IShape;

import java.awt.*;

final class Rect implements IShape {

    IShape shape = null;
    final Point startPoint;
    final Point endPoint;
//    private int width;
//    private int height;
//    Graphics2D g;


//    public Rect(Point startPoint, Point endPoint, int width, int height) {
//        this.startPoint = startPoint;
//        this.endPoint = endPoint;
//        this.width = width;
//        this.height = height;
//        return;
//    }

    public Rect(Point startPoint, Point endPoint){
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }
    final Rect immutableObject(){
        final Point sStartPoint = getStartPoint();
        final Point sEndPoint = getEndPoint();
        return new Rect(sStartPoint,sEndPoint);
    }

//    @Override
    public void draw(Graphics2D g) {

        g.setColor(Color.GREEN);

//        System.out.println("DRAW METHOD******");
        int startX = Math.min(startPoint.getX(), endPoint.getX());
//        System.out.println(startPoint.getX() + ", " + endPoint.getX());
//        System.out.println("Start X: " + startX);
        int endX = Math.max(startPoint.getX(), endPoint.getX());
//        System.out.println("End X: " + endX);

        int startY = Math.min(startPoint.getY(), endPoint.getY());
        int endY = Math.max(startPoint.getY(), endPoint.getY());

        int width = endX - startX;
        int height = endY - startY;

        g.fillRect(startX,startY,width, height);
    }
}

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

