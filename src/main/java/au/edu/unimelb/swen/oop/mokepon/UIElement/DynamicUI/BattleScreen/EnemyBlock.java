package au.edu.unimelb.swen.oop.mokepon.UIElement.DynamicUI.BattleScreen;

import javafx.geometry.Pos;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;

public class EnemyBlock {

    public static VBox createEnemyBarBlock(ProgressBar[] enemyBarArr) {

        VBox enemyBarBlock = new VBox(5);
        enemyBarBlock.setAlignment(Pos.CENTER);

        ProgressBar enemyHPBar = new ProgressBar();
        enemyHPBar.setStyle("-fx-pref-width: 210; -fx-pref-height: 25;" +
                "-fx-accent: #31c831;"
        );
        enemyHPBar.setProgress(1.0);

        ProgressBar enemyEnergyBar = new ProgressBar();
        enemyEnergyBar.setStyle("-fx-pref-width: 210; -fx-pref-height: 25;" +
                "-fx-accent: #3e64d8;"
        );
        enemyEnergyBar.setProgress(1.0);

        enemyBarBlock.getChildren().addAll(enemyHPBar, enemyEnergyBar);
        enemyBarArr[0] = enemyHPBar;
        enemyBarArr[1] = enemyEnergyBar;

        return enemyBarBlock;
    }

}
