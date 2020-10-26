package model;

import java.awt.*;
import java.lang.reflect.Array;
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

//    public static Stack<Shape> shapeList = new Stack<>();
//    public static Stack<Shape> deletedShapeList = new Stack<>();
//    public static Stack<Shape> selectedShapeList = new Stack<>();
//    public static Stack<Shape> copiedShapeList = new Stack<>();

    public static ArrayList<Shape> shapeList = new ArrayList<>();
    public static ArrayList<Shape> deletedShapeList = new ArrayList<>();
    public static ArrayList<Shape> selectedShapeList = new ArrayList<>();
    public static ArrayList<Shape> copiedShapeList = new ArrayList<>();

    private static PaintCanvas paintCanvas;
    public ApplicationState appState;


    public ShapeList(PaintCanvasBase paintCanvas, ApplicationState appState) {
        this.paintCanvas = (PaintCanvas) paintCanvas;
        this.appState = appState;
    }

    public void addShape(Shape shape){
        shapeList.add(shape);
        deletedShapeList.clear();
        shapeListDrawer(shapeList,selectedShapeList);
    }


    public void shapeListDrawer(ArrayList<Shape> shapeList, ArrayList<Shape> selectedShapeList){

        Graphics2D g = paintCanvas.getGraphics2D();
        g.setColor(Color.white);
        g.fillRect(0,0,9999,9999);
        for (Shape s: shapeList){
            s.draw(g);
        }
        for (Shape z: selectedShapeList){
            ShapeDecorator shapeDecorator = new ShapeDecorator(paintCanvas);
            shapeDecorator.outlineShape(z);

        }
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
        shapeListDrawer(shapeList,selectedShapeList);
    }

    public void removeSpecificShape(Shape s){
        if(shapeList.size() == 0) {
            System.out.println("There's nothing in the list to remove!");
            return;
        }
        //get last shape in Shape List
        shapeList.remove(s);
        deletedShapeList.add(s);
//        }
        shapeListDrawer(shapeList,selectedShapeList);
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
                shapeListDrawer(shapeList,selectedShapeList);
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
        Shape d = deletedShapeList.remove(deletedShapeList.size()-1);
        shapeList.add(d);
        if(deletedShapeList.size()!=0){
            addDeletedShapes();
        }
        shapeListDrawer(shapeList,selectedShapeList);
    }

    public void addSpecificDeletedShape(Shape s){
        shapeList.add(s);
    }

    public void deleteSpecificShape(Shape s){
        shapeList.remove(s);
    }

    public ArrayList<Shape> getShapeList() {
        return shapeList;
    }

    public ArrayList<Shape> getSelectedShapeList(){
        return selectedShapeList;
    }

    public ArrayList<Shape> getDeletedShapeList(){
        return deletedShapeList;
    }

    public ArrayList<Shape> getCopiedShapeList() { return copiedShapeList;}


}

