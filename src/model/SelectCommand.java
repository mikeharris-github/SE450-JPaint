package model;

import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import model.persistence.ApplicationState;

//import java.awt.Point;
import java.lang.reflect.Array;
import java.util.ArrayList;

import view.interfaces.PaintCanvasBase;

public class SelectCommand implements ICommand, IUndoable {

    PaintCanvasBase paintCanvas;
    private Point startPoint;
    private Point endPoint;
    private ShapeList shapeList;
    ApplicationState appState;

    public SelectCommand(ApplicationState appState, Point startPoint, Point endPoint, ShapeList shapeList, PaintCanvasBase paintCanvas){
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.shapeList = shapeList;
        this.appState = appState;
        this.paintCanvas = paintCanvas;
    }

    @Override
    public void run() {
//        ShapeDecorator shapeDecorator = new ShapeDecorator(paintCanvas);
        //iterate over each shape in shapelist. if the shape intersects with the invisbile rectangle, add it to selectedShapeList that would exist between start/end points (11:30)
        ArrayList<IShape> myShapeList = shapeList.getShapeList();
        ArrayList<IShape> mySelectedShapeList = shapeList.getSelectedShapeList();

        for (IShape s : mySelectedShapeList) {
            if (s.getSize() > 0) {
                s.getGroup().groupSelected = false;
            } else {
                s.getShape().shapeSelected = false;
            }
        }
        //clear selectedShapeList
        mySelectedShapeList.clear();

//        Graphics2D g = paintCanvas.getGraphics2D();


        int mouseStartX = (int) Math.min(this.startPoint.getX(), this.endPoint.getX());
        int mouseEndX = (int) Math.max(this.startPoint.getX(), this.endPoint.getX());
        int mouseStartY = (int) Math.min(this.startPoint.getY(), this.endPoint.getY());
        int mouseEndY = (int) Math.max(this.startPoint.getY(), this.endPoint.getY());
        int width = (int) mouseEndX - (int) mouseStartX;
        int height = (int) mouseEndY - (int) mouseStartY;

        for (IShape s : myShapeList) {
//            s.getShape().getStartPoint();
//            s.getShape().getEndPoint();
            System.out.println("Shape: " + s);

            if (s.getSize() > 0) {
//                System.out.println("THIS ISHAPE IS A GROUP!");
                int groupWidth = s.getGroup().getMaxXY().x - (int) s.getGroup().getMinXY().x;
                int groupHeight = s.getGroup().getMaxXY().x - (int) s.getGroup().getMinXY().x;

                if (s.getGroup().getMinXY().x + groupWidth > mouseStartX
                        && s.getGroup().getMinXY().y + groupHeight > mouseStartY
                        && mouseStartX + width > s.getGroup().getMinXY().x
                        && mouseStartY + height > s.getGroup().getMinXY().y) {
                    //added to shape list
                    s.getGroup().groupSelected=true;
                    mySelectedShapeList.add(s);
//                shapeDecorator.outlineShape(s);

            }
            }
            else{
                System.out.println("Current ShapeList size: " + myShapeList.size());
                System.out.println("current shape: " + s);
                System.out.println("current shapegroup children number: " + s.getSize());

                int shapeStartX = (int) Math.min(s.getShape().getStartPoint().getX(), s.getShape().getEndPoint().getX());
                int shapeEndX = (int) Math.max(s.getShape().getStartPoint().getX(), s.getShape().getEndPoint().getX());
                int shapeStartY = (int) Math.min(s.getShape().getStartPoint().getY(), s.getShape().getEndPoint().getY());
                int shapeEndY = (int) Math.max(s.getShape().getStartPoint().getY(), s.getShape().getEndPoint().getY());

                int shapeWidth = (int) shapeEndX - (int) shapeStartX;
                int shapeHeight = (int) shapeEndY - (int) shapeStartY;

                if (shapeStartX + shapeWidth > mouseStartX
                        && shapeStartY + shapeHeight > mouseStartY
                        && mouseStartX + width > shapeStartX
                        && mouseStartY + height > shapeStartY) {
                    //if shape is selected
//                    System.out.println("shapeStartX: " + shapeStartX);
//                    System.out.println("shapeWidth: " + shapeWidth);
//                    System.out.println("mouseStartX: " + mouseStartX);
//                    System.out.println("shapeStartY: " + shapeStartY);
//                    System.out.println("shapeHeight: " + shapeHeight);
//                    System.out.println("mouseStartY: " + mouseStartY);
//                    System.out.println("width: " + width);
//                    System.out.println("height: " + height);
                    System.out.println("Shape " + s + " is selected!");
                    //added to shape list
                    s.getShape().shapeSelected();
                    mySelectedShapeList.add(s);
//                shapeDecorator.outlineShape(s);
                }
//            }
            }
            shapeList.shapeListDrawer(myShapeList, mySelectedShapeList);
//            System.out.println("Numher of selected shapes: " + mySelectedShapeList.size());
            CommandHistory.add(this);

        }
    }

    @Override
    public void undo() {
        ArrayList<IShape> selectedShapeList = shapeList.getSelectedShapeList();
        ArrayList<IShape> deselectedShapeList = shapeList.deselectedShapeList;
        for(IShape d: selectedShapeList){
            deselectedShapeList.add(d);
            d.getShape().shapeSelected=false;
        }
        selectedShapeList.clear();
        System.out.println("undoSelect done!");
        shapeList.shapeListDrawer(shapeList.getShapeList(),selectedShapeList);
    }

    @Override
    public void redo() {
        ArrayList<IShape> selectedShapeList = shapeList.getSelectedShapeList();
        ArrayList<IShape> deselectedShapeList = shapeList.deselectedShapeList;
        for(IShape d: deselectedShapeList){
            selectedShapeList.add(d);
            d.getShape().shapeSelected=true;
        }
        deselectedShapeList.clear();
        System.out.println("redoSelect done!");
        shapeList.shapeListDrawer(shapeList.getShapeList(),selectedShapeList);

    }
}
