package model;

import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoable;

import java.util.ArrayList;

public class DeleteCommand implements ICommand, IUndoable {

    private ShapeList shapeList;


    public DeleteCommand(ShapeList shapeList){

        this.shapeList = shapeList;
        ArrayList<IShape> myDeletedShapeList = shapeList.getDeletedShapeList();
    }

    @Override
    public void run() {
        System.out.println("Delete Command called");
        ArrayList<IShape> myShapeList = shapeList.getShapeList();
        ArrayList<IShape> mySelectedShapeList = shapeList.getSelectedShapeList();

        if(mySelectedShapeList.size()==0) {
            System.out.println("The SelectShapeList is empty! Nothing to delete");
        }
        else if (mySelectedShapeList.size()!=0){
            for(IShape s: mySelectedShapeList){
                System.out.println("Removing shape: " + s);
                shapeList.removeSpecificShape(s);
            }
            mySelectedShapeList.clear();
            shapeList.shapeListDrawer(myShapeList,mySelectedShapeList);
            CommandHistory.add(this);
        }

    }

    @Override
    public void undo() {
        shapeList.addDeletedShapes();

//        shapeList.shapeListDrawer(shapeList.getShapeList(),shapeList.getSelectedShapeList());
//        CommandHistory.add(this);
    }

    @Override
    public void redo() {
            System.out.println("Redo called");
        if(shapeList.getSelectedShapeList().size()==0) {
            System.out.println("The SelectShapeList is empty for redo!");
        }
        else if (shapeList.getSelectedShapeList().size()!=0){
            for(IShape s : shapeList.getSelectedShapeList()){
                shapeList.removeShape();
            }
            shapeList.shapeListDrawer(shapeList.getShapeList(),shapeList.getSelectedShapeList());
            System.out.println("Shape delete redone!");
            CommandHistory.add(this);
        }

    }
}
