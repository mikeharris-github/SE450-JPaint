package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

import model.interfaces.IApplicationState;
import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import model.persistence.ApplicationState;
import view.gui.PaintCanvas;
import view.interfaces.IEventCallback;
import view.interfaces.PaintCanvasBase;

public class ShapeList {

    public static Stack<Shape> shapeList = new Stack<>();
    public static Stack<Shape> deletedShapeList = new Stack<>();
    public static Stack<Shape> selectedShapeList = new Stack<>();
    public static Stack<Shape> copiedShapeList = new Stack<>();
    private static PaintCanvas paintCanvas;
    public ApplicationState appState;

    //    private final DrawCommand drawCommand;
//    private final PaintCanvasBase paintCanvas;

    public ShapeList(PaintCanvasBase paintCanvas, ApplicationState appState) {
        this.paintCanvas = (PaintCanvas) paintCanvas;
        this.appState = appState;
    }
    public void addShape(Shape shape){
        shapeList.add(shape);
        deletedShapeList.clear();
        shapeListDrawer(shapeList);
    }


    public void shapeListDrawer(Stack<Shape> shapeList){
        ShapeDecorator shapeDecorator = new ShapeDecorator(paintCanvas);

        Graphics2D g = paintCanvas.getGraphics2D();
        g.setColor(Color.white);
        g.fillRect(0,0,9999,9999);
        for (Shape s: shapeList){
            s.draw(g);
        }
//        for (Shape z: selectedShapeList){
//            System.out.println("Printing from selectedShapeList! Sahpe: " + z);
//            shapeDecorator.outlineShape(z);
//        }
//        CommandHistory.add(this);
    }

    public void removeShape(){
        if(shapeList.size() == 0) {
            System.out.println("There's nothing in the list to remove!");
            return;
        }
        //get last shape in Shape List
        Shape lastShape = shapeList.get(shapeList.size()-1);
        shapeList.remove(lastShape);
        deletedShapeList.add(lastShape);
//        }
        shapeListDrawer(shapeList);
    }

    public void redoShape(){
        if(deletedShapeList.size() == 0 && shapeList.size()== 0) {
            System.out.println("There's nothing to redo!");
            return;
        }
        else if(deletedShapeList.size() == 0 && shapeList.size() != 0){
            Shape lastShape = shapeList.get(shapeList.size()-1);
            if(lastShape.undoPerformered==true){
                lastShape.redoMove();
                lastShape.undoPerformered=false;
                shapeListDrawer(shapeList);
            }
        }
        else{
            addDeletedShapes();
        }
    }

    public void addDeletedShapes(){
//        System.out.println("AddDeletedShapes called");
        Shape dShape = deletedShapeList.get(deletedShapeList.size()-1);
        System.out.println("Adding shape: " + dShape);
        Shape d = deletedShapeList.pop();
        shapeList.push(d);
        shapeListDrawer(shapeList);
    }

    public Stack<Shape> getShapeList() {
        return shapeList;
    }

    public Stack<Shape> getSelectedShapeList(){
        return selectedShapeList;
    }

    public Stack<Shape> getDeletedShapeList(){
        return deletedShapeList;
    }

    public Stack<Shape> getCopiedShapeList() { return copiedShapeList;}


}

