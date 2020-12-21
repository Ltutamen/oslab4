package ua.axiom.labs.controller.menu;

import ua.axiom.labs.view.TreeView;

public class ShowTreeController implements MenuController {
    private final TreeView treeView;

    public ShowTreeController(TreeView treeView) {
        this.treeView = treeView;
    }

    @Override
    public void run() {
        treeView.showTree();
    }
}
