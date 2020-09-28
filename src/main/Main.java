//Author: Mike Harris
package main;

import controller.IJPaintController;
import controller.JPaintController;
import model.*;
import model.Point;
import model.persistence.ApplicationState;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.PaintCanvasBase;
import view.interfaces.IUiModule;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;


public class Main {

    public static void main(String[] args){
        PaintCanvasBase paintCanvas = new PaintCanvas();
        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);
        ApplicationState appState = new ApplicationState(uiModule);
        IJPaintController controller = new JPaintController(uiModule, appState);
        controller.setup();


//        RectBuilder rb = new RectBuilder();
        Graphics2D g = paintCanvas.getGraphics2D();
        Point startPoint = new Point(0,0);
        Point endPoint = new Point(0,0);


        MouseAdapter ma = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("Mouse clicked: " + e.getX() + "; " +  e.getY());
                startPoint.setX(e.getX());
                startPoint.setY(e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("Mouse released: " + e.getX() + "; " + e.getY());
                endPoint.setX(e.getX());
                endPoint.setY(e.getY());

                //we should be creating a shape, NOT draw

                DrawCommand draw = new DrawCommand(g, startPoint, endPoint);
                try {
                    draw.run();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }

            };

        paintCanvas.addMouseListener(ma);

    }
}























        // For example purposes only; remove all lines below from your final project.





//        // Filled in rectangle
//        Graphics2D graphics2d = paintCanvas.getGraphics2D();
//        graphics2d.setColor(Color.GREEN);
//        graphics2d.fillRect(12, 13, 200, 400);
//
//        // Outlined rectangle
//        graphics2d.setStroke(new BasicStroke(5));
//        graphics2d.setColor(Color.BLUE);
//        graphics2d.drawRect(12, 13, 200, 400);
//
//        // Selected Shape
//        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
//        graphics2d.setStroke(stroke);
//        graphics2d.setColor(Color.BLACK);
//        graphics2d.drawRect(7, 8, 210, 410);
//    }
//}
