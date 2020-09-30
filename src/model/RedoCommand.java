package model;

import model.interfaces.ICommand;


public class RedoCommand implements ICommand {
    @Override
    public void run()  {
        System.out.println("Redo Called");
        CommandHistory.redo();
    }

}