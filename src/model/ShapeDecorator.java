package model;

import model.interfaces.IShape;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.awt.Point;

public class ShapeDecorator {

//    private Shape s;
//    private Shape shape;
    PaintCanvasBase paintCanvas;

    public ShapeDecorator(PaintCanvasBase paintCanvas){
        this.paintCanvas = paintCanvas;
    }

    public void outlineShape(IShape s){
//        System.out.println("Outline shape called!");

        Graphics2D g = paintCanvas.getGraphics2D();
        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        g.setStroke(stroke);
        g.setColor(Color.BLACK);
        if(s.getShape().getShapeType()==ShapeType.RECTANGLE){
            g.drawRect(s.getShape().getStartPoint().x-5, s.getShape().getStartPoint().y-5, s.getShape().getWidth()+10, s.getShape().getHeight()+10);
//            System.out.println("ShapeType: " + s.getShapeType());
        }
        else if(s.getShape().getShapeType()==ShapeType.ELLIPSE){
            g.drawOval(s.getShape().getStartPoint().x-5, s.getShape().getStartPoint().y-5, s.getShape().getWidth()+10, s.getShape().getHeight()+10);
//            System.out.println("ShapeType: " + s.getShapeType());
        }
        else if(s.getShape().getShapeType()==ShapeType.TRIANGLE){

            //UPDATE LATER
            Point newPoint = new Point(s.getShape().getStartPoint().x, s.getShape().getEndPoint().y);

            int startArray[] = new int[3];
            int endArray[] = new int[3];

            startArray[0] = (int)s.getShape().getStartPoint().getX()-5;
            startArray[1] = (int)s.getShape().getEndPoint().getX()-5;
            startArray[2] = (int)newPoint.getX()-5;

            endArray[0] = (int)s.getShape().getStartPoint().getY();
            endArray[1] = (int)s.getShape().getEndPoint().getY();
            endArray[2] = (int)newPoint.getY();

            g.drawPolygon(startArray,endArray,3);
//            System.out.println("ShapeType: " + s.getShapeType());
        }

    }


}
