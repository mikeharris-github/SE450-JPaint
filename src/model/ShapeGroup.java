package model;

import model.interfaces.IShape;

import java.awt.*;
import java.util.ArrayList;

public class ShapeGroup implements IShape {

    IShape shape;
    private ArrayList<IShape> children;

    ShapeGroup(){
        children = new ArrayList<IShape>();
//        this.shape = shape;
    }

    public void addChild(IShape shape){
        children.add(shape);
    }

    public int getSize(){
        int size = 0;
        for(IShape child : children){
            size++;
        }
        return size;
    }

    @Override
    public void draw(Graphics2D g) {

    }

    @Override
    public Point getStartPoint() {
        return null;
    }

    @Override
    public Point getEndPoint() {
        return null;
    }

    @Override
    public Shape getShape() {
        return null;
    }


}
