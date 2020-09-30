package model;

import model.interfaces.ICommand;

public class UndoCommand implements ICommand {
    @Override
    public void run() {
        System.out.println("Undo Called");
        CommandHistory.undo();
    }
}
