package ua.axiom.labs.controller.menu;

import ua.axiom.labs.controller.BTreeController;
import ua.axiom.labs.model.FileIndex;
import ua.axiom.labs.view.MenuView;

import java.util.Scanner;

public class DeleteFileController implements MenuController {
    private final BTreeController<FileIndex> controller;
    private final MenuView view;

    public DeleteFileController(
            BTreeController<FileIndex> controller,
            MenuView view
    ) {
        this.controller = controller;
        this.view = view;
    }

    @Override
    public void run() {
        int toDeleteID = view.askQuestion("Enter findex to delete:", Scanner::nextInt);

        controller.deleteNode(toDeleteID);
    }
}
