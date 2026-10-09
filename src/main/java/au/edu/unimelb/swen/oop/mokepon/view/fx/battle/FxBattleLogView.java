package au.edu.unimelb.swen.oop.mokepon.view.fx.battle;

import au.edu.unimelb.swen.oop.mokepon.util.ScreenPosition;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.battle.BattleLogView;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;

public class FxBattleLogView implements BattleLogView {


    private TextArea battleLogRoot = new TextArea();



    public FxBattleLogView() {
        battleLogRoot.setFont(Font.font("default", 15));
        battleLogRoot.setEditable(false);
        battleLogRoot.setPrefSize(630, 290);
        battleLogRoot.setLayoutX(ScreenPosition.SCREENWIDTH - 685);
        battleLogRoot.setLayoutY(ScreenPosition.SCREENHEIGHT - 335);
    }



    @Override
    public Node node() {
        return battleLogRoot;
    }

    @Override
    public void append(String message) {
        battleLogRoot.appendText(message);
    }

    @Override
    public void clear() {
        battleLogRoot.clear();
    }
}


