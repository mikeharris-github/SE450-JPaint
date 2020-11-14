package model;


import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoable;

import java.util.ArrayList;

public class GroupCommand implements ICommand, IUndoable {

    ShapeList shapeList;
    ShapeGroup shapeGroup;
    int numChildren;


    public GroupCommand(ShapeList shapeList){
        this.shapeList = shapeList;
    }

    @Override
    public void run() {
        ArrayList<IShape> myShapeList = shapeList.getShapeList();
        ArrayList<IShape> selectedShapeList = shapeList.getSelectedShapeList();
        ArrayList<IShape> groupList = shapeList.getGroupList();

        //if the selectedShapeList is empty, end
        if(selectedShapeList.size() <= 0) { System.out.println("There's no selected shapes!"); }

        //if shapes are grouped
        else if(selectedShapeList.size()!= 0){
            //create new group
            shapeGroup = new ShapeGroup();
            groupList.add(shapeGroup);
            //remove shapes from selectedShapeList and add to group
            for(IShape s : selectedShapeList){
                //remove shape from main shapelist
                myShapeList.remove(s);
                //if current shape is shape, add to group
                if(s.isGroup()==false){
//                if(s.getSize()==0){
                    shapeGroup.addChild(s);
                    s.getShape().shapeSelected=false;
                    numChildren++;
                }
                //if current shape is group, add all shapes to new group
                else if(s.isGroup()==true){
                    s.getGroup().groupSelected=false;
                    shapeGroup.addChild(s);
                    numChildren++;
                }

            }
            myShapeList.add(shapeGroup);
            selectedShapeList.clear();
            selectedShapeList.add(shapeGroup);
        }
        shapeList.shapeListDrawer(myShapeList,selectedShapeList);

        CommandHistory.add(this);
    }

    @Override
    public void undo() {

        ArrayList<IShape> selectedShapeList = shapeList.getSelectedShapeList();
        ArrayList<IShape> mainShapeList = shapeList.getShapeList();

        selectedShapeList.remove(shapeGroup);
        mainShapeList.remove(shapeGroup);

        //remove each shape from shape group iteratively
        //add shapes to mainShapeList and selectedShapeGroup
        while(shapeGroup.getSize()!=0){
            IShape s = shapeGroup.removeChild(shapeGroup.getSize()-1);
            mainShapeList.add(s);
            selectedShapeList.add(s);
            s.getShape().shapeSelected=true;
        }
        shapeList.shapeListDrawer(shapeList.getShapeList(),shapeList.getSelectedShapeList());
    }

    @Override
    public void redo() {


        ArrayList<IShape> selectedShapeList = shapeList.getSelectedShapeList();
        ArrayList<IShape> mainShapeList = shapeList.getShapeList();

        for(IShape s : selectedShapeList){
            //remove shape from main shapelist
            mainShapeList.remove(s);
            //if current shape is shape, add to group
            if(s.isGroup()==false){
                shapeGroup.addChild(s);
                s.getShape().shapeSelected=false;
            }
            //if current shape is group, add all shapes to new group
            else if(s.isGroup()==true){
                s.getGroup().groupSelected=false;
                for(IShape z: s.getGroup().getChildren()){
                    shapeGroup.addChild(z);
                }
            }

        }
        mainShapeList.add(shapeGroup);
        selectedShapeList.clear();
        selectedShapeList.add(shapeGroup);
        //remove group from selected shapelist
        shapeList.shapeListDrawer(shapeList.getShapeList(),shapeList.getSelectedShapeList());
    }
}
