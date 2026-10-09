package au.edu.unimelb.swen.oop.mokepon.view.fx.battle;

import au.edu.unimelb.swen.oop.mokepon.util.FontLoader;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.battle.BattleNameView;
import javafx.scene.Node;
import javafx.scene.text.Text;

public class FxBattleNameView implements BattleNameView {

    private Text nameTitle = new Text("default");


    public FxBattleNameView() {
        nameTitle.setFont(FontLoader.getGostischFont(28));
    }


    @Override
    public void setText(String str) {
        nameTitle.setText(str);
    }

    @Override
    public Node node() {
        return nameTitle;
    }
}


