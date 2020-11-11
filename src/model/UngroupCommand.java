package model;

import model.interfaces.ICommand;
import model.interfaces.IShape;

import java.util.ArrayList;

public class UngroupCommand implements ICommand {

    ShapeList shapeList;

    public UngroupCommand(ShapeList shapeList){
        this.shapeList = shapeList;
    }

    @Override
    public void run() {
        System.out.println("Ungroup Command Pressed!");
        ArrayList<IShape> myShapeList = shapeList.getShapeList();
        ArrayList<IShape> selectedShapeList = shapeList.getSelectedShapeList();

        for(IShape s: selectedShapeList){
            if(s.getSize()>0){
                //remove children from shapeGroup
                while(s.getSize()>0){
                    System.out.println("number of children in group to be ungrouped: " + s.getGroup().getChildren().size());
                    IShape x = s.getGroup().removeChild(0);
                    myShapeList.add(x);
                    System.out.println("number of children in group after removal: " + s.getGroup().getChildren().size());
                }
            }
            if(s.isGroup()==true){
                shapeList.removeSpecificShape(s);
            }
        }
        selectedShapeList.clear();
        shapeList.shapeListDrawer(myShapeList,selectedShapeList);

    }
}
