package ua.axiom.labs.controller.menu;

import ua.axiom.labs.controller.BTreeController;
import ua.axiom.labs.model.BTreeNode;
import ua.axiom.labs.model.FileIndex;
import ua.axiom.labs.view.MenuView;

import java.util.Scanner;

public class FindFileController implements MenuController {
    private final MenuView view;
    private final BTreeController<FileIndex> controller;

    public FindFileController(MenuView view, BTreeController<FileIndex> controller) {
        this.view = view;
        this.controller = controller;
    }

    @Override
    public void run() {
        int fileId = view.askQuestion("Enter file id to find:", Scanner::nextInt);
        BTreeNode<FileIndex> node = controller.searchTree(fileId);
        if(node.getKey() != fileId) {
            throw new RuntimeException("Tree is broken, for id:" + fileId + " returns node vis key:" + node.getKey());
        }

        System.out.println("file found:" + node.getValue());
    }
}
