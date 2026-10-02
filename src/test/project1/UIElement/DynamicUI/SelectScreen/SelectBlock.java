package project1.UIElement.DynamicUI.SelectScreen;

import project1.Backend.Mokepon;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;

import static project1.UIElement.StaticUI.SelectScreen.DisplayBlock.scrollHeight;
import static project1.UIElement.StaticUI.SelectScreen.DisplayBlock.scrollWidth;
import static project1.UIElement.Utils.ScreenPosition.screenHeight;
import static project1.UIElement.Utils.ScreenPosition.screenWidth;

public class SelectBlock {

    public static final double titleX = 56.8889;
    public static final double titleY = 104.4348;

    public static ScrollPane createSelectButtonBlock(ArrayList<Mokepon> mokeponList, Font txtFont, ArrayList<Button> selectBtnList) {

        VBox btnBlock = new VBox(10);
        for (Mokepon mokepon : mokeponList) {
            Button nameBtn = createSelectButton(mokepon.getName(), txtFont);
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

    public static Button createSelectButton(String txt, Font txtFont) {

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


    public static GridPane createAttributeDisplayBlock(String[] attrArr, ArrayList<Text> attrTxtList) {

        GridPane attrBlock = new GridPane(30, 15);

        for (int i = 0; i < attrArr.length; i++) {
            Text attrTxt = new Text(attrArr[i] + ":");
            attrTxt.setFont(Font.font("default", FontWeight.BOLD, 16));
            Text appendTxt = new Text();
            appendTxt.setFont(Font.font("default", 16));
            attrTxtList.add(appendTxt);
            attrBlock.add(attrTxt, 0, i);
            attrBlock.add(appendTxt, 1, i);
        }
        attrBlock.setLayoutX(0.488372 * screenWidth + 4);
        attrBlock.setLayoutY(0.63125 * screenHeight + 5);
        attrBlock.setAlignment(Pos.CENTER_LEFT);
        attrBlock.setMaxSize(scrollWidth, scrollHeight);

        return attrBlock;
    }

}
