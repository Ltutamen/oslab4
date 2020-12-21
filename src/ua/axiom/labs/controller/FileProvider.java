package ua.axiom.labs.controller;

import ua.axiom.labs.model.FileIndex;

public class FileProvider {
    private final FSystemController controller;
    private int previous = 0;

    public FileProvider(FSystemController controller) {
        this.controller = controller;
    }

    public FileIndex getFileIndex(String name, int fsize) {

        if(controller.canPlace(fsize)) {
            return new FileIndex(previous++, name, fsize);
        }

        throw new RuntimeException("not enough space for file: " + name + " with size:" + fsize);
    }
}
