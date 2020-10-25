package model;

import jdk.swing.interop.SwingInterOpUtils;
import model.interfaces.ICommand;

import java.awt.*;
import java.util.Stack;

public class PasteCommand implements ICommand {

    private ShapeList shapeList;

    Graphics2D g;

    public PasteCommand(ShapeList shapeList){
        this.shapeList = shapeList;
    }

    @Override
    public void run() {
        //you'll need to clone the shapes, then add to the main shapeList, with some offset (100 pixels by 100 pixels)
        //createShapeCommand
        System.out.println("Paste Command called");

        Stack<Shape> copiedShapeList = shapeList.getCopiedShapeList();

        for(Shape s: copiedShapeList){
            //create shape
            System.out.println("S1 startpoint: " + s.getStartPoint() + ", endPoint: " + s.getEndPoint());
            int x1 = s.getStartPoint().x - 100;
            int y1 = s.getStartPoint().y - 100;
            int x2 = s.getEndPoint().x -100;
            int y2 = s.getEndPoint().y - 100;
            java.awt.Point s2sPoint = new java.awt.Point(x1,y1);
            java.awt.Point s2ePoint = new java.awt.Point(x2,y2);

            Shape s2 = new Shape(s2sPoint, s2ePoint, s.appState, s.getpColor(), s.getsColor(), s.getShadingType(), s.getShapeType());


            System.out.println("S2 startPoint: " + s2.getStartPoint() + ", endPoint: " + s2.getEndPoint());
            System.out.println("S1 startpoint: " + s.getStartPoint() + ", endPoint: " + s.getEndPoint());

            //IMPLEMENTATION 1
            ICommand createShapeCommand = new CreateShape(s2.appState, s2.getStartPoint(), s2.getEndPoint(), shapeList, s2.getpColor(), s2.getsColor(), s2.getShadingType(), s2.getShapeType());
            createShapeCommand.run();

        }
    }
}
