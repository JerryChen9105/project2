package au.edu.unimelb.swen.oop.mokepon.view;

import au.edu.unimelb.swen.oop.mokepon.util.ScreenPosition;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.bg.BgView;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;

public class GameBgView {

    private BgView bgView;


    public GameBgView(BgView bgView) {
        this.bgView = bgView;


        bgView.enable(2);


        bgView.node().setLayoutX(ScreenPosition.SCREENLEFT);
        bgView.node().setLayoutY(ScreenPosition.SCREENTOP);
    }

    public Node getBgViewRoot() {
        return bgView.node();
    }

    public MenuItem getMenuItem(int index) {
        return bgView.getMenuItemArr()[index];
    }

}
