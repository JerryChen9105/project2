package au.edu.unimelb.swen.oop.mokepon.view;

import au.edu.unimelb.swen.oop.mokepon.util.ScreenPosition;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.BattleSelfView;
import javafx.scene.Node;

public class MokeponBattleView {

    private BattleSelfView battleSelfView;


    public MokeponBattleView(BattleSelfView battleSelfView) {
        this.battleSelfView = battleSelfView;

        Node battleSelfViewRoot = this.battleSelfView.node();
        battleSelfViewRoot.setLayoutX(ScreenPosition.SCREENLEFT + 40);
        battleSelfViewRoot.setLayoutY(ScreenPosition.SCREENTOP + ScreenPosition.MENUBARHEIGHT + 470);
    }

    public BattleSelfView getBattleSelfView() {
        return battleSelfView;
    }


}
