//package model;
//
//import model.interfaces.IShape;
//
//import java.awt.*;
//import java.awt.Point;
//
//final class Rect implements IShape {
//
//    private final Point startPoint;
//    private final Point endPoint;
//    boolean shapeSelected;
//
//    Rect(Point startPoint, Point endPoint, boolean shapeSelected) {
//        this.startPoint = startPoint;
//        this.endPoint = endPoint;
//        this.shapeSelected = shapeSelected;
//    }
//
//    @Override
//    public void draw(Graphics2D g) {
//
//        int startX = (int)Math.min(this.startPoint.getX(), this.endPoint.getX());
//        int endX = (int)Math.max(this.startPoint.getX(), this.endPoint.getX());
//        int startY = (int)Math.min(this.startPoint.getY(), this.endPoint.getY());
//        int endY = (int)Math.max(this.startPoint.getY(), this.endPoint.getY());
//
//
//        int width = (int)endX - (int)startX;
//        int height = (int)endY - (int)startY;
////
////
//        g.setColor(Color.green);
//        g.fillRect(startX,startY,width, height);
////        System.out.println("final test: " + (int)startX + ", " + (int)startY);
////        System.out.println("final test: " + (int)endX + ", " + (int)endY);
//
//    }
//
//    @Override
//    public model.Point getStartPoint() {
//        return null;
//    }
//
//    @Override
//    public model.Point getEndPoint() {
//        return null;
//    }
//
//    @Override
//    public Shape getShape() {
//        return null;
//    }
//
//}
