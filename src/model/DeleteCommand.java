package model;

import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoable;

import java.util.ArrayList;

public class DeleteCommand implements ICommand, IUndoable {

    private ShapeList shapeList;
    int deleteNum;


    public DeleteCommand(ShapeList shapeList){

        this.shapeList = shapeList;
    }

    @Override
    public void run() {
        System.out.println("Delete Command called");
        ArrayList<IShape> myShapeList = shapeList.getShapeList();
        ArrayList<IShape> mySelectedShapeList = shapeList.getSelectedShapeList();
        ArrayList<IShape> deletedShapeList = shapeList.getDeletedShapeList();

        if(mySelectedShapeList.size()==0) {
            System.out.println("The SelectShapeList is empty! Nothing to delete");
        }
        else if (mySelectedShapeList.size()!=0){
            for(IShape s: mySelectedShapeList){
                if(s.getSize()==0){
                    System.out.println("Removing shape: " + s);
                    myShapeList.remove(s);
                    deletedShapeList.add(s);
                    s.getShape().shapeSelected=false;
                    deleteNum++;
                }
                else{
                    System.out.println("Removing group: " + s);
                    myShapeList.remove(s);
                    deletedShapeList.add(s);
//                    s.getGroup().getChildren().shapeSelected=false;
                    deleteNum++;
                }
            }
            mySelectedShapeList.clear();
            shapeList.shapeListDrawer(myShapeList,mySelectedShapeList);
            CommandHistory.add(this);
        }

    }

    @Override
    public void undo() {
        System.out.println("undoDelete  called");
        ArrayList<IShape> mainShapeList = shapeList.getShapeList();
        ArrayList<IShape> mySelectedShapeList = shapeList.getSelectedShapeList();
        ArrayList<IShape> deletedShapeList = shapeList.getDeletedShapeList();
        System.out.println("dlistSize: " + deletedShapeList.size());

        if(deleteNum==0){
            deleteNum=deletedShapeList.size();
        }

        while(deletedShapeList.size()!=0){
            IShape lastShape = deletedShapeList.get(deletedShapeList.size()-1);
            deletedShapeList.remove(lastShape);
            mainShapeList.add(lastShape);
            mySelectedShapeList.add(lastShape);
            if(lastShape.isGroup()==true){
                lastShape.getGroup().groupSelected=true;
            }
            else{
                lastShape.getShape().shapeSelected=true;
            }
            shapeList.shapeListDrawer(mainShapeList,shapeList.getSelectedShapeList());
            System.out.println("BLOOP DELETE");
            System.out.println("new size: " + deletedShapeList.size());
        }
    }

    @Override
    public void redo() {
        System.out.println("redoDelete  called");
        ArrayList<IShape> mainShapeList = shapeList.getShapeList();
        ArrayList<IShape> mySelectedShapeList = shapeList.getSelectedShapeList();
        ArrayList<IShape> deletedShapeList = shapeList.getDeletedShapeList();

        while(deleteNum!=0){
            IShape lastShape = mainShapeList.get(mainShapeList.size()-1);
            mainShapeList.remove(lastShape);
            deletedShapeList.add(lastShape);
            mySelectedShapeList.clear();
            if(lastShape.isGroup()==true){
                lastShape.getGroup().groupSelected=false;
            }
            else{
                lastShape.getShape().shapeSelected=false;
            }
            shapeList.shapeListDrawer(mainShapeList,shapeList.getSelectedShapeList());
            deleteNum--;
            System.out.println("BLOOP DELETE");
        }

    }
}
