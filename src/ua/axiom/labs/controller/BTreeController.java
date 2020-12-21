package ua.axiom.labs.controller;

import ua.axiom.labs.model.BTreeNode;

public class BTreeController<T extends Comparable<T>> {
    private BTreeNode<T> root;
    private final BTreeNode<T> TNULL;

    public BTreeController() {
        this.TNULL = new BTreeNode<T>(0, null);
        this.TNULL.setRed(false);

        this.root = TNULL;
    }

    public BTreeNode<T> getTNULL() {
        return TNULL;
    }

    private void preOrderHelper(BTreeNode<T> node) {
        if (node != TNULL) {
            preOrderHelper(node.getLeft());
            preOrderHelper(node.getRight());
        }
    }

    private void inOrderHelper(BTreeNode<T> node) {
        if (node != TNULL) {
            inOrderHelper(node.getLeft());
            inOrderHelper(node.getRight());
        }
    }

    private void postOrderHelper(BTreeNode<T> node) {
        if (node != TNULL) {
            postOrderHelper(node.getLeft());
            postOrderHelper(node.getRight());
        }
    }

    private BTreeNode<T> searchTreeHelper(BTreeNode<T> node, int key) {
        if (node == TNULL || key == node.getKey()) {
            return node;
        }

        if (key < node.getKey()) {
            return searchTreeHelper(node.getLeft(), key);
        }
        return searchTreeHelper(node.getRight(), key);
    }

    // fix the rb tree modified by the delete operation
    private void fixDelete(BTreeNode<T> x) {
        BTreeNode<T> s;
        while (x != root && !x.isRed()) {
            if (x == x.getParent().getLeft()) {
                s = x.getParent().getRight();
                if (s.isRed()) {
                    // case 3.1
                    s.setRed(false);
                    x.getParent().setRed(true);
                    leftRotate(x.getParent());
                    s = x.getParent().getRight();
                }

                if (!s.getLeft().isRed() && !s.getRight().isRed()) {
                    // case 3.2
                    s.setRed(true);
                    x = x.getParent();
                } else {
                    if (!s.getRight().isRed()) {
                        // case 3.3
                        s.getLeft().setRed(false);
                        s.setRed(true);
                        rightRotate(s);
                        s = x.getParent().getRight();
                    }

                    // case 3.4
                    s.setRed(x.getParent().isRed());
                    x.getParent().setRed(false);
                    s.getRight().setRed(false);
                    leftRotate(x.getParent());
                    x = root;
                }
            } else {
                s = x.getParent().getLeft();
                if (s.isRed()) {
                    // case 3.1
                    s.setRed(false);
                    x.getParent().setRed(true);
                    rightRotate(x.getParent());
                    s = x.getParent().getLeft();
                }

                if (!s.getRight().isRed() && !s.getRight().isRed()) {
                    // case 3.2
                    s.setRed(true);
                    x = x.getParent();
                } else {
                    if (!s.getLeft().isRed()) {
                        // case 3.3
                        s.getRight().setRed(false);
                        s.setRed(true);
                        leftRotate(s);
                        s = x.getParent().getLeft();
                    }

                    // case 3.4
                    s.setRed(x.getParent().isRed());
                    x.getParent().setRed(false);
                    s.getLeft().setRed(false);
                    rightRotate(x.getParent());
                    x = root;
                }
            }
        }
        x.setRed(false);
    }


    private void rbTransplant(BTreeNode<T> u, BTreeNode<T> v){
        if (u.getParent() == null) {
            root = v;
        } else if (u == u.getParent().getLeft()){
            u.getParent().setLeft(v);
        } else {
            u.getParent().setRight(v);
        }
        v.setParent(u.getParent());
    }

    private void deleteNodeHelper(BTreeNode<T> node, int key) {
        // find the node containing key
        BTreeNode<T> z = TNULL;
        BTreeNode<T> x, y;
        while (node != TNULL){
            if (node.getValue().equals(key)) {
                z = node;
            }

            if (node.getKey() <= key) {
                node = node.getRight();
            } else {
                node = node.getLeft();
            }
        }

        if (z == TNULL) {
            return;
        }

        y = z;
        boolean isYRed = y.isRed();
        if (z.getLeft() == TNULL) {
            x = z.getRight();
            rbTransplant(z, z.getRight());
        } else if (z.getRight() == TNULL) {
            x = z.getLeft();
            rbTransplant(z, z.getLeft());
        } else {
            y = minimum(z.getRight());
            isYRed = y.isRed();
            x = y.getRight();
            if (y.getParent() == z) {
                x.setParent(y);
            } else {
                rbTransplant(y, y.getRight());
                y.setRight(z.getRight());
                y.getRight().setParent(y);
            }

            rbTransplant(z, y);
            y.setLeft(z.getLeft());
            y.getLeft().setParent(y);
            y.setRed(z.isRed());
        }
        if (!isYRed){
            fixDelete(x);
        }
    }

    // fix the red-black tree
    private void fixInsert(BTreeNode<T> k){
        BTreeNode<T> u;
        while (k.getParent().isRed() ) {
            if (k.getParent() == k.getParent().getParent().getRight()) {
                u = k.getParent().getParent().getLeft(); // uncle
                if (u.isRed()) {
                    // case 3.1
                    u.setRed(false);
                    k.getParent().setRed(false);
                    k.getParent().getParent().setRed(true);
                    k = k.getParent().getParent();
                } else {
                    if (k == k.getParent().getLeft()) {
                        // case 3.2.2
                        k = k.getParent();
                        rightRotate(k);
                    }
                    // case 3.2.1
                    k.getParent().setRed(false);
                    k.getParent().getParent().setRed(true);
                    leftRotate(k.getParent().getParent());
                }
            } else {
                u = k.getParent().getParent().getRight(); // uncle

                if (u.isRed()) {
                    // mirror case 3.1
                    u.setRed(false);
                    k.getParent().setRed(false);
                    k.getParent().getParent().setRed(true);
                    k = k.getParent().getParent();
                } else {
                    if (k == k.getParent().getRight()) {
                        // mirror case 3.2.2
                        k = k.getParent();
                        leftRotate(k);
                    }
                    // mirror case 3.2.1
                    k.getParent().setRed(false);
                    k.getParent().getParent().setRed(true);
                    rightRotate(k.getParent().getParent());
                }
            }
            if (k == root) {
                break;
            }
        }
        root.setRed(false);
    }

    // Pre-Order traversal
    // Node.Left Subtree.Right Subtree
    public void preorder() {
        preOrderHelper(this.root);
    }

    // In-Order traversal
    // Left Subtree . BTreeNode<T> . Right Subtree
    public void inorder() {
        inOrderHelper(this.root);
    }

    // Post-Order traversal
    // Left Subtree . Right Subtree . Node
    public void postorder() {
        postOrderHelper(this.root);
    }

    // search the tree for the key k
    // and return the corresponding node
    public BTreeNode<T> searchTree(int k) {
        return searchTreeHelper(this.root, k);
    }

    // find the node with the minimum key
    public BTreeNode<T> minimum(BTreeNode<T> node) {
        while (node.getLeft() != TNULL) {
            node = node.getLeft();
        }
        return node;
    }

    // find the node with the maximum key
    public BTreeNode<T> maximum(BTreeNode<T> node) {
        if(node == TNULL) {
            return null;
        }
        while (node.getRight() != TNULL) {
            node = node.getRight();
        }
        return node;
    }

    // find the successor of a given node
    public BTreeNode<T> successor(BTreeNode<T> x) {
        // if the right subtree is not null,
        // the successor is the leftmost node in the
        // right subtree
        if (x.getRight() != TNULL) {
            return minimum(x.getRight());
        }

        // else it is the lowest ancestor of x whose
        // left child is also an ancestor of x.
        BTreeNode<T> y = x.getParent();
        while (y != TNULL && x == y.getRight()) {
            x = y;
            y = y.getParent();
        }
        return y;
    }

    // find the predecessor of a given node
    public BTreeNode<T> predecessor(BTreeNode<T> x) {
        // if the left subtree is not null,
        // the predecessor is the rightmost node in the
        // left subtree
        if (x.getLeft() != TNULL) {
            return maximum(x.getLeft());
        }

        BTreeNode<T> y = x.getParent();
        while (y != TNULL && x == y.getLeft()) {
            x = y;
            y = y.getParent();
        }

        return y;
    }

    // rotate left at node x
    public void leftRotate(BTreeNode<T> x) {
        BTreeNode<T> y = x.getRight();
        x.setRight(y.getLeft());
        if (y.getLeft() != TNULL) {
            y.getLeft().setParent(x);
        }
        y.setParent(x.getParent());
        if (x.getParent() == null) {
            this.root = y;
        } else if (x == x.getParent().getLeft()) {
            x.getParent().setLeft(y);
        } else {
            x.getParent().setRight(y);
        }
        y.setLeft(x);
        x.setParent(y);
    }

    // rotate right at node x
    public void rightRotate(BTreeNode<T> x) {
        BTreeNode<T> y = x.getLeft();
        x.setLeft(y.getRight());
        if (y.getRight() != TNULL) {
            y.getRight().setParent(x);;
        }
        y.setParent(x.getParent());
        if (x.getParent() == null) {
            this.root = y;
        } else if (x == x.getParent().getRight()) {
            x.getParent().setRight(y);
        } else {
            x.getParent().setLeft(y);
        }
        y.setRight(x);
        x.setParent(y);
    }

    // insert the key to the tree in its appropriate position
    // and fix the tree
    public void insert(int key, T value) {
        // Ordinary Binary Search Insertion
        BTreeNode<T> node = new BTreeNode<>(key, value);
        node.setParent(null);
        node.setLeft(TNULL);
        node.setRight(TNULL);
        node.setRed(true); // new node must be red

        BTreeNode<T> y = null;
        BTreeNode<T> x = this.root;

        while (x != TNULL) {
            y = x;
            if (node.getKey() < node.getKey()) {
                x = x.getLeft();
            } else {
                x = x.getRight();
            }
        }

        // y is parent of x
        node.setParent(y);
        if (y == null) {
            root = node;
        } else if (node.getKey() < y.getKey()) {
            y.setLeft(node);
        } else {
            y.setRight(node);
        }

        // if new node is a root node, simply return
        if (node.getParent() == null){
            node.setRed(false);
            return;
        }

        // if the grandparent is null, simply return
        if (node.getParent().getParent() == null) {
            return;
        }

        // Fix the tree
        fixInsert(node);
    }

    public BTreeNode<T> getRoot(){
        return this.root;
    }

    // delete the node from the tree
    public void deleteNode(int data) {
        deleteNodeHelper(this.root, data);
    }
}
