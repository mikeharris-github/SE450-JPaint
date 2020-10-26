package model;

import model.interfaces.ICommand;
import model.interfaces.IUndoable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;

public class DeleteCommand implements ICommand, IUndoable {

    private ShapeList shapeList;


    public DeleteCommand(ShapeList shapeList){

        this.shapeList = shapeList;
        ArrayList<Shape> myDeletedShapeList = shapeList.getDeletedShapeList();
    }

    @Override
    public void run() {
        System.out.println("Delete Command called");
        ArrayList<Shape> myShapeList = shapeList.getShapeList();
        ArrayList<Shape> mySelectedShapeList = shapeList.getSelectedShapeList();

        if(mySelectedShapeList.size()==0) {
            System.out.println("The SelectShapeList is empty! Nothing to delete");
        }
        else if (mySelectedShapeList.size()!=0){
            for(Shape s: mySelectedShapeList){
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
            for(Shape s : shapeList.getSelectedShapeList()){
                shapeList.removeShape();
            }
            shapeList.shapeListDrawer(shapeList.getShapeList(),shapeList.getSelectedShapeList());
            System.out.println("Shape delete redone!");
            CommandHistory.add(this);
        }

    }
}
