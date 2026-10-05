package au.edu.unimelb.swen.oop.mokepon.view.fx.overworld;

import au.edu.unimelb.swen.oop.mokepon.util.FontLoader;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.overworld.ExitMarkerView;
import javafx.beans.property.IntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.function.Consumer;

public class FxExitMarkerView implements ExitMarkerView {

    private VBox exitMarkerRoot = new VBox(20);
    private Text titleTxt = new Text();
    private HBox buttonBlock = new HBox(20);
    private Button returnBtn = new Button("Return");
    private Button quitBtn = new Button("Quit");

    private IntegerProperty fightCount;
    private Consumer<IntegerProperty> onReturnAction;


    public FxExitMarkerView() {
        titleTxt.setFont(FontLoader.getGostischFont(28));
        buttonBlock.setAlignment(Pos.CENTER);

        String btnStyle = "-fx-pref-width: 140;" +
                "-fx-pref-height: 40;" +
                "-fx-background-radius: 999px;" +
                "-fx-background-color: #d3d3d3;" +
                "-fx-text-fill: #000000;";
        returnBtn.setFont(FontLoader.getGostischFont(20));
        returnBtn.setStyle(btnStyle);
        quitBtn.setFont(FontLoader.getGostischFont(20));
        quitBtn.setStyle(btnStyle);

        returnBtn.setOnAction(event -> {
            if (onReturnAction != null) {
                onReturnAction.accept(fightCount);
            }
        });

        buttonBlock.getChildren().addAll(returnBtn, quitBtn);
        exitMarkerRoot.getChildren().addAll(titleTxt, buttonBlock);
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
    public void setReturnButton(IntegerProperty fightCount, Consumer<IntegerProperty> onReturnAction) {
        this.fightCount = fightCount;
        this.onReturnAction = onReturnAction;
    }

    @Override
    public void setQuitButton(Consumer<Object> onTerminateProgram) {
        quitBtn.setOnAction(event -> onTerminateProgram.accept(event));
    }

    @Override
    public void setVisible(boolean visible) {
        exitMarkerRoot.setVisible(visible);
    }
}
