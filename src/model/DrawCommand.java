package model;

import model.interfaces.ICommand;
import model.interfaces.IShape;
import view.gui.PaintCanvas;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

public class DrawCommand implements ICommand {

    private final PaintCanvasBase paintCanvas;
    //    IShape shape;
    Point startPoint;
    Point endPoint;
    PaintCanvasBase PaintCanvas;
    Graphics2D g;
    ShapeList shapeList;


    IShape shape;

    public void updateShapeList(ShapeList shapeList){
        this.shapeList = shapeList;
    }

    public DrawCommand(IShape shape, PaintCanvasBase paintCanvas){
        this.shape = shape;
        this.paintCanvas = paintCanvas;
    }


    @Override
    public void run() {

        Graphics2D g = paintCanvas.getGraphics2D();

//        System.out.println(shape.getStartPoint().getX());
//        System.out.println(shape.getStartPoint().getY());
//        System.out.println(shape.getEndPoint().getX());
//        System.out.println(shape.getEndPoint().getY());
//
        double startX = Math.min(shape.getStartPoint().getX(), shape.getEndPoint().getX());
        double endX = Math.max(shape.getStartPoint().getX(), shape.getEndPoint().getX());
        double startY = Math.min(shape.getStartPoint().getY(), shape.getEndPoint().getY());
        double endY = Math.max(shape.getStartPoint().getY(), shape.getEndPoint().getY());

        double width = endX - startX;
        double height = endY - startY;


        g.setColor(Color.green);
        g.fillRect((int)startX,(int)startY,(int)width, (int)height);

        //add to ShapeList

    }

}
