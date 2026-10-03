package au.edu.unimelb.swen.oop.mokepon.view.fx.bg;

import au.edu.unimelb.swen.oop.mokepon.util.ImageLoader;
import au.edu.unimelb.swen.oop.mokepon.util.ScreenPosition;
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

import java.util.function.Consumer;

public class FxGameMenuView implements GameMenuView {

    private Group bgRoot = new Group();

    private Image bgImg = ImageLoader.load("/assets/bg.png", ScreenPosition.BGWIDTH, ScreenPosition.BGHEIGHT, false);

    private Menu menu = new Menu("Game");
    private MenuBar menuBar = new MenuBar();

    private MenuItem[] menuItemArr = {
            new MenuItem("Reset"), new MenuItem("Restart Battle"), new MenuItem("Exit ")
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
        menuBar.setStyle("-fx-fill: #ececec;" +
                "-fx-pref-width: " + ScreenPosition.SCREENWIDTH + ";" +
                "-fx-pref-height: " + ScreenPosition.MENUBARHEIGHT + ";" +
                "-fx-text-fill: #94807a;");

        ImageView bgImgView = new ImageView(bgImg);
        bgImgView.setLayoutX(ScreenPosition.SCREENLEFT);
        bgImgView.setLayoutY(ScreenPosition.SCREENTOP + ScreenPosition.MENUBARHEIGHT);

        bgRoot.getChildren().addAll(bgImgView, menuBar);
    }

    public void setAction(int index, Consumer<Integer> onClick) {
        menuItemArr[index].setOnAction(_ -> onClick.accept(index));
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

    @Override
    public MenuItem[] getMenuItemArr() {
        return menuItemArr;
    }
}
