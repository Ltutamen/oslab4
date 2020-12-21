package ua.axiom.labs.controller.menu;

import ua.axiom.labs.controller.BTreeController;
import ua.axiom.labs.model.FileIndex;
import ua.axiom.labs.view.FSView;
import ua.axiom.labs.view.MenuView;
import ua.axiom.labs.view.TreeView;

import java.util.HashMap;
import java.util.Map;

public class MainMenuController implements MenuController {
    private final MenuView view = new MenuView();
    private final FSView treeView = new FSView();
    private final Map<Integer, MenuController> inputToControllerMap = new HashMap<>();
    private final String[] menuQuestions = new String[]{"Find file", "Add file", "Delete file", "View tree", "Exit"};

    public MainMenuController(BTreeController<FileIndex> treeController) {
        inputToControllerMap.put(1, new FindFileController(view, treeController));
        inputToControllerMap.put(2, new AddFileController(view, treeController));
        inputToControllerMap.put(3, new DeleteFileController(treeController, view));
        inputToControllerMap.put(4, new ShowTreeController(new TreeView(treeController)));
    }

    @Override
    public void run() {
        int action = view.ask(new String[]{"Find file", "Add file", "Delete file", "View tree", "Exit"});

        while (action != 5) {
            inputToControllerMap.get(action).run();

            action = view.ask(new String[]{"Find file", "Add file", "Delete file", "View tree", "Exit"});
        }
    }
}
