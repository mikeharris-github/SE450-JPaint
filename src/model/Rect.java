package model;

import model.interfaces.IShape;

import java.awt.*;

public class Rect implements IShape {

    private Point startPoint = new Point(0,0);
    private Point endPoint = new Point(0,0);

    private int width;
    private int height;

    public Rect() {};

    @Override
    public void setStartPoint(int x, int y) { }
    @Override
    public void setEndPoint(int x, int y) { }

    public Point getStartPoint() { return startPoint; }
    public Point getEndPoint() { return endPoint; }

}





//**********
//**********
//**********
//**********

//    private static int x;
//    private static int y;
//    private static int width;
//    private static int height;

//    //Set Methods
//    public void setX(int x) {this.x = x;}
//    public void setY(int y) {this.y = y;}
//    public void setWidth(int width) {this.width=width;}
//    public void setHeight(int height) {this.height = height;}