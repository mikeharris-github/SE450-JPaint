package model;

import model.interfaces.IShape;

import java.awt.*;
import java.util.ArrayList;

public class ShapeGroup implements IShape {

    IShape shape;
    public ArrayList<IShape> children;
    Graphics2D g;
    boolean groupSelected;
    int deltaX;
    int deltaY;
//    boolean isGroup = true;

    ShapeGroup(){
        children = new ArrayList<IShape>();
    }

    public ArrayList<IShape> getChildren(){
        return children;
    }

    public void addChild(IShape shape){
        children.add(shape);
    }

    public IShape removeChild(int i){
        System.out.println("REMOVE CHILD INDEX PASSED: " + i);
        IShape z = children.remove(i);
        System.out.println("RETURNING CHILD: " + i);
        return z;
    }
//
//    public void removeAllChildren(){
//        for(IShape s: children){
//            removeChild(this);
//            ShapeList.shapeList.add(this);
//        }
//    }

    public void moveChildren(int deltaX, int deltaY){
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        for(IShape c : children){
            c.getShape().setStartPoint((c.getShape().getStartPoint().x)+deltaX, (c.getShape().getStartPoint().y)+deltaY);
            c.getShape().setEndPoint((c.getShape().getEndPoint().x)+deltaX, (c.getShape().getEndPoint().y)+deltaY);
        }
    }

    public void undoMoveChildren(){
        System.out.println("UndoMoveChildren Triggered!");
        for(IShape c : children){
            c.getShape().setStartPoint((c.getShape().getStartPoint().x)-deltaX, (c.getShape().getStartPoint().y)-deltaY);
            c.getShape().setEndPoint((c.getShape().getEndPoint().x)-deltaX, (c.getShape().getEndPoint().y)-deltaY);
        }
    }

    public void redoMoveChildren(){
        for(IShape c : children){
            c.getShape().setStartPoint((c.getShape().getStartPoint().x)+deltaX, (c.getShape().getStartPoint().y)+deltaY);
            c.getShape().setEndPoint((c.getShape().getEndPoint().x)+deltaX, (c.getShape().getEndPoint().y)+deltaY);
        }
    }

    public int getSize(){
        int size = 0;
        for(IShape child : children){
            size++;
        }
        return size;
    }



    public Point getMinXY(){
        int shapeStartX = 9999;
        int shapeStartY = 9999;
        for(IShape child : children){
            if(child.getShape().getStartPoint().x<shapeStartX){
//                System.out.println("HOLY TOLEDO!");
                shapeStartX = child.getShape().getStartPoint().x;
//                System.out.println("shapeStartX: " + shapeStartX);
            }
            if(child.getShape().getStartPoint().y<shapeStartY){
//                System.out.println("HOLY TOLEDO!");
                shapeStartY = child.getShape().getStartPoint().y;
//                System.out.println("shapeStartY: " + shapeStartY);

            }

        }
        Point minXY = new Point(shapeStartX,shapeStartY);
        return minXY;
    }

    public Point getMaxXY(){
        int shapeEndX = 0;
        int shapeEndY = 0;
        for(IShape child: children){
            if(child.getShape().getEndPoint().x>shapeEndX){
//                System.out.println("HOLY TOLEDO!");
                shapeEndX = child.getShape().getEndPoint().x;
//                System.out.println("shapeEndX: " + shapeEndX);
            }
            if(child.getShape().getEndPoint().y>shapeEndY){
//                System.out.println("HOLY TOLEDO!");
                shapeEndY = child.getShape().getEndPoint().y;
//                System.out.println("shapeEndY: " + shapeEndY);
            }
        }
        Point maxXY = new Point(shapeEndX,shapeEndY);
        return maxXY;
    }

    @Override
    public void drawChildren(Graphics2D g) {
        this.g = g;
        for(IShape s:children){
            if(s.getSize()>0){
                s.drawChildren(g);
            }
            else{
                s.draw(g);
            }
        }
    }

    @Override
    public boolean isGroup() {
        return true;
    }

    public boolean groupSelected() {
        groupSelected = !groupSelected;
        return groupSelected;
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

    @Override
    public ShapeGroup getGroup() {
        return this;
    }


}
