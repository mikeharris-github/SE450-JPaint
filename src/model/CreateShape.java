package model;

import model.interfaces.IShape;
import model.interfaces.ICommand;
import java.awt.*;


public class CreateShape implements ICommand {

    //create a shape via the Ishape interface
    //add the shape to the ShapeList
    //invoke the draw command

    private static Point startPoint;
    private static Point endPoint;
    Graphics2D g;
    ShapeList shapeList;


    public CreateShape(Point startPoint, Point endPoint, Graphics2D g, ShapeList shapeList){
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.g = g;
        this.shapeList = shapeList;

    }


    public void run() {

        int width = 0;
        int height=0;
        Rect rect = new Rect();
        //        System.out.println("CreateShape Command Executed!");
        rect.setStartPoint(startPoint);
        rect.setEndPoint(endPoint);
        rect.setG(g);
        rect.setColor(Color.GREEN);


//        rect.toRect();
//        System.out.println("startPoint: " + rect.getStartPoint().getX() + "; y: " + rect.getStartPoint().getY());
//        System.out.println("Rectangle Created");
//        add shape to ShapeList
//        System.out.println("about to test shapelist...");
        shapeList.addShape(rect,g);
//        shapeList.shapeListCount();

        //add itself to the command history

    }



}
