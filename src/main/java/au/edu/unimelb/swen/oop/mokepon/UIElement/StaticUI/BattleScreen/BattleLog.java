package au.edu.unimelb.swen.oop.mokepon.UIElement.StaticUI.BattleScreen;

import javafx.scene.control.TextArea;
import javafx.scene.text.Font;

import static au.edu.unimelb.swen.oop.mokepon.UIElement.Utils.ScreenPosition.*;

public class BattleLog {

    public static TextArea createBattleLog() {

        TextArea battleLog = new TextArea(">>> battle log :\n");
        battleLog.setFont(Font.font("default", 15));
        battleLog.setEditable(false);
        battleLog.setPrefSize(620, 300);
        battleLog.setLayoutX(screenWidth - 620 - 20 - 20);
        battleLog.setLayoutY(screenHeight + menuBarHeight - 300 - 20 - 15);

        return battleLog;
    }

}
