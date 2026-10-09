package au.edu.unimelb.swen.oop.mokepon.view.fx.battle;

import au.edu.unimelb.swen.oop.mokepon.util.FontLoader;
import au.edu.unimelb.swen.oop.mokepon.util.ScreenPosition;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.battle.BattleWinMarkerView;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class FxWinMarkerView implements BattleWinMarkerView {

    private StackPane winMarkerRoot = new StackPane();

    private Rectangle winMarkerShadow = new Rectangle(ScreenPosition.SCREENWIDTH, ScreenPosition.SCREENHEIGHT);
    private Rectangle winMarkerBg = new Rectangle(300, 300);
    private VBox winMarkerBlock = new VBox(30);

    private Text titleTxt = new Text("You Win");
    private Button returnBtn = new Button("Back to it!");


    public FxWinMarkerView() {
        winMarkerShadow.setFill(Paint.valueOf("black"));
        winMarkerShadow.setOpacity(0.45);

        winMarkerBg.setFill(Paint.valueOf("white"));

        titleTxt.setFont(FontLoader.getGostischFont(32));

        returnBtn.setFont(Font.font(16));
        returnBtn.setStyle("-fx-pref-width: 100;" +
                "-fx-pref-height: 40;" +
                "-fx-background-radius: 999px;" +
                "-fx-background-color: #d3d3d3;" +
                "-fx-text-fill: #000000;");

        winMarkerBlock.getChildren().addAll(titleTxt, returnBtn);
        winMarkerBlock.setAlignment(Pos.CENTER);

        winMarkerRoot.getChildren().addAll(winMarkerShadow, winMarkerBg, winMarkerBlock);
    }


    @Override
    public Node node() {
        return winMarkerRoot;
    }

    @Override
    public void setReturnBtn(Runnable onReturnClicked) {
        returnBtn.setOnAction(event -> onReturnClicked.run());
    }

    @Override
    public void setVisible(boolean visible) {
        winMarkerRoot.setVisible(visible);
    }
}


