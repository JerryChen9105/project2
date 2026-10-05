package au.edu.unimelb.swen.oop.mokepon.view.fx.overworld;

import au.edu.unimelb.swen.oop.mokepon.driven.model.Mokepon;
import au.edu.unimelb.swen.oop.mokepon.util.FontLoader;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.overworld.BattleMarkerView;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.function.Consumer;

public class FxBattleMarkerView implements BattleMarkerView {

    private VBox battleMarkerRoot = new VBox(10);
    private Text titleTxt = new Text();
    private Text hintTxt = new Text();
    private Button fightBtn = new Button("Fight");

    private Mokepon enemy;
    private Consumer<Mokepon> switchToBattle;


    public FxBattleMarkerView() {
        titleTxt.setFont(FontLoader.getGostischFont(24));
        hintTxt.setFont(FontLoader.getGostischFont(18));
        fightBtn.setFont(FontLoader.getGostischFont(20));
        fightBtn.setStyle("-fx-pref-width: 120;" +
                "-fx-pref-height: 30;" +
                "-fx-background-radius: 999px;" +
                "-fx-background-color: #d3d3d3;" +
                "-fx-text-fill: #000000;");
        fightBtn.setOnAction(event -> {
            if (switchToBattle != null) {
                switchToBattle.accept(enemy);
            }
        });
        battleMarkerRoot.getChildren().addAll(titleTxt, hintTxt, fightBtn);
        battleMarkerRoot.setAlignment(Pos.CENTER);
    }


    @Override
    public Node node() {
        return battleMarkerRoot;
    }

    @Override
    public void setTitle(String text) {
        titleTxt.setText(text);
    }

    @Override
    public void setHint(String text) {
        hintTxt.setText(text);
    }

    @Override
    public void setFightButton(Mokepon enemy, Consumer<Mokepon> switchToBattle) {
        this.enemy = enemy;
        this.switchToBattle = switchToBattle;
    }

    @Override
    public void setVisible(boolean visible) {
        battleMarkerRoot.setVisible(visible);
    }
}
