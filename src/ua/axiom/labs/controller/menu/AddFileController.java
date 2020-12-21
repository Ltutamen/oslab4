package ua.axiom.labs.controller.menu;

import ua.axiom.labs.controller.BTreeController;
import ua.axiom.labs.model.FileIndex;
import ua.axiom.labs.view.MenuView;

import java.util.Scanner;

public class AddFileController implements MenuController {
    private final MenuView modelView;
    private final BTreeController<FileIndex> treeController;

    public AddFileController(MenuView modelView, BTreeController<FileIndex> treeController) {
        this.modelView = modelView;
        this.treeController = treeController;
    }

    @Override
    public void run() {
        int newIndexId = modelView.askQuestion("Enter new file index", Scanner::nextInt);
        FileIndex fileIndex = new FileIndex(newIndexId,"" + newIndexId + ".file", 300);
        treeController.insert(newIndexId, fileIndex);
    }
}
