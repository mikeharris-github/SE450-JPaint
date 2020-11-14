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

    public void outlineGroup(IShape s){
//        System.out.println("outlineGroup called!");
//        System.out.println("max x: " + s.getGroup().getMaxXY().x);
        int groupWidth = s.getGroup().getMaxXY().x - s.getGroup().getMinXY().x;
        int groupHeight = s.getGroup().getMaxXY().y - s.getGroup().getMinXY().y;

        Graphics2D g = paintCanvas.getGraphics2D();
        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        g.setStroke(stroke);
        g.setColor(Color.BLACK);
        g.drawRect(s.getGroup().getMinXY().x -5, s.getGroup().getMinXY().y-5, groupWidth+10,groupHeight+10);
    }

    public void outlineShape(IShape s){
//        System.out.println("Outline shape called!");

        Graphics2D g = paintCanvas.getGraphics2D();
        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        g.setStroke(stroke);
        g.setColor(Color.BLACK);
        if(s.getShape().getShapeType()==ShapeType.RECTANGLE && (s.getShape().shapeSelected == true)){
            g.drawRect(s.getShape().getMinXY().x-5, s.getShape().getMinXY().y-5, s.getShape().getWidth()+10, s.getShape().getHeight()+10);
//            g.drawRect(s.getShape().getStartPoint().x-5, s.getShape().getStartPoint().y-5, s.getShape().getWidth()+10, s.getShape().getHeight()+10);
//            System.out.println("ShapeType: " + s.getShapeType());
        }
        else if(s.getShape().getShapeType()==ShapeType.ELLIPSE && (s.getShape().shapeSelected == true)){
            g.drawOval(s.getShape().getMinXY().x-5, s.getShape().getMinXY().y-5, s.getShape().getWidth()+10, s.getShape().getHeight()+10);
//            System.out.println("ShapeType: " + s.getShapeType());
        }
        else if(s.getShape().getShapeType()==ShapeType.TRIANGLE && (s.getShape().shapeSelected == true)){

            //UPDATE LATER
            Point newPoint = new Point(s.getShape().getStartPoint().x, s.getShape().getEndPoint().y);

            int startArray[] = new int[3];
            int endArray[] = new int[3];

            startArray[0] = s.getShape().getStartPoint().getX();
            startArray[1] = s.getShape().getEndPoint().getX();
            startArray[2] = (int)newPoint.getX();

            endArray[0] = s.getShape().getStartPoint().getY();
            endArray[1] = s.getShape().getEndPoint().getY();
            endArray[2] = (int)newPoint.getY();

            g.drawPolygon(startArray,endArray,3);
//            System.out.println("ShapeType: " + s.getShapeType());
        }
        else if (s.getShape().shapeSelected == false){
            System.out.println("this shape ain't selected!");
        }

    }


}
