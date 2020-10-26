package model;

import model.interfaces.ICommand;
import model.interfaces.IUndoable;
import model.persistence.ApplicationState;

import java.awt.Point;

public class MoveShape implements ICommand, IUndoable {

    private java.awt.Point startPoint;
    private java.awt.Point endPoint;
    private ShapeList shapeList;
    ApplicationState appState;

    private int deltaX;
    private int deltaY;

    public MoveShape(ApplicationState appState, java.awt.Point startPoint, Point endPoint, ShapeList shapeList){
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.shapeList = shapeList;
        this.appState = appState;

        //determine delta
        deltaX = (int)endPoint.getX()-(int)startPoint.getX();
        deltaY = (int)endPoint.getY()-(int)startPoint.getY();
    }

    @Override
    public void run() {
        //if ShapeList == NULL, throw an error!
        if(shapeList.getSelectedShapeList().size()==0) {
            System.out.println("The SelectShapeList is empty!");
        }
        else if (shapeList.getSelectedShapeList().size()!=0){
            for(Shape s : shapeList.getSelectedShapeList()){
                s.move(deltaX,deltaY);
            }
            shapeList.shapeListDrawer(shapeList.getShapeList(),shapeList.getSelectedShapeList());
            CommandHistory.add(this);
        }

    }


    @Override
    public void undo() {
        System.out.println("UNDO called");
        shapeList.getSelectedShapeList();
        for(Shape s: shapeList.getSelectedShapeList()){
            s.undoMove();
        }
        shapeList.shapeListDrawer(shapeList.getShapeList(),shapeList.getSelectedShapeList());
//        CommandHistory.add(this);
    }

    @Override
    public void redo() {
        System.out.println("Redo Called");
        shapeList.getSelectedShapeList();
        for(Shape s: shapeList.getSelectedShapeList()){
            s.redoMove();
        }
        shapeList.shapeListDrawer(shapeList.getShapeList(),shapeList.getSelectedShapeList());
//        CommandHistory.add(this);
    }
}
