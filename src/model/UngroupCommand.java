package model;

import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoable;


import java.util.ArrayList;

public class UngroupCommand implements ICommand, IUndoable {

    ShapeList shapeList;

    public UngroupCommand(ShapeList shapeList){
        this.shapeList = shapeList;
    }

    @Override
    public void run() {
        System.out.println("Ungroup Command Pressed!");
        ArrayList<IShape> mainShapeList = shapeList.getShapeList();
        ArrayList<IShape> selectedShapeList = shapeList.getSelectedShapeList();
        ArrayList<IShape> deletedShapeList = shapeList.getDeletedShapeList();
        int groupIndex = 123;

        System.out.println("Main shape list size: " + mainShapeList.size());

        ShapeGroup shapeGroup = new ShapeGroup();
        shapeGroup = selectedShapeList.get(0).getGroup();

        while(shapeGroup.getSize()!=0){
            IShape s = shapeGroup.removeChild(shapeGroup.getSize()-1);
            if(s.isGroup()==false){
                mainShapeList.add(s);
                selectedShapeList.add(s);
                s.getShape().shapeSelected=true;
            }
        }
        for(IShape z: mainShapeList){
            if(z.isGroup()==true){
                groupIndex = mainShapeList.indexOf(z);
                System.out.println("groupIndex: " + groupIndex);
            }
        }
//        selectedShapeList.clear();
        IShape x = mainShapeList.remove(groupIndex);
        deletedShapeList.add(x);
        System.out.println("The main shape list size after ungrouping is: " + mainShapeList.size());
        shapeList.shapeListDrawer(mainShapeList,selectedShapeList);
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }
}
