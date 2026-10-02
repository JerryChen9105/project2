package project1.UIElement.DynamicUI.BattleScreen;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;

import static project1.UIElement.Utils.ScreenPosition.*;
import static project1.UIElement.Utils.ScreenPosition.menuBarHeight;

public class SelfBlock {

    public static final String[] skillArr = {"Bite[1]", "tackle[5]", "bunker[3]", "wait[-2]"};

    public static VBox createSelfBarBlock(ProgressBar[] selfBarArr, Text[] selfBarInfoArr) {

        VBox selfBarBlock = new VBox(7);
        selfBarBlock.setLayoutX(relativeCenterX(screenLeft + 190, 250));
        selfBarBlock.setLayoutY(screenHeight + menuBarHeight - 115);
        selfBarBlock.setAlignment(Pos.CENTER);


        StackPane selfHPBlock = new StackPane();
        ProgressBar selfHPBar = new ProgressBar();
        selfHPBar.setStyle("-fx-pref-width: 250; -fx-pref-height: 27;" +
                "-fx-accent: #31c831;"
        );
        selfHPBar.setProgress(1.0);
        Text selfHPBarText = new Text();
        selfHPBarText.setStyle("-fx-font-size: 16");
        selfHPBlock.getChildren().addAll(selfHPBar, selfHPBarText);

        StackPane selfEnergyBlock = new StackPane();
        ProgressBar selfEnergyBar = new ProgressBar();
        selfEnergyBar.setStyle("-fx-pref-width: 250; -fx-pref-height: 27;" +
                "-fx-accent: #3e64d8;"
        );
        selfEnergyBar.setProgress(1.0);
        Text selfEnergyBarText = new Text();
        selfEnergyBarText.setStyle("-fx-font-size: 16");
        selfEnergyBlock.getChildren().addAll(selfEnergyBar, selfEnergyBarText);

        selfBarBlock.getChildren().addAll(selfHPBlock, selfEnergyBlock);

        selfBarArr[0] = selfHPBar; selfBarArr[1] = selfEnergyBar;
        selfBarInfoArr[0] = selfHPBarText; selfBarInfoArr[1] = selfEnergyBarText;

        return selfBarBlock;
    }


    public static VBox createSkillBlock(Font titleFont, Font btnFont, ArrayList<Button> skillBtnList) {

        VBox skillBlock = new VBox(15);
        skillBlock.setLayoutX(screenLeft + 365);
        skillBlock.setLayoutY(screenHeight + menuBarHeight - 375);
        skillBlock.setAlignment(Pos.CENTER);

        Text skillTitle = new Text("Actions");
        skillTitle.setFont(titleFont);

        VBox skillBtnBlock = new VBox(10);
        skillBtnBlock.setAlignment(Pos.CENTER);
        for (String skill : skillArr) {
            Button btn = new Button(skill);
            btn.setFont(btnFont);
            btn.setStyle("-fx-background-radius: 999px;" +
                    "-fx-background-color: #d3d3d3;" +
                    "-fx-text-fill: #000000;"
            );
            btn.setOpacity(0.2);
            btn.setPrefSize(120, 30);
            skillBtnBlock.getChildren().add(btn);
            skillBtnList.add(btn);
        }

        skillBlock.getChildren().addAll(skillTitle, skillBtnBlock);

        return skillBlock;
    }

}
