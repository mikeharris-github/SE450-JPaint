package model;

import model.interfaces.ICommand;
import model.interfaces.IUndoable;
import model.persistence.ApplicationState;

import java.awt.*;
import java.awt.Point;
import java.util.ArrayList;

import view.interfaces.PaintCanvasBase;

public class SelectShape implements ICommand{

    PaintCanvasBase paintCanvas;
    private Point startPoint;
    private Point endPoint;
    private ShapeList shapeList;
    ApplicationState appState;

    public SelectShape(ApplicationState appState, java.awt.Point startPoint, Point endPoint, ShapeList shapeList, PaintCanvasBase paintCanvas){
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
        ArrayList<Shape> myShapeList = shapeList.getShapeList();
        ArrayList<Shape> mySelectedShapeList = shapeList.getSelectedShapeList();

        //clear selectedShapeList
        mySelectedShapeList.clear();

        Graphics2D g = paintCanvas.getGraphics2D();


        int mouseStartX = (int)Math.min(this.startPoint.getX(), this.endPoint.getX());
        int mouseEndX = (int)Math.max(this.startPoint.getX(), this.endPoint.getX());
        int mouseStartY = (int)Math.min(this.startPoint.getY(), this.endPoint.getY());
        int mouseEndY = (int)Math.max(this.startPoint.getY(), this.endPoint.getY());
        int width = (int)mouseEndX - (int)mouseStartX;
        int height = (int)mouseEndY - (int)mouseStartY;

        for(Shape s: myShapeList){
            s.getStartPoint();
            s.getEndPoint();

            int shapeStartX = (int)Math.min(s.getStartPoint().getX(), s.getEndPoint().getX());
            int shapeEndX = (int)Math.max(s.getStartPoint().getX(), s.getEndPoint().getX());
            int shapeStartY = (int)Math.min(this.startPoint.getY(), s.getEndPoint().getY());
            int shapeEndY = (int)Math.max(this.startPoint.getY(), s.getEndPoint().getY());

            int shapeWidth = (int)shapeEndX - (int)shapeStartX;
            int shapeHeight = (int)shapeEndY - (int)shapeStartY;

            if(shapeStartX + shapeWidth > mouseStartX && shapeStartY + shapeHeight > mouseStartY && mouseStartX + width > shapeStartX && mouseStartY + height > shapeStartY)
            {
                //if shape is selected
                System.out.println("Shape " + s + " is selected!");
                //added to shape list
                mySelectedShapeList.add(s);
//                shapeDecorator.outlineShape(s);
            }
        }
        shapeList.shapeListDrawer(myShapeList,mySelectedShapeList);
        System.out.println("Numher of selected shapes: " + mySelectedShapeList.size());

    }

}
