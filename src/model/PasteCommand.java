package model;

import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoable;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PasteCommand implements ICommand, IUndoable {

    private ShapeList shapeList;
    public int pasteNum;

//    Graphics2D g;

    public PasteCommand(ShapeList shapeList){
        this.shapeList = shapeList;
    }

    @Override
    public void run() {
        //you'll need to clone the shapes, then add to the main shapeList, with some offset (100 pixels by 100 pixels)
        //createShapeCommand
        System.out.println("Paste Command called");
        pasteNum=0;

        ArrayList<IShape> copiedShapeList = shapeList.getCopiedShapeList();
        ArrayList<IShape> pasteShapeList = shapeList.getPasteShapeList();

        for(IShape s: pasteShapeList){
            s.getShape().shapePasted=false;
        }

        for(IShape s: copiedShapeList){
            if(s.isGroup()==false){
                //create shape
                System.out.println("S1 startpoint: " + s.getStartPoint() + ", endPoint: " + s.getEndPoint());
                int x1 = s.getShape().getStartPoint().x - 100;
                int y1 = s.getShape().getStartPoint().y - 100;
                int x2 = s.getShape().getEndPoint().x -100;
                int y2 = s.getShape().getEndPoint().y - 100;
                Point s2sPoint = new Point(x1,y1);
                Point s2ePoint = new Point(x2,y2);
                ICommand createShapeCommand = new CreateShapeCommand(s.getShape().appState, s2sPoint,s2ePoint, shapeList, s.getShape().getpColor(), s.getShape().getsColor(), s.getShape().getShadingType(), s.getShape().getShapeType());
                createShapeCommand.run();
                pasteNum++;
            }
            else{
                for(IShape z: s.getGroup().children){
                    int x1 = z.getShape().getStartPoint().x - 100;
                    int y1 = z.getShape().getStartPoint().y - 100;
                    int x2 = z.getShape().getEndPoint().x -100;
                    int y2 = z.getShape().getEndPoint().y - 100;
                    Point s2sPoint = new Point(x1,y1);
                    Point s2ePoint = new Point(x2,y2);
                    ICommand createShapeCommand = new CreateShapeCommand(z.getShape().appState, s2sPoint,s2ePoint, shapeList, z.getShape().getpColor(), z.getShape().getsColor(), z.getShape().getShadingType(), z.getShape().getShapeType());
                    createShapeCommand.run();
                    pasteNum++;
                }
            }
        }
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        System.out.println("undoPaste called");
        ArrayList<IShape> mainShapeList = shapeList.getShapeList();
        ArrayList<IShape> pasteShapeList = shapeList.getPasteShapeList();
        ArrayList<IShape> undoRedoList = shapeList.getUndoRedoList();
        ArrayList<IShape> deletedShapeList = shapeList.getDeletedShapeList();

        if(mainShapeList.size() == 0) { return; }

        while(pasteNum!=0){
            IShape lastShape = mainShapeList.get(mainShapeList.size()-1);
            mainShapeList.remove(lastShape);
            undoRedoList.add(lastShape);
            shapeList.shapeListDrawer(mainShapeList,shapeList.getSelectedShapeList());
            pasteNum--;
            System.out.println("BLOOP");
        }
    }

    @Override
    public void redo() {
        System.out.println("redoPaste called");
        ArrayList<IShape> mainShapeList = shapeList.getShapeList();
        ArrayList<IShape> pasteShapeList = shapeList.getPasteShapeList();
        ArrayList<IShape> undoRedoList = shapeList.getUndoRedoList();
        ArrayList<IShape> deletedShapeList = shapeList.getDeletedShapeList();

        if(mainShapeList.size() == 0) { return; }

        while(undoRedoList.size()!=0){
            IShape lastShape = undoRedoList.get(undoRedoList.size()-1);
            undoRedoList.remove(lastShape);
            mainShapeList.add(lastShape);
            shapeList.shapeListDrawer(mainShapeList,shapeList.getSelectedShapeList());
            pasteNum++;
            System.out.println("BLOOP BLOOP");
        }
    }
}
