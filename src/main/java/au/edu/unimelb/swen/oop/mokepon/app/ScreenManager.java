package au.edu.unimelb.swen.oop.mokepon.app;

import au.edu.unimelb.swen.oop.mokepon.controller.ScreenController;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class ScreenManager {

    private Stage stage;
    private HashMap<ScreenState, ScreenController> controllerMap = new LinkedHashMap<>();
    private ScreenState currentScreenState;

    public ScreenManager(Stage stage) {
        this.stage = stage;
        currentScreenState = null;
    }


    public void register(ScreenState screenState, ScreenController screenController) {
        this.controllerMap.put(screenState, screenController);
    }

    public void switchTo(ScreenState screenState) {
        if (currentScreenState != null) {
            controllerMap.get(currentScreenState).hide();
        }
        ScreenController targetController = controllerMap.get(screenState);
        if (targetController == null) return;

        targetController.show();
        currentScreenState = screenState;

        Parent currentRoot = stage.getScene().getRoot();
        //stage.getScene().setRoot(null); // add view.node()
    }

    public ScreenState getCurrentScreenState() {
        return currentScreenState;
    }
}
