package au.edu.unimelb.swen.oop.mokepon.view;

import au.edu.unimelb.swen.oop.mokepon.util.ScreenPosition;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.bg.GameMenuView;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;

public class GameBgView {

    private GameMenuView gameMenuView;


    public GameBgView(GameMenuView gameMenuView) {
        this.gameMenuView = gameMenuView;


        gameMenuView.enable(2);


        gameMenuView.node().setLayoutX(ScreenPosition.SCREENLEFT);
        gameMenuView.node().setLayoutY(ScreenPosition.SCREENTOP);
    }

    public Node getBgViewRoot() {
        return gameMenuView.node();
    }

    public MenuItem getMenuItem(int index) {
        return gameMenuView.getMenuItemArr()[index];
    }

}
