package model;

import model.interfaces.ICommand;
import model.interfaces.IShape;

import java.util.ArrayList;

public class CopyCommand implements ICommand {

    private ShapeList shapeList;

    public CopyCommand(ShapeList shapeList){
        this.shapeList = shapeList;
    }
    @Override
    public void run() {
        System.out.println("Copy Command called");
        //iterate over the selectedShapeList, where the selectedShapeList iterated over the mainShapeList
        ArrayList<IShape> selectedShapeList = shapeList.getSelectedShapeList();

        for(IShape s: selectedShapeList){
            shapeList.getCopiedShapeList().add(s);
            System.out.println("Shape " + s + " copied!");
        }
    }
}
