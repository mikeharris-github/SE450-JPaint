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
                s.getShape().shapeSelected=false;
                System.out.println("Removed shape: " + s);
                System.out.println("Number of shapes in group: " + shapeGroup.getSize());
            }
            myShapeList.add(shapeGroup);
            selectedShapeList.add(shapeGroup);
        }
        System.out.println("Shapes are grouped!");
        shapeList.shapeListDrawer(myShapeList,selectedShapeList);
        selectedShapeList.clear();

        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        for(int i = 0;i<shapeGroup.getSize();i++){
            IShape s = shapeGroup.removeChild(i);
            shapeList.addShape(s);
        }
        shapeList.shapeListDrawer(shapeList.getShapeList(),shapeList.getSelectedShapeList());
    }

    @Override
    public void redo() {

    }
}
