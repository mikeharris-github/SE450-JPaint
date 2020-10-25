package model;

import view.gui.PaintCanvas;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.awt.Point;

public class ShapeDecorator {

    private Shape s;
    private Shape shape;
    PaintCanvasBase paintCanvas;

    public ShapeDecorator(PaintCanvasBase paintCanvas){
        this.paintCanvas = paintCanvas;
    }

    public void outlineShape(Shape s){
        System.out.println("Outline shape called!");

        Graphics2D g = paintCanvas.getGraphics2D();
        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        g.setStroke(stroke);
        g.setColor(Color.BLACK);
        if(s.getShapeType()==ShapeType.RECTANGLE){
            g.drawRect(s.getStartPoint().x, s.getStartPoint().y, s.getWidth(), s.getHeight());
            System.out.println("ShapeType: " + s.getShapeType());
        }
        else if(s.getShapeType()==ShapeType.ELLIPSE){
            g.drawOval(s.getStartPoint().x, s.getStartPoint().y, s.getWidth(), s.getHeight());
            System.out.println("ShapeType: " + s.getShapeType());
        }
        else if(s.getShapeType()==ShapeType.TRIANGLE){

            //UPDATE LATER
            Point newPoint = new Point(s.getStartPoint().x, s.getEndPoint().y);

            int startArray[] = new int[3];
            int endArray[] = new int[3];

            startArray[0] = (int)s.getStartPoint().getX();
            startArray[1] = (int)s.getEndPoint().getX();
            startArray[2] = (int)newPoint.getX();

            endArray[0] = (int)s.getStartPoint().getY();
            endArray[1] = (int)s.getEndPoint().getY();
            endArray[2] = (int)newPoint.getY();

            g.drawPolygon(startArray,endArray,3);
            System.out.println("ShapeType: " + s.getShapeType());
        }

    }


}
