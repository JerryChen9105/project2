package au.edu.unimelb.swen.oop.mokepon.view.fx.overworld;

import au.edu.unimelb.swen.oop.mokepon.util.FontLoader;
import au.edu.unimelb.swen.oop.mokepon.util.ScreenPosition;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.overworld.ExitMarkerView;
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

public class FxExitMarkerView implements ExitMarkerView {

    private StackPane exitMarkerRoot = new StackPane();

    private Rectangle exitMarkerShadow = new Rectangle(ScreenPosition.SCREENWIDTH, ScreenPosition.SCREENHEIGHT);
    private Rectangle exitMarkerBg = new Rectangle(650, 200);
    private VBox exitMarkerBlock = new VBox(35);

    private Text titleTxt = new Text("You finished the game! You won {int} fights!");
    private HBox buttonBlock = new HBox(15);
    private Button returnBtn = new Button("Return to Main Menu");
    private Button quitBtn = new Button("Quit");


    public FxExitMarkerView() {
        exitMarkerShadow.setFill(Paint.valueOf("black"));
        exitMarkerShadow.setOpacity(0.45);

        exitMarkerBg.setFill(Paint.valueOf("white"));

        titleTxt.setFont(FontLoader.getGostischFont(32));

        returnBtn.setFont(Font.font(16));
        returnBtn.setStyle("-fx-pref-width: 200;" +
                "-fx-pref-height: 40;" +
                "-fx-background-radius: 999px;" +
                "-fx-background-color: #d3d3d3;" +
                "-fx-text-fill: #000000;");
        quitBtn.setFont(Font.font(16));
        quitBtn.setStyle("-fx-pref-width: 70;" +
                "-fx-pref-height: 40;" +
                "-fx-background-radius: 999px;" +
                "-fx-background-color: #d3d3d3;" +
                "-fx-text-fill: #000000;");

        buttonBlock.getChildren().addAll(returnBtn, quitBtn);
        buttonBlock.setAlignment(Pos.CENTER);

        exitMarkerBlock.getChildren().addAll(titleTxt, buttonBlock);
        exitMarkerBlock.setAlignment(Pos.CENTER);

        exitMarkerRoot.getChildren().addAll(exitMarkerShadow, exitMarkerBg, exitMarkerBlock);
        exitMarkerRoot.setAlignment(Pos.CENTER);
    }


    @Override
    public Node node() {
        return exitMarkerRoot;
    }

    @Override
    public void setTitle(String text) {
        titleTxt.setText(text);
    }

    @Override
    public void setReturnButton(Runnable onReturnAction) {
        returnBtn.setOnAction(event -> onReturnAction.run());
    }

    @Override
    public void setQuitButton(Runnable onTerminateProgram) {
        quitBtn.setOnAction(event -> onTerminateProgram.run());
    }

    @Override
    public void setVisible(boolean visible) {
        exitMarkerRoot.setVisible(visible);
    }
}

