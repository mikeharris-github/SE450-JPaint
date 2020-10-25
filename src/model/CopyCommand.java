package model;

import model.interfaces.ICommand;

import java.util.Stack;

public class CopyCommand implements ICommand {

    private ShapeList shapeList;

    public CopyCommand(ShapeList shapeList){
        this.shapeList = shapeList;
    }
    @Override
    public void run() {
        System.out.println("Copy Command called");
        //iterate over the selectedShapeList, where the selectedShapeList iterated over the mainShapeList
        Stack<Shape> selectedShapeList = shapeList.getSelectedShapeList();

        for(Shape s: selectedShapeList){
            shapeList.getCopiedShapeList().add(s);
            System.out.println("Shape " + s + " copied!");
        }
    }
}
