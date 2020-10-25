package model;

import model.interfaces.ICommand;
import model.persistence.ApplicationState;

import java.awt.*;
import java.awt.Point;
import java.util.Stack;
import model.ShapeDecorator;
import view.gui.PaintCanvas;
import view.interfaces.PaintCanvasBase;

public class SelectShape implements ICommand {

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
        ShapeDecorator shapeDecorator = new ShapeDecorator(paintCanvas);
        //iterate over each shape in shapelist. if the shape intersects with the invisbile rectangle, add it to selectedShapeList that would exist between start/end points (11:30)
        Stack<Shape> myShapeList = shapeList.getShapeList();
        Stack<Shape> mySelectedShapeList = shapeList.getSelectedShapeList();

        //clear selectedShapeList
        mySelectedShapeList.clear();


        int startX = (int)Math.min(this.startPoint.getX(), this.endPoint.getX());
        int endX = (int)Math.max(this.startPoint.getX(), this.endPoint.getX());
        int startY = (int)Math.min(this.startPoint.getY(), this.endPoint.getY());
        int endY = (int)Math.max(this.startPoint.getY(), this.endPoint.getY());
        int width = (int)endX - (int)startX;
        int height = (int)endY - (int)startY;

        for(Shape s: myShapeList){
            s.getStartPoint();
            s.getEndPoint();

            int shapeStartX = (int)Math.min(s.getStartPoint().getX(), s.getEndPoint().getX());
            int shapeEndX = (int)Math.max(s.getStartPoint().getX(), s.getEndPoint().getX());
            int shapeStartY = (int)Math.min(this.startPoint.getY(), s.getEndPoint().getY());
            int shapeEndY = (int)Math.max(this.startPoint.getY(), s.getEndPoint().getY());

            int shapeWidth = (int)shapeEndX - (int)shapeStartX;
            int shapeHeight = (int)shapeEndY - (int)shapeStartY;

            if(shapeStartX + shapeWidth > startX && shapeStartY + shapeHeight > startY && startX + width > shapeStartX && startY + height > shapeStartY)
            {
                //if shape is selected
                System.out.println("Shape " + s + " is selected!");
                //shape selected boolean is true
                s.shapeSelected=true;
                //added to shape list
                mySelectedShapeList.add(s);
                //create dotted outline
                shapeDecorator.outlineShape(s);
            }

        }


    }
}
