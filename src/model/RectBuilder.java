//package model;
//
//public class RectBuilder {
//    private Point startPoint;
//    private Point endPoint;
//    private int width;
//    private int height;
//
//    int startX = Math.min(startPoint.getX(), endPoint.getX());
//    int endX = Math.max(startPoint.getX(), endPoint.getX());
//    int startY = Math.min(startPoint.getY(), endPoint.getY());
//    int endY = Math.max(startPoint.getY(), endPoint.getY());
//
//    int width = endX - startX;
//    int height = endY - startY;
//
//    public Rect toRect(){
//        return new Rect(startX,startY,width,height);
//    }
//
//
//    public RectBuilder() {}
//    public void setX(int x) {this.x = x;}
//    public void setY(int y) {this.y = y;}
//    public void setWidth(int width) {this.width=width;}
//    public void setHeight(int height) {this.height = height;}
//
//    public int getX() {return this.x = x;
//    }
//    public int getY() {return this.y = y;}
//    public int getWidth() {return this.width=width;}
//    public int getHeight() {return this.height = height;}
//
//
//}
