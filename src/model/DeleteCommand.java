package model;

import model.interfaces.ICommand;
import model.interfaces.IUndoable;

import java.util.Stack;

public class DeleteCommand implements ICommand, IUndoable {

    private ShapeList shapeList;


    public DeleteCommand(ShapeList shapeList){
        this.shapeList = shapeList;
    }

    @Override
    public void run() {
        System.out.println("Delete Command called");
        if(shapeList.getSelectedShapeList().size()==0) {
            System.out.println("The SelectShapeList is empty!");
        }
        else if (shapeList.getSelectedShapeList().size()!=0){
            for(Shape s : shapeList.getSelectedShapeList()){
                shapeList.removeShape();
            }
            shapeList.shapeListDrawer(shapeList.getShapeList());
            System.out.println("Shape deleted!");
            CommandHistory.add(this);
        }

    }

    @Override
    public void undo() {
        shapeList.getSelectedShapeList();
        for(Shape s: shapeList.getSelectedShapeList()){
            shapeList.addDeletedShapes();
        }
        shapeList.shapeListDrawer(shapeList.getShapeList());
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
            shapeList.shapeListDrawer(shapeList.getShapeList());
            System.out.println("Shape delete redone!");
            CommandHistory.add(this);
        }

    }
}
