package ua.axiom.labs.view;

import ua.axiom.labs.model.FileIndex;

import java.util.List;

public class FSView {

    public void showFSystem(List<FileIndex> indexes, int fSSize) {
        System.out.println("FS size:" + fSSize);
        for (FileIndex index : indexes) {
            System.out.println("findex:" + index.getId() + ", takes space " +index.getShift() + ":" + index.getShift() + index.getSize());
        }
    }
}
