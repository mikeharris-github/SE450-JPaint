package model;

import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import model.persistence.ApplicationState;

public class MoveShape implements ICommand, IUndoable {

    private Point startPoint;
    private Point endPoint;
    private ShapeList shapeList;
    ApplicationState appState;

    private int deltaX;
    private int deltaY;

    public MoveShape(ApplicationState appState, Point startPoint, Point endPoint, ShapeList shapeList){
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
        else if (shapeList.getSelectedShapeList().size()>0){
            for(IShape s : shapeList.getSelectedShapeList()){
                if(s.getSize()>0){
                    System.out.println("Holy cow! i think this is a group!");

                }
                else{
                    s.getShape().move(deltaX,deltaY);
                }
            }
            shapeList.shapeListDrawer(shapeList.getShapeList(),shapeList.getSelectedShapeList());
            CommandHistory.add(this);
        }

    }


    @Override
    public void undo() {
        System.out.println("UNDO called");
        shapeList.getSelectedShapeList();
        for(IShape s: shapeList.getSelectedShapeList()){
            s.getShape().undoMove();
        }
        shapeList.shapeListDrawer(shapeList.getShapeList(),shapeList.getSelectedShapeList());
//        CommandHistory.add(this);
    }

    @Override
    public void redo() {
        System.out.println("Redo Called");
        shapeList.getSelectedShapeList();
        for(IShape s: shapeList.getSelectedShapeList()){
            s.getShape().redoMove();
        }
        shapeList.shapeListDrawer(shapeList.getShapeList(),shapeList.getSelectedShapeList());
//        CommandHistory.add(this);
    }
}
