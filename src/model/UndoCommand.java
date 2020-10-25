package model;

import model.interfaces.ICommand;

import java.awt.*;
import model.ShapeList;

public class UndoCommand implements ICommand {
    @Override
    public void run() {
        System.out.println("Undo Called");
        CommandHistory.undo();
    }
}
