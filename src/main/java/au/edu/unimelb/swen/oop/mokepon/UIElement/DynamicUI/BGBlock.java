package au.edu.unimelb.swen.oop.mokepon.UIElement.DynamicUI;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import java.util.ArrayList;

import static au.edu.unimelb.swen.oop.mokepon.UIElement.Utils.ScreenPosition.*;

public class BGBlock {

    public static MenuBar createMenuBar(ArrayList<MenuItem> itemList) {

        Menu menu = new Menu("Game");

        MenuItem resetButton = new MenuItem("Reset");
        resetButton.setDisable(true);
        MenuItem restartButton = new MenuItem("Restart Battle");
        restartButton.setDisable(true);
        MenuItem exitButton = new MenuItem("Exit");
        exitButton.setDisable(false);

        menu.getItems().addAll(resetButton, restartButton, exitButton);
        itemList.add(resetButton); itemList.add(restartButton); itemList.add(exitButton);

        MenuBar menuBar = new MenuBar(menu);
        menuBar.setLayoutX(screenLeft);
        menuBar.setLayoutY(screenTop);
        menuBar.setStyle("-fx-fill: #ececec;" +
                "-fx-pref-width: 1280;" +
                "-fx-pref-height: 15;" +
                "-fx-text-fill: #94807a;");

        return menuBar;
    }

}
