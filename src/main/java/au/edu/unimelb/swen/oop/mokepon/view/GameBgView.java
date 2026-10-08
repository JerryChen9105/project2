package au.edu.unimelb.swen.oop.mokepon.view;

import au.edu.unimelb.swen.oop.mokepon.util.ScreenPosition;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.bg.GameMenuView;
import javafx.scene.Node;

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

    public void setExitBtn(Runnable runnable) {
        gameMenuView.setExitAction(runnable);
    }

    public void setRunAwayBtn(Runnable runnable) {
        gameMenuView.setRunAwayAction(runnable);
    }

    public void setMainMenuBtn(Runnable runnable) {
        gameMenuView.setMainMenuAction(runnable);
    }

    public void enable(int index) {
        gameMenuView.enable(index);
    }

    public void disable(int index) {
        gameMenuView.disable(index);
    }

}
