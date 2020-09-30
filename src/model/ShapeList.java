package model;

import java.awt.*;
import java.util.ArrayList;

import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import view.gui.PaintCanvas;
import view.interfaces.PaintCanvasBase;

public class ShapeList {

    public ArrayList<IShape> shapeList = new ArrayList<>();

    public ShapeList(PaintCanvasBase paintCanvas) { }

    Graphics2D g;

    int canvasWidth;
    int canvasHeight;
    private PaintCanvas paintCanvas;

    //add shape to shape list
    public void addShape(IShape shape, Graphics2D g){
//        System.out.println("Triggered!");
        this.shapeList.add(shape);

//        System.out.println("Rectangle added to ShapeList!");
        shapeListDrawer(this.shapeList,g);
    }

    //ShapeList Drawer
    public static void shapeListDrawer(ArrayList<IShape> shapeList, Graphics2D g){
        System.out.println("number of items in list: " + shapeList.size());
        g.setColor(Color.WHITE);
        g.fillRect(0,0,9999,9999);
        for (IShape s: shapeList){
//            System.out.println(s.getStartPoint()  + ", " + s.getEndPoint());
            s.draw();
            System.out.println("draw done");
//            System.out.println("boom... drawn");
        }
        System.out.println("\n****PRINT DONE***\n");
    }

    public void removeShape(IShape shape){
        System.out.println("List size before: " + shapeList.size());
        shapeList.remove(shape);
        shapeList.remove((shapeList.size()-1));
        System.out.println("List size after: " + shapeList.size());
    }


    //count of items in the list
    public void shapeListCount(){
        System.out.println("ShapeList size: " + shapeList.size());
    }


    //get a shape from shape list
    public IShape getShape(int i){
        return shapeList.get(i);
    }


    public void listAllShapes() {
        for(IShape shape: shapeList){
            System.out.println(shape);
        }
    }

//    @Override
//    public void run() {
//        ICommand undoCommand = new UndoCommand();
//    }
}
