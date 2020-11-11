package model;


import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoable;

import java.util.ArrayList;

public class GroupCommand implements ICommand, IUndoable {

    ShapeList shapeList;
    ShapeGroup shapeGroup;
    boolean groupCreated;


    public GroupCommand(ShapeList shapeList){
        this.shapeList = shapeList;
    }


    @Override
    public void run() {
        System.out.println("Group Command Pressed!");
        ArrayList<IShape> myShapeList = shapeList.getShapeList();
        ArrayList<IShape> selectedShapeList = shapeList.getSelectedShapeList();

        System.out.println("Selected Group size: " + selectedShapeList.size());

        //if the selectedShapeList is empty, end
        if(selectedShapeList.size() <= 0) { System.out.println("There's no selected shapes!"); }

        //if shapes are grouped
        else if(selectedShapeList.size()!= 0){
            //create new group
            shapeGroup = new ShapeGroup();
            //remove shapes from selectedShapeList and add to group
            for(IShape s : selectedShapeList){
                //remove shape from main shapelist
                myShapeList.remove(s);
                //if current shape is shape, add to group
                if(s.isGroup()==false){
//                if(s.getSize()==0){
                    shapeGroup.addChild(s);
                    s.getShape().shapeSelected=false;
                }
                //if current shape is group, add all shapes to new group
                else if(s.isGroup()==true){
//                else if(s.getSize()>0){
                    s.getGroup().groupSelected=false;
                    for(IShape z: s.getGroup().getChildren()){
                        shapeGroup.addChild(z);
                    }
                }
                System.out.println("Removed shape: " + s);
                System.out.println("Number of shapes in group: " + shapeGroup.getSize());
            }
            myShapeList.add(shapeGroup);
            selectedShapeList.clear();
            selectedShapeList.add(shapeGroup);
            System.out.println("Shapes are grouped!");
        }
        shapeList.shapeListDrawer(myShapeList,selectedShapeList);
//        selectedShapeList.clear();

        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        System.out.println("shapeGroup size: " + shapeGroup.getSize());
        System.out.println("shapeGroup children number: " + shapeGroup.getChildren().size());
        //remove group from selected shapelist
        shapeList.getSelectedShapeList().clear();
        //remove shapegroup from shapelist
        //select shapes and add to selectedShapelist
        //remove shapes from shapegroup
//        for(IShape s: shapeGroup.getChildren()){
////            IShape z = shapeGroup.removeChild(s);
//            System.out.println("IShape z: " + z);
//            shapeList.addShape(z);
//            shapeList.getSelectedShapeList().add(z);
//            z.getShape().shapeSelected = true;
//            System.out.println("REMAINING CHILDREN: " + shapeGroup.getChildren().size());
//        }
        shapeList.shapeListDrawer(shapeList.getShapeList(),shapeList.getSelectedShapeList());
    }

    @Override
    public void redo() {

    }
}
