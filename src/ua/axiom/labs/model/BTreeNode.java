package ua.axiom.labs.model;

public class BTreeNode<T> {
    private boolean isRed;
    private BTreeNode<T> parent;
    private BTreeNode<T> left, right;

    private final int key;
    private T value;

    public BTreeNode(int key, T data) {
        this.key = key;
        this.value = data;
    }

    public int getKey() {
        return key;
    }

    public BTreeNode<T> getLeft() {
        return left;
    }

    public BTreeNode<T> getRight() {
        return right;
    }

    public T getValue() {
        return value;
    }

    public void setParent(BTreeNode<T> parent) {
        this.parent = parent;
    }

    public void setLeft(BTreeNode<T> left) {
        this.left = left;
    }

    public void setRight(BTreeNode<T> right) {
        this.right = right;
    }

    public void setRed(boolean red) {
        isRed = red;
    }

    public BTreeNode<T> getParent() {
        return parent;
    }

    public boolean isRed() {
        return isRed;
    }


}
