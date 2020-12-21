package ua.axiom.labs;

import ua.axiom.labs.controller.BTreeController;
import ua.axiom.labs.controller.menu.MainMenuController;
import ua.axiom.labs.controller.menu.MenuController;
import ua.axiom.labs.model.FileIndex;

public class LabApplication {
    private final BTreeController<FileIndex> treeController;

    public static void main(String[] args) {
        new LabApplication().run();
    }

    public LabApplication() {
        treeController = new BTreeController<>();
    }

    public void run() {
        MenuController controller = new MainMenuController(treeController);
        controller.run();
    }
}
