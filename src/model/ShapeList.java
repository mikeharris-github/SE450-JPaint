package model;

import model.interfaces.IShape;

import java.awt.*;
import java.util.ArrayList;

public class ShapeList {

    //The purpose of this class is to have a list of IShapes

    //create ArrayList for Shape List
    private ArrayList<IShape> shapeList;

    //create object
    public ShapeList(ArrayList<IShape> shapeList) {this.shapeList = shapeList;}

    //add shape to shape list
    public void addShape(IShape shape){
        shapeList.add(shape);
    }



//    @Override
//    public void setStartPoint(int x, int y) {
//
//    }
//
//    @Override
//    public void setEndPoint(int x, int y) {
//
//    }
}
