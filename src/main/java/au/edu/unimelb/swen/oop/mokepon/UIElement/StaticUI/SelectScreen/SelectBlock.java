package au.edu.unimelb.swen.oop.mokepon.UIElement.StaticUI.SelectScreen;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class SelectBlock {

    public static Text createSelectTitle(String title, Font titleFont) {
        Text selectTitle = new Text(title);
        selectTitle.setFont(titleFont);
        selectTitle.setStyle("-fx-fill: #000000;" +
                            "-fx-stroke: #ffffff;" +
                            "-fx-stroke-width: 2px;");
        return selectTitle;
    }

}
