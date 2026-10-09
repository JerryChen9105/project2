package au.edu.unimelb.swen.oop.mokepon.view.fx.battle;

import au.edu.unimelb.swen.oop.mokepon.util.FontLoader;
import au.edu.unimelb.swen.oop.mokepon.util.ScreenPosition;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.battle.BattleLossMarkerView;
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

public class FxLossMarkerView implements BattleLossMarkerView {

    private StackPane lossMarkerRoot = new StackPane();

    private Rectangle lossMarkerShadow = new Rectangle(ScreenPosition.SCREENWIDTH, ScreenPosition.SCREENHEIGHT);
    private Rectangle lossMarkerBg = new Rectangle(500, 300);

    private VBox lossMarkerBlock = new VBox(30);
    private Text titleTxt = new Text("Game Over");

    private HBox btnBlock = new HBox(15);
    private Button returnBtn = new Button("Return to Main Menu");
    private Button quitBtn = new Button("Quit");


    public FxLossMarkerView() {
        lossMarkerShadow.setFill(Paint.valueOf("black"));
        lossMarkerShadow.setOpacity(0.45);

        lossMarkerBg.setFill(Paint.valueOf("white"));

        titleTxt.setFont(FontLoader.getGostischFont(32));

        returnBtn.setFont(Font.font(16));
        returnBtn.setStyle("-fx-pref-width: 200;" +
                "-fx-pref-height: 40;" +
                "-fx-background-radius: 999px;" +
                "-fx-background-color: #d3d3d3;" +
                "-fx-text-fill: #000000;");

        quitBtn.setFont(Font.font(16));
        quitBtn.setStyle("-fx-pref-width: 80;" +
                "-fx-pref-height: 40;" +
                "-fx-background-radius: 999px;" +
                "-fx-background-color: #d3d3d3;" +
                "-fx-text-fill: #000000;");

        btnBlock.getChildren().addAll(returnBtn, quitBtn);
        btnBlock.setAlignment(Pos.CENTER);

        lossMarkerBlock.getChildren().addAll(titleTxt, btnBlock);
        lossMarkerBlock.setAlignment(Pos.CENTER);

        lossMarkerRoot.getChildren().addAll(lossMarkerShadow, lossMarkerBg, lossMarkerBlock);
        lossMarkerRoot.setAlignment(Pos.CENTER);
    }


    @Override
    public Node node() {
        return lossMarkerRoot;
    }

    @Override
    public void setReturnBtn(Runnable onReturnClicked) {
        returnBtn.setOnAction(event -> onReturnClicked.run());
    }

    @Override
    public void setQuitBtn(Runnable onQuitClicked) {
        quitBtn.setOnAction(event -> onQuitClicked.run());
    }

    @Override
    public void setVisible(boolean visible) {
        lossMarkerRoot.setVisible(visible);
    }
}


