package controller;

import model.*;
import model.interfaces.ICommand;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import model.Point;

public class MouseHandler extends MouseAdapter {

    ApplicationState appState;
    private ShapeList shapeList;
    public Point startPoint;
    public Point endPoint;
    public PaintCanvasBase paintCanvas;

    public MouseHandler(ApplicationState appState, PaintCanvasBase paintCanvas, ShapeList shapeList){
        this.paintCanvas = paintCanvas;
        this.shapeList = shapeList;
        this.appState = appState;
    }



    @Override
    public void mousePressed(MouseEvent e) {
        startPoint = new Point(e.getX(),e.getY());
//        System.out.println("MousePressed: " + startPoint.getX() + ", " + startPoint.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.endPoint = new Point(e.getX(), e.getY());
//        System.out.println("MouseReleased: " + endPoint.getX() + ", " + endPoint.getY());

        //PrimaryColor
        ColorTranslate primaryColorTranslate = new ColorTranslate(appState.getActivePrimaryColor());
        Color pColor = primaryColorTranslate.getColor();
        //SecondaryColor
        ColorTranslate secondaryColorTranslate = new ColorTranslate(appState.getActiveSecondaryColor());
        Color sColor = secondaryColorTranslate.getColor();
        //Shading Type
        ShapeShadingType shadingType = appState.getActiveShapeShadingType();
        //ShapeType
        ShapeType shapeType = appState.getActiveShapeType();

        //if in draw mode
        if (appState.getActiveMouseMode() == MouseMode.DRAW) {
            ICommand createShapeCommand = new CreateShapeCommand(appState, startPoint, endPoint, shapeList, pColor, sColor, shadingType, shapeType);
            createShapeCommand.run();
        }
        //if in select Mode
        else if (appState.getActiveMouseMode() == MouseMode.SELECT){
//            System.out.println("MouseMode in Select");
            ICommand selectShapeCommand = new SelectCommand(appState, startPoint, endPoint, shapeList, paintCanvas);
            selectShapeCommand.run();
        }
        else {
//            System.out.println("MouseMode in Move");
            ICommand moveShapeCommand = new MoveShape(appState, startPoint, endPoint, shapeList);
            moveShapeCommand.run();
        }

    }

}
