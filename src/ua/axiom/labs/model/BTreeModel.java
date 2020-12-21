package ua.axiom.labs.model;

import ua.axiom.labs.model.conf.BTreeModelConfiguration;

public class BTreeModel<T> {
    private BTreeNode<T> root;

    public BTreeModel() {
        root = null;
    }

    public void setRoot(BTreeNode<T> root) {
        this.root = root;
    }

    public BTreeNode<T> getRoot() {
        return root;
    }
}
