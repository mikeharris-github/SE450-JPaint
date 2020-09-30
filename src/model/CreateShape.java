package model;

import model.interfaces.IShape;
import model.interfaces.ICommand;
import model.interfaces.IUndoable;

import java.awt.*;



public class CreateShape implements ICommand, IUndoable {

    //create a shape via the Ishape interface
    //add the shape to the ShapeList
    //invoke the draw command

    private Point startPoint;
    private Point endPoint;
    Graphics2D g;
    ShapeList shapeList;


    public CreateShape(Point startPoint, Point endPoint, Graphics2D g, ShapeList shapeList){
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.g = g;
        this.shapeList = shapeList;
    }


    public void run() {
        System.out.println("RUN EXECUTED");

        Rect o = null;
        Rect rect = new Rect(startPoint,endPoint);
        o = rect.immutableObject();

        System.out.println("here are o's info: \n" + o.startPoint.getX() + ", " + o.endPoint.getY());


//        Rect rect = new Rect(startPoint,endPoint,width,height);
        //        System.out.println("CreateShape Command Executed!");
//        rect.setStartPoint(startPoint);
//        rect.setEndPoint(endPoint);
//        rect.setG(g);
//        rect.setColor(Color.GREEN);
//        System.out.println("startPoint \nx: " + rect.getStartPoint().getX() + "; \ny: " + rect.getStartPoint().getY());
//        System.out.println("endPoint \nx: " + rect.getEndPoint().getX() + "; \ny: " + rect.getEndPoint().getY());

//        System.out.println("Rectangle Created");
//        add shape to ShapeList
//        System.out.println("about to test shapelist...");
        shapeList.addShape(o,g);
        CommandHistory.add(this);

        //add itself to the command history

    }


    @Override
    public void undo() {
    }

    @Override
    public void redo() {

    }
}
