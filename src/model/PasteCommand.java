package model;

import model.interfaces.ICommand;
import model.interfaces.IShape;

import java.awt.*;
import java.util.ArrayList;

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

        ArrayList<IShape> copiedShapeList = shapeList.getCopiedShapeList();

        for(IShape s: copiedShapeList){
            //create shape
            System.out.println("S1 startpoint: " + s.getStartPoint() + ", endPoint: " + s.getEndPoint());
            int x1 = s.getShape().getStartPoint().x - 100;
            int y1 = s.getShape().getStartPoint().y - 100;
            int x2 = s.getShape().getEndPoint().x -100;
            int y2 = s.getShape().getEndPoint().y - 100;
            Point s2sPoint = new Point(x1,y1);
            Point s2ePoint = new Point(x2,y2);

            Shape s2 = new Shape(s2sPoint, s2ePoint, s.getShape().appState, s.getShape().getpColor(), s.getShape().getsColor(), s.getShape().getShadingType(), s.getShape().getShapeType());


            System.out.println("S2 startPoint: " + s2.getStartPoint() + ", endPoint: " + s2.getEndPoint());
            System.out.println("S1 startpoint: " + s.getStartPoint() + ", endPoint: " + s.getEndPoint());

            //IMPLEMENTATION 1
            ICommand createShapeCommand = new CreateShape(s2.appState, s2.getStartPoint(), s2.getEndPoint(), shapeList, s2.getpColor(), s2.getsColor(), s2.getShadingType(), s2.getShapeType());
            createShapeCommand.run();

        }
    }
}
