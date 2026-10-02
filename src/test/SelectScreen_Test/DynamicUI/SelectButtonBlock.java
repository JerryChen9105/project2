package au.edu.unimelb.swen.oop.mokepon.SelectScreen_Test.DynamicUI;

import au.edu.unimelb.swen.oop.mokepon.Backend.Mokepon;
import au.edu.unimelb.swen.oop.mokepon.SelectScreen_Test.SelectBlockElement;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.HashMap;

public class SelectButtonBlock extends SelectBlockElement implements SelectScreenDynamicElement<ScrollPane, ArrayList<Button>> {

    protected static ArrayList<Button> selectBtnList = new ArrayList<>();

    @Override
    public ScrollPane buildElement(HashMap<String, Object> dict) {
        VBox btnBlock = new VBox(10);
        for (Mokepon mokepon : (ArrayList<Mokepon>)dict.get("mokeponList")) {
            Button nameBtn = createSelectButton(mokepon.getName(), (Font)dict.get("font"));
            btnBlock.getChildren().add(nameBtn);
            selectBtnList.add(nameBtn);
        }
        btnBlock.setAlignment(Pos.CENTER);

        ScrollPane scrollPane = new ScrollPane(btnBlock);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
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

        return scrollPane;
    }

    private Button createSelectButton(String txt, Font txtFont) {

        Button btn = new Button(txt);
        btn.setFont(txtFont);
        btn.setStyle("-fx-background-radius: 999px;" +
                "-fx-background-color: #d3d3d3;" +
                "-fx-text-fill: #000000;"
        );

        btn.setOpacity(0.2);
        btn.setPrefSize(280, 25);

        return btn;
    }

    @Override
    public ArrayList<Button> getInnerElement() {
        return selectBtnList;
    }
}
