package view.gui;

//import model.Rectangle;
import org.w3c.dom.css.Rect;
import view.interfaces.PaintCanvasBase;

import javax.swing.JComponent;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class PaintCanvas extends PaintCanvasBase {

    public Graphics2D getGraphics2D() {
        return (Graphics2D)getGraphics();
    }


}
