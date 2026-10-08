package au.edu.unimelb.swen.oop.mokepon.view.fx.bg;

import au.edu.unimelb.swen.oop.mokepon.util.ImageLoader;
import au.edu.unimelb.swen.oop.mokepon.util.ScreenPosition;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.View;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.bg.GameMenuView;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

public class FxGameMenuView implements GameMenuView, View {

    private Group bgRoot = new Group();

    private Image bgImg = ImageLoader.load("/assets/bg.png", ScreenPosition.BGWIDTH, ScreenPosition.BGHEIGHT, false);

    private Menu menu = new Menu("Game");
    private MenuBar menuBar = new MenuBar();

    private MenuItem[] menuItemArr = {
            new MenuItem("Run away"), new MenuItem("Main menu"), new MenuItem("Exit")
    };


    public FxGameMenuView() {

        menuItemArr[0].setAccelerator(new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN));
        menuItemArr[1].setAccelerator(new KeyCodeCombination(KeyCode.B, KeyCombination.CONTROL_DOWN));
        menuItemArr[2].setAccelerator(new KeyCodeCombination(KeyCode.F4, KeyCombination.ALT_DOWN));

        for (MenuItem menuItem : menuItemArr) {
            menuItem.setDisable(true);
            menu.getItems().add(menuItem);
        }
        menuBar.getMenus().add(menu);
        menuBar.setLayoutX(ScreenPosition.SCREENLEFT);
        menuBar.setLayoutY(ScreenPosition.SCREENTOP);
        menuBar.setPrefWidth(ScreenPosition.SCREENWIDTH);
        menuBar.setPrefHeight(ScreenPosition.MENUBARHEIGHT);
        menuBar.setStyle("-fx-background-color: #ececec; -fx-text-fill: #94807a;");

        ImageView bgImgView = new ImageView(bgImg);
        bgImgView.setLayoutX(ScreenPosition.SCREENLEFT);
        bgImgView.setLayoutY(ScreenPosition.SCREENTOP + ScreenPosition.MENUBARHEIGHT + ScreenPosition.WINDOWHEIGHT);

        bgRoot.getChildren().addAll(bgImgView, menuBar);
    }

    @Override
    public void setExitAction(Runnable exitClick) {
        menuItemArr[2].setOnAction(_ -> exitClick.run());
    }

    @Override
    public void setRunAwayAction(Runnable runAwayAction) {
        menuItemArr[0].setOnAction(_ -> runAwayAction.run());
    }

    @Override
    public void setMainMenuAction(Runnable mainMenuAction) {
        menuItemArr[1].setOnAction(_ -> mainMenuAction.run());
    }

    @Override
    public Node node() {
        return bgRoot;
    }

    @Override
    public void enable(int index) {
        menuItemArr[index].setDisable(false);
    }

    @Override
    public void disable(int index) {
        menuItemArr[index].setDisable(true);
    }
}

