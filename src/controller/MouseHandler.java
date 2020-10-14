package controller;

import model.CreateShape;
import model.ShapeList;
import model.interfaces.ICommand;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;
import model.persistence.ApplicationState;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//import model.Point;

public class MouseHandler extends MouseAdapter {

    private ShapeList shapeList;
    public Point startPoint;
    public Point endPoint;
    public PaintCanvasBase paintCanvas;
    ICommand command;
    private ApplicationState appState;

    public MouseHandler(PaintCanvasBase paintCanvas, ShapeList shapeList, ApplicationState appState){
        this.paintCanvas = paintCanvas;
        this.shapeList = shapeList;
        this.appState = appState;
    }



    @Override
    public void mousePressed(MouseEvent e) {
        startPoint = new Point(e.getX(),e.getY());
        System.out.println("MousePressed: " + (int)startPoint.getX() + ", " + (int)startPoint.getY());

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.endPoint = new Point(e.getX(), e.getY());
        System.out.println("MouseReleased: " + (int)endPoint.getX() + ", " + (int)endPoint.getY());

        ICommand createShapeCommand = new CreateShape(startPoint, endPoint, shapeList, appState);
        createShapeCommand.run();
    }

}
