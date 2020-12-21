package ua.axiom.labs.controller;

import ua.axiom.labs.model.BTreeNode;
import ua.axiom.labs.model.FileIndex;

import java.util.*;

public class FSystemController {
    private final BTreeController<FileIndex> table;
    private final List<FileIndex> inserted = new ArrayList<>();
    private final int SIZE = 512;

    public FSystemController(BTreeController<FileIndex> table) {
        this.table = table;
    }

    public void addFile(FileIndex file) {
        table.insert(file.getId(), file);
        inserted.add(file);
    }

    public FileIndex findFile(int id) {
        return table.searchTree(id).getValue();
    }

    public void deleteFile(int id) {
        table.deleteNode(id);
    }

    public int getSize() {
        return SIZE;
    }

    public boolean canPlace(int size) {
        BTreeNode<FileIndex> max = table.maximum(table.getRoot());

        if(max == null) {
            System.out.println("log: canPlace ask for size: " + size + " for empty fs, fs size:" + SIZE);
            return size < SIZE;
        }
        int last = max.getValue().getShift();

        System.out.println("log: canPlace ask for size:" + size + " max elm:" + last + ", ret:" + (last + size <= SIZE) );
        return last + size <= SIZE;
    }

    public List<FileIndex> getAllFiles() {
        return inserted;
    }

}
