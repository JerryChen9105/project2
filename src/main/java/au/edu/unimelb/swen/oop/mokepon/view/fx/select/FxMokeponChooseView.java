package au.edu.unimelb.swen.oop.mokepon.view.fx.select;

import au.edu.unimelb.swen.oop.mokepon.model.Mokepon;
import au.edu.unimelb.swen.oop.mokepon.util.FontLoader;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.select.MokeponChooseView;
import javafx.beans.property.ObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.Button;

import java.util.function.Consumer;

public class FxMokeponChooseView implements MokeponChooseView {

    private Button mokeponChooseBtn = new Button("Choose Mokepon");


    public FxMokeponChooseView() {
        mokeponChooseBtn.setStyle("-fx-background-color: #d3d3d3;" +
                "-fx-text-fill: #000000;" +
                "-fx-wrap-text: true;" +
                "-fx-text-alignment: center;" +
                "-fx-max-width: 160;" +
                "-fx-min-height: 170;" +
                "-fx-background-radius: 15;");
        mokeponChooseBtn.setOpacity(0.2);
        mokeponChooseBtn.setOnMouseEntered(enter -> {
            mokeponChooseBtn.setOpacity(0.5);
        });
        mokeponChooseBtn.setOnMouseExited(exit -> {
            mokeponChooseBtn.setOpacity(0.2);
        });
        mokeponChooseBtn.setOnMouseClicked(click -> {
            mokeponChooseBtn.setOpacity(0.75);
        });
        mokeponChooseBtn.setFont(FontLoader.getGostischFont(24));
        mokeponChooseBtn.setLayoutX(1045);
        mokeponChooseBtn.setLayoutY(515);
    }


    @Override
    public Node node() {
        return mokeponChooseBtn;
    }

}
