package model;

import model.interfaces.ICommand;

import java.awt.*;


public class RedoCommand implements ICommand {
    @Override
    public void run()  {
        System.out.println("Redo Called");
        ShapeList.redoShape();
        CommandHistory.redo();
    }

}
