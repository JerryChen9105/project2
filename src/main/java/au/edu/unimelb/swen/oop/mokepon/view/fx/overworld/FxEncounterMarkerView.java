package au.edu.unimelb.swen.oop.mokepon.view.fx.overworld;

import au.edu.unimelb.swen.oop.mokepon.util.FontLoader;
import au.edu.unimelb.swen.oop.mokepon.util.ScreenPosition;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.overworld.EncounterMarkerView;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class FxEncounterMarkerView implements EncounterMarkerView {

    private StackPane battleMarkerRoot = new StackPane();

    private Rectangle battleMarkerShadow = new Rectangle(ScreenPosition.SCREENWIDTH, ScreenPosition.SCREENHEIGHT);
    private Rectangle battleMarkerBg = new Rectangle(500, 300);

    private VBox battleMarkerBlock = new VBox(15);
    private Text titleTxt = new Text("Encountered a {Mokepon name}");
    private Text selfHintTxt = new Text("Your mokepon will be at an advantage here!");
    private Text enemyHintTxt = new Text("Your enemy will be at an advantage here!");

    private HBox btnBlock = new HBox(15);
    private Button fightBtn = new Button("Fight");
    private Button runBtn = new Button("Run Away!");


    public FxEncounterMarkerView() {
        battleMarkerShadow.setFill(Paint.valueOf("black"));
        battleMarkerShadow.setOpacity(0.45);
        battleMarkerBg.setFill(Paint.valueOf("white"));
        titleTxt.setFont(FontLoader.getGostischFont(32));
        selfHintTxt.setFont(Font.font(16));
        enemyHintTxt.setFont(Font.font(16));
        fightBtn.setFont(Font.font(16));
        fightBtn.setStyle("-fx-pref-width: 80;" +
                "-fx-pref-height: 40;" +
                "-fx-background-radius: 999px;" +
                "-fx-background-color: #d3d3d3;" +
                "-fx-text-fill: #000000;");
        runBtn.setFont(Font.font(16));
        runBtn.setStyle("-fx-pref-width: 120;" +
                "-fx-pref-height: 40;" +
                "-fx-background-radius: 999px;" +
                "-fx-background-color: #d3d3d3;" +
                "-fx-text-fill: #000000;");

        btnBlock.getChildren().addAll(fightBtn, runBtn);
        btnBlock.setAlignment(Pos.CENTER);

        battleMarkerBlock.getChildren().addAll(titleTxt, selfHintTxt, enemyHintTxt, btnBlock);
        battleMarkerBlock.setAlignment(Pos.CENTER);

        battleMarkerRoot.getChildren().addAll(battleMarkerShadow, battleMarkerBg, battleMarkerBlock);
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
    public void setSelfHint(String text) {
        selfHintTxt.setText(text);
    }

    @Override
    public void setEnemyHint(String text) {
        enemyHintTxt.setText(text);
    }

    @Override
    public void setFightButton(Runnable switchToBattle) {
        fightBtn.setOnAction(event -> switchToBattle.run());
    }

    @Override
    public void setRunButton(Runnable backToOverworld) {
        runBtn.setOnAction(event -> backToOverworld.run());
    }

    @Override
    public void setVisible(boolean visible) {
        battleMarkerRoot.setVisible(visible);
    }
}

