package model;

import model.ShapeList;
import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoable;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CopyCommand implements ICommand, IUndoable {

    public ShapeList shapeList;

    public CopyCommand(ShapeList shapeList){
        this.shapeList = shapeList;
    }
    @Override
    public void run() {
        System.out.println("Copy Command called");
        ArrayList<IShape> selectedShapeList = shapeList.getSelectedShapeList();
        ArrayList<IShape> copiedlist = shapeList.getCopiedShapeList();
        copiedlist.clear();
        shapeList.getPasteShapeList().clear();

        for(IShape s: selectedShapeList){
            copiedlist.add(s);
            System.out.println("Shape " + s + " copied!");
        }
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        System.out.println("undoCopy called!");
        ArrayList<IShape> selectedShapeList = shapeList.getSelectedShapeList();
        ArrayList<IShape> copiedlist = shapeList.getCopiedShapeList();

        for(IShape s: copiedlist){
            selectedShapeList.add(s);
        }
        copiedlist.clear();
    }

    @Override
    public void redo() {
        System.out.println("undoCopy called!");
        ArrayList<IShape> selectedShapeList = shapeList.getSelectedShapeList();
        ArrayList<IShape> copiedlist = shapeList.getCopiedShapeList();

        for(IShape s: selectedShapeList){
            copiedlist.add(s);
        }
    }
}
