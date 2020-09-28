package model;

import model.interfaces.ICommand;
import model.Point;

import java.awt.*;
import java.io.IOException;

public class DrawCommand implements ICommand {

    Point startPoint;
    Point endPoint;
    Graphics2D g;

    public DrawCommand(Graphics2D g, Point startPoint, Point endPoint){
        this.g = g;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }


    @Override
    public void run() throws IOException {
        System.out.println("Draw Command Executed!");
        g.setColor(Color.green);

        int startX = Math.min(startPoint.getX(), endPoint.getX());
        int endX = Math.max(startPoint.getX(), endPoint.getX());
        int startY = Math.min(startPoint.getY(), endPoint.getY());
        int endY = Math.max(startPoint.getY(), endPoint.getY());

        int width = endX - startX;
        int height = endY - startY;


        g.setColor(Color.GREEN);
        g.fillRect(startX,startY,width, height);

        //add to ShapeList

    }
}
