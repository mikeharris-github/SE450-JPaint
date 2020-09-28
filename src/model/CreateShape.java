package model;

import model.interfaces.ICommand;
import model.DrawCommand;
import javax.xml.stream.events.StartDocument;
import java.awt.*;
import java.io.IOException;

public class CreateShape implements ICommand {

    Point startPoint;
    Point endPoint;
    Graphics2D g;

    DrawCommand draw;


    public CreateShape(Point startPoint, Point endPoint){
        this.startPoint = startPoint;
        this.endPoint = endPoint;

        int startX = Math.min(startPoint.getX(), endPoint.getX());
        int endX = Math.max(startPoint.getX(), endPoint.getX());
        int startY = Math.min(startPoint.getY(), endPoint.getY());
        int endY = Math.max(startPoint.getY(), endPoint.getY());

        int width = endX - startX;
        int height = endY - startY;
    }

    @Override
    public void run() throws IOException {
        DrawCommand draw = new DrawCommand(g, startPoint, endPoint);

    }

    Rect rect = new Rect();

//    public class Rect {
//
//        private int x;
//        private int y;
//        private int width;
//        private int height;
//
//        public Rect(int x, int y, int width, int height) {
//            this.x = x;
//            this.y = y;
//            this.width = width;
//            this.height = height;
//        }
//
//    }


}
