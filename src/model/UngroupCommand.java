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
        ArrayList<IShape> mainShapeList = shapeList.getShapeList();
        ArrayList<IShape> selectedShapeList = shapeList.getSelectedShapeList();
        ArrayList<IShape> undoRedoList = shapeList.getUndoRedoList();
        ArrayList<IShape> groupList = shapeList.getGroupList();

        IShape outerShapeGroup = groupList.get(groupList.size()-1);

        if(groupList.size()==0){
            System.out.println("there's nothing here!");
        }
        else if(groupList.size()==1){
            for(IShape s: outerShapeGroup.getGroup().children){
                mainShapeList.add(s);
                selectedShapeList.add(s);
                s.getShape().shapeSelected=true;
            }
            outerShapeGroup.getGroup().groupSelected=false;
        }
        else if(groupList.size()>1){

            for(IShape s: outerShapeGroup.getGroup().children){
                mainShapeList.add(s);
                selectedShapeList.add(s);
                if(s.isGroup()==false){
                    s.getShape().shapeSelected=true;
                }
                else{
                    s.getGroup().groupSelected=true;
                }
            }
            outerShapeGroup.getGroup().groupSelected=false;
        }

        groupList.remove(outerShapeGroup);
        mainShapeList.remove(outerShapeGroup);
        selectedShapeList.remove(outerShapeGroup);
        undoRedoList.add(outerShapeGroup);
        shapeList.shapeListDrawer(mainShapeList,selectedShapeList);
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
//        System.out.println("undo Ungroup Command Pressed!");

        ArrayList<IShape> mainShapeList = shapeList.getShapeList();
        ArrayList<IShape> selectedShapeList = shapeList.getSelectedShapeList();
        ArrayList<IShape> groupList = shapeList.getGroupList();

        ShapeGroup shapeGroup = new ShapeGroup();

        for(IShape s: selectedShapeList){
            mainShapeList.remove(s);
            shapeGroup.addChild(s);
            if(s.isGroup()==false){
                s.getShape().shapeSelected=false;
            }
            else{
                s.getGroup().groupSelected=false;
            }
        }

        mainShapeList.add(shapeGroup);
        selectedShapeList.clear();
        selectedShapeList.add(shapeGroup);
        groupList.add(shapeGroup);
        shapeGroup.groupSelected=true;
        shapeList.shapeListDrawer(mainShapeList,selectedShapeList);
    }

    @Override
    public void redo() {
//        System.out.println("Ungroup Command Pressed!");
        ArrayList<IShape> mainShapeList = shapeList.getShapeList();
        ArrayList<IShape> selectedShapeList = shapeList.getSelectedShapeList();
        ArrayList<IShape> undoRedoList = shapeList.getUndoRedoList();
        ArrayList<IShape> groupList = shapeList.getGroupList();

        IShape outerShapeGroup = groupList.get(groupList.size()-1);

        if(groupList.size()==0){
            System.out.println("there's nothing here!");
        }
        else if(groupList.size()==1){
            for(IShape s: outerShapeGroup.getGroup().children){
                mainShapeList.add(s);
                selectedShapeList.add(s);
                s.getShape().shapeSelected=true;
            }
            outerShapeGroup.getGroup().groupSelected=false;
        }
        else if(groupList.size()>1){
            for(IShape s: outerShapeGroup.getGroup().children){
                mainShapeList.add(s);
                selectedShapeList.add(s);
                if(s.isGroup()==false){
                    s.getShape().shapeSelected=true;
                }
                else{
                    s.getGroup().groupSelected=true;
                }
            }
            outerShapeGroup.getGroup().groupSelected=false;
        }

        groupList.remove(outerShapeGroup);
        mainShapeList.remove(outerShapeGroup);
        selectedShapeList.remove(outerShapeGroup);
        undoRedoList.add(outerShapeGroup);

        shapeList.shapeListDrawer(mainShapeList,selectedShapeList);


    }
}
