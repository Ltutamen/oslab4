package ua.axiom.labs.view;

import ua.axiom.labs.controller.BTreeController;
import ua.axiom.labs.model.BTreeNode;
import ua.axiom.labs.model.FileIndex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class TreeView {
    private final BTreeNode<FileIndex> nullNode;
    private final BTreeController<FileIndex> controller;

    public TreeView(BTreeController<FileIndex> controller) {
        this.nullNode = controller.getTNULL();

        this.controller = controller;
    }

    public void showTree() {
        showNodeLayer(Collections.singletonList(Optional.ofNullable(controller.getRoot())));
    }

    private void showNodeLayer(List<Optional<BTreeNode<FileIndex>>> layer) {
        List<Optional<BTreeNode<FileIndex>>> nextLayer = new ArrayList<>(layer.size() * 2);
        String skip = "\te\t";

        for (Optional<BTreeNode<FileIndex>> optional : layer) {
            System.out.print(optional.isPresent() ? optional.get().getValue() : skip);

            if(optional.isPresent()) {
                BTreeNode<FileIndex> node = optional.get();
                nextLayer.add(node.getLeft() != nullNode ? Optional.of(node.getLeft()) : Optional.empty());
                nextLayer.add(node.getRight() != nullNode ? Optional.of(node.getRight()) : Optional.empty());
            }
        }

        if(nextLayer.size() != 0) {
            System.out.println();
            showNodeLayer(nextLayer);
        }


    }
}
