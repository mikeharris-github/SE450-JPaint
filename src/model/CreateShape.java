package model;

import model.interfaces.ICommand;
import model.interfaces.IUndoable;
import model.persistence.ApplicationState;

import java.awt.Point;



public class CreateShape implements ICommand, IUndoable {

    private Point startPoint;
    private Point endPoint;
    private ShapeList shapeList;
    private ApplicationState appState;


    public CreateShape(Point startPoint, Point endPoint, ShapeList shapeList, ApplicationState appState){
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.shapeList = shapeList;
        this.appState = appState;
    }

    public void run() {

//        System.out.println("Start Points for x: " + this.startPoint.getX() + ", " + this.startPoint.getY());
        Rect rect = new Rect(startPoint,endPoint);
        System.out.println("The shape type is: " + appState.getActiveShapeType());
        shapeList.addShape(rect);
        CommandHistory.add(this);
    }


    @Override
    public void undo() {
    }

    @Override
    public void redo() {

    }
}

