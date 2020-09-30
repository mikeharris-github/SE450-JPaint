package model;

import model.interfaces.ICommand;
import model.Point;
import model.interfaces.IShape;

import java.awt.*;

public class DrawCommand implements ICommand {

//    IShape shape;
    Point startPoint;
    Point endPoint;
    Graphics2D g;

    IShape shape;

//    public DrawCommand(Graphics2D g,IShape shape){
//        this.g = g;
//        this.shape = shape;
//        System.out.println("DrawCommand Function printout:\n Shape GetX: " + shape.getStartPoint().getX() + "; Shape GetY: " + shape.getStartPoint().getY());
//    }


    @Override
    public void run() {
        System.out.println("Draw Command Executed!");
//
//
//        int startX = Math.min(shape.;
//        int endX = Math.max(shape.getStartPoint().getX(), shape.getEndPoint().getX());
//        int startY = Math.min(shape.startPoint.getY(), shape.endPoint.getY());
//        int endY = Math.max(shape.startPoint.getY(), shape.endPoint.getY());
//
//        int width = endX - startX;
//        int height = endY - startY;
//
//
//        g.setColor(Color.GREEN);
//        g.fillRect(startX,startY,width, height);

        //add to ShapeList

    }

}
