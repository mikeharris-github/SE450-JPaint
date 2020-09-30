//package model;
//
//import model.interfaces.IShape;
//import model.Point;
//
//import java.awt.*;
//
//public class ShapeFactory {
//
//    Point startPoint;
//    Point endPoint
//    Graphics2D g;
//    Color color;
//
//    int width;
//    int height;
//
//    public ShapeFactory() {}
//
//    public static IShape makeRect(Point startPoint, Point endPoint, Graphics2D g, Color g){
//        System.out.println("Factor called!");
//
//        int startX = Math.min(startPoint.getX(), endPoint.getX());
//        int endX = Math.max(startPoint.getX(), endPoint.getX());
//        int startY = Math.min(startPoint.getY(), endPoint.getY());
//        int endY = Math.max(startPoint.getY(), endPoint.getY());
//
//        int width = endX - startX;
//        int height = endY - startY;
//        g.setColor(Color.GREEN);
//    }
//
//
//
//}
