package au.edu.unimelb.swen.oop.mokepon.SelectScreen_Test.StaticUI;

import au.edu.unimelb.swen.oop.mokepon.SelectScreen_Test.SelectBlockElement;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.HashMap;

public class SelectTitle extends SelectBlockElement implements SelectScreenStaticElement<Text> {

    @Override
    public Text buildElement(HashMap<String, Object> dict) {
        Text selectTitle = new Text((String)dict.get("title"));
        selectTitle.setFont((Font)dict.get("font"));
        selectTitle.setStyle("-fx-fill: #000000;" +
                "-fx-stroke: #ffffff;" +
                "-fx-stroke-width: 2px;");
        return selectTitle;
    }

}
