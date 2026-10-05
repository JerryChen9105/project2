package au.edu.unimelb.swen.oop.mokepon.view.fx.select;

import au.edu.unimelb.swen.oop.mokepon.driven.model.Mokepon;
import au.edu.unimelb.swen.oop.mokepon.util.FontLoader;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.select.MokeponListView;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.function.Consumer;

public class FxMokeponListView implements MokeponListView {

    private Text mokeponSelectTitle = new Text("Choose your Mokepon");

    private ScrollPane scrollPane = new ScrollPane();
    private VBox listBox = new VBox(10);

    private VBox selectBlock = new VBox(20);

    private HashMap<Mokepon, Button> buttonMap = new LinkedHashMap<>();
    private Font btnFont = FontLoader.getGostischFont(20);
    private Font titleFont = FontLoader.getGostischFont(34);
    private Button selectedButton;


    public FxMokeponListView() {
        listBox.setAlignment(Pos.CENTER);
        scrollPane.setContent(listBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setBackground(Background.EMPTY);
        scrollPane.setStyle("-fx-background-color: transparent;");
        scrollPane.skinProperty().addListener((observer, oldSkin, newSkin) -> {
            Node viewport = scrollPane.lookup(".viewport");
            if (viewport != null)
                viewport.setStyle("-fx-background-color: transparent;");

            Node scrollBar = scrollPane.lookup(".scroll-bar");
            if (scrollBar != null)
                scrollBar.setStyle("-fx-background-color: transparent;");
        });
        scrollPane.setPrefSize(300, 600);

        mokeponSelectTitle.setFont(titleFont);

        selectBlock.getChildren().addAll(mokeponSelectTitle, scrollPane);
        selectBlock.setAlignment(Pos.CENTER);
    }


    @Override
    public Node node() {
        return selectBlock;
    }

    @Override
    public void setItems(ArrayList<Mokepon> mokeponList, Consumer<Mokepon> onSelect) {
        listBox.getChildren().clear();
        buttonMap.clear();
        for (Mokepon mokepon : mokeponList) {
            Button nameBtn = new Button(mokepon.getName());
            nameBtn.setFont(btnFont);
            nameBtn.setStyle("-fx-background-radius: 999px;" +
                    "-fx-background-color: #d3d3d3;" +
                    "-fx-text-fill: #000000;"
            );
            nameBtn.setOpacity(0.2);
            nameBtn.setOnMouseEntered(click -> {
                nameBtn.setOpacity(0.5);
            });
            nameBtn.setOnMouseExited(click -> {
                nameBtn.setOpacity(0.2);
            });
            nameBtn.setOnMouseClicked(click -> {
                nameBtn.setOpacity(0.75);
            });
            nameBtn.setPrefSize(280, 25);
            nameBtn.setOnAction(event -> onSelect.accept(mokepon));
            buttonMap.put(mokepon, nameBtn);
            listBox.getChildren().add(nameBtn);
        }
    }

    @Override
    public String getSelectedButtonName() {
        return selectedButton.getText();
    }

    @Override
    public void select(Mokepon mokepon) {
        clearSelection();
        selectedButton = buttonMap.get(mokepon);
    }

    @Override
    public void clearSelection() {
        selectedButton = null;
    }
}
