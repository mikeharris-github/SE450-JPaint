package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import view.gui.PaintCanvas;
import view.interfaces.IEventCallback;
import view.interfaces.PaintCanvasBase;

public class ShapeList implements ICommand {

    public static Stack<IShape> shapeList = new Stack<>();
    public static Stack<IShape> deletedShapeList = new Stack<>();
    private static PaintCanvas paintCanvas;

    //    private final DrawCommand drawCommand;
//    private final PaintCanvasBase paintCanvas;

    public ShapeList(PaintCanvasBase paintCanvas) {
        this.paintCanvas = (PaintCanvas) paintCanvas;
    }
    public void addShape(IShape shape){
//        System.out.println("Shape: " + shape);
        shapeList.add(shape);
//        deletedShapeList.add(shape);
        deletedShapeList.clear();
        shapeListDrawer(shapeList);

    }


    public static void shapeListDrawer(Stack<IShape> shapeList){

        Graphics2D g = paintCanvas.getGraphics2D();
        g.setColor(Color.white);
        g.fillRect(0,0,9999,9999);
        for (IShape s: shapeList){
            s.draw(g);
        }
    }

    public static void removeShape(){
        if(shapeList.size() == 0) {
            System.out.println("There's nothing in the list to remove!");
            return;
        }
        System.out.println("Removing shape: " + shapeList.get(shapeList.size()-1));
//        shapeList.remove((shapeList.size()-1));
        IShape r = shapeList.pop();
        deletedShapeList.push(r);
        shapeListDrawer(shapeList);

    }

    public static void redoShape(){
        if(deletedShapeList.size() == 0) {
            System.out.println("There's nothing to redo!");
            return;
        }
        addDeletedShapes();
    }

    public static void addDeletedShapes(){
//        System.out.println("AddDeletedShapes called");
        IShape dShape = deletedShapeList.get(deletedShapeList.size()-1);
        System.out.println("Adding shape: " + dShape);
        IShape d = deletedShapeList.pop();
        shapeList.push(d);
        shapeListDrawer(shapeList);
    }



    @Override
    public void run() {


    }

}

