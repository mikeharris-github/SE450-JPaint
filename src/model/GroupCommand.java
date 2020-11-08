package model;


import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoable;

import java.util.ArrayList;

public class GroupCommand implements ICommand, IUndoable {

    ShapeList shapeList;
    ShapeGroup shapeGroup;
    ArrayList<Shape> tempGroupList;

    public GroupCommand(ShapeList shapeList){
        this.shapeList = shapeList;
    }

    @Override
    public void run() {
        System.out.println("Group Command Pressed!");
        ArrayList<IShape> myShapeList = shapeList.getShapeList();
        ArrayList<IShape> selectedShapeList = shapeList.getSelectedShapeList();

        if(selectedShapeList.size()!= 0){
            //create a new shapeGroup
            shapeGroup = new ShapeGroup();
            for(IShape s : selectedShapeList){
                //remove shape from shapelist
//                selectedShapeList.remove(s);
                //remove shape from main shapelist
                myShapeList.remove(s);
                //add shape to group as child
                shapeGroup.addChild(s);
                System.out.println("Removed shape: " + s);
                System.out.println("Number of shapes in group: " + shapeGroup.getSize());
            }
            myShapeList.add(shapeGroup);
        }
        System.out.println("Shapes are grouped!");
        shapeList.shapeListDrawer(myShapeList,selectedShapeList);
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        for(Shape s: tempGroupList){
            tempGroupList.remove(s);
        }

    }

    @Override
    public void redo() {

    }
}
